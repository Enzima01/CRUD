package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.UserDao;
import model.entities.User;

public class UserDaoJDBC implements UserDao {

	private Connection conn;

	public UserDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(User u) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO user(name, email) VALUES (?,?)", Statement.RETURN_GENERATED_KEYS);
			st.setString(1, u.getName());
			st.setString(2, u.getEmail());

			int rowsAffected = st.executeUpdate();
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					u.setId(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("No rows affected!");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void update(User u) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE user SET name = ?, email = ? WHERE id = ?");
			st.setString(1, u.getName());
			st.setString(2, u.getEmail());
			st.setInt(3, u.getId());
			st.executeUpdate();
			System.out.println("User updated!");
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM user WHERE id = ?");
			st.setInt(1, id);
			int rowsAffected = st.executeUpdate();
			if (rowsAffected == 0) {
				throw new DbException("User not found!");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);

		}
	}

	@Override
	public User findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM user WHERE id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				User user = instantiateUser(rs);
				return user;
			} else {
				throw new DbException("User not found!");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}

	}

	@Override
	public List<User> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM user");
			rs = st.executeQuery();
			List<User> list = new ArrayList<>();
			Map<Integer, User> map = new HashMap<>();
			while (rs.next()) {
				User user = map.get(rs.getInt("id"));
				if (user == null) {
					user = instantiateUser(rs);
					map.put(rs.getInt("id"), user);
				}
				list.add(user);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private User instantiateUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setEmail(rs.getString("email"));
		return user;
	}

}
