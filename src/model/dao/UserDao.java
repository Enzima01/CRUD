package model.dao;

import java.util.List;

import model.entities.User;

public interface UserDao {
	void insert(User u);
	void update(User u);
	void deleteById(Integer id);
	User findById(Integer id);
	List<User> findAll();
}
