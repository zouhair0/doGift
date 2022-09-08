package com.glady.challenge.dao;

import java.util.List;

import com.glady.challenge.entities.User;

public interface UserDao {

	public void addUser(User user) throws Exception;
	public User findUserById(int id) throws Exception;
	public List<User> findAllUsers();
}
