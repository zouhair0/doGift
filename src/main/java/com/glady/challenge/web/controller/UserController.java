package com.glady.challenge.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.glady.challenge.dao.DistributionDao;
import com.glady.challenge.dao.UserDao;
import com.glady.challenge.entities.User;

@RestController
public class UserController {

	@Autowired
	UserDao userDao;
	
	@Autowired
	DistributionDao distributionDao;
	
	@GetMapping(value="/user/{idUser}")
	public User getUserById(@PathVariable int idUser) throws Exception {
		return userDao.findUserById(idUser);
	}
	
	@GetMapping(value="/allUsers")
	public List<User> getAllUsers() {
		return userDao.findAllUsers();
	}
	
	@PostMapping(value="/addUser")
	public void setUser(@RequestBody User user) throws Exception {
		userDao.addUser(user);
	}
	
	@GetMapping(value="/user/balance/{idUser}")
	public String getUserBalance(@PathVariable int idUser) {
		Double totalBalance = distributionDao.findAllDistributions().stream()
									.filter(d -> d.getUser().getId() == idUser && d.getEndDate().after(new Date()))
									.map(d -> d.getAmoumt())
									.mapToDouble(f -> f.doubleValue())
									.sum();
		return "Le solde total à ce jour est à : " + totalBalance ;
	}
}
