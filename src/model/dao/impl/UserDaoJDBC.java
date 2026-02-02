package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

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

	}

	@Override
	public void deleteById(Integer id) {

	}

	@Override
	public User findById(Integer id) {

		return null;
	}

	@Override
	public List<User> findAll() {

		return null;
	}

}
