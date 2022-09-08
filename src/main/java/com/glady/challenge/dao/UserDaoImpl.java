package com.glady.challenge.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.glady.challenge.entities.User;
import com.glady.challenge.utils.Constants;

@Repository
public class UserDaoImpl implements UserDao {

	public static List<User> users = new ArrayList<>();
	private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);
	
	@Override
	public void addUser(User user) throws Exception {
		if(users.stream().anyMatch(u -> u.getId() == user.getId())) {
			logger.error(Constants.EXCEPTION_ID_ALEARDY_EXIST);
			throw new Exception(Constants.EXCEPTION_ID_ALEARDY_EXIST);
		} else {
			users.add(user);
		}
	}

	@Override
	public User findUserById(int id) throws Exception {
		List<User> user = users.stream().filter(d -> d.getId() == id).collect(Collectors.toList());
		if(user.size() > 0) {
			return user.get(0);
		} else {
			logger.error(Constants.EXCEPTION_NO_ACCOUNT);
			throw new Exception(Constants.EXCEPTION_NO_ACCOUNT); 
		} 
	}

	@Override
	public List<User> findAllUsers() {
		return users;
	}

}
