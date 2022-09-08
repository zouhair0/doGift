package com.glady.challenge.web.controller;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.glady.challenge.dao.CompanyDao;
import com.glady.challenge.dao.DistributionDao;
import com.glady.challenge.dao.UserDao;
import com.glady.challenge.entities.Company;
import com.glady.challenge.entities.Distribution;
import com.glady.challenge.entities.User;
import com.glady.challenge.utils.BalanceException;
import com.glady.challenge.utils.Constants;
import com.glady.challenge.web.service.DistributionService;

@RestController
public class DistributionController {
	
	@Autowired
	DistributionDao distributionDao;
	
	@Autowired
	CompanyDao companyDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	DistributionService distributionService;
	
	public static int idDistr = 0;
	private static final Logger logger = LogManager.getLogger(DistributionController.class);
	
	@GetMapping(value="/gift/{idUser}/{idCp}/{amount}")
	public Distribution sendGiftDeposit(@PathVariable int idUser, @PathVariable int idCp, @PathVariable float amount) throws Exception {
		try {
			Company company = companyDao.findCompanyById(idCp);
			User user = userDao.findUserById(idUser);
			if(amount > company.getBalance()) {
				throw new BalanceException(Constants.EXCEPTION_INSUFFICIENT_BALANCE);
			}

			Date endDate = distributionService.calculateExpirationDateOfGiftDeposits(new Date());
			Distribution distribution = new Distribution(company, user, amount, Constants.GIFT_DEPOSIT, idDistr, distributionService.setToZeroHour(new Date()), endDate);
			distributionDao.addDistribution(distribution);
			company.setBalance(company.getBalance() - amount);
			user.setBalanceGift(user.getBalanceGift() + amount);
			idDistr++;
			return distribution;
		}
		catch(BalanceException e)
		{
			logger.error(Constants.EXCEPTION_INSUFFICIENT_BALANCE);
			throw new Exception(Constants.EXCEPTION_INSUFFICIENT_BALANCE);	
		}
		catch(Exception e)
		{
			logger.error(Constants.EXCEPTION_NO_ACCOUNT + e.getMessage());
			throw new Exception(Constants.EXCEPTION_NO_ACCOUNT);	
		}
		
	}
	

	@GetMapping(value="/meal/{idUser}/{idCp}/{amount}")
	public Distribution sendMealDeposit(@PathVariable int idUser, @PathVariable int idCp, @PathVariable float amount) throws Exception {
		try {
			Company company = companyDao.findCompanyById(idCp);
			User user = userDao.findUserById(idUser);
			if(amount > company.getBalance()) {
				throw new BalanceException(Constants.EXCEPTION_INSUFFICIENT_BALANCE);
			}
			Date endDate = distributionService.calculateExpirationDateOfMealDeposits(new Date());
			Distribution distribution = new Distribution(company, user, amount, Constants.MEAL_DEPOSIT, idDistr, distributionService.setToZeroHour(new Date()), endDate);
			distributionDao.addDistribution(distribution);
			company.setBalance(company.getBalance() - amount);
			user.setBalanceMeal(user.getBalanceMeal() + amount);
			idDistr++;
			return distribution;
		}
		catch(BalanceException e)
		{
			logger.error(Constants.EXCEPTION_INSUFFICIENT_BALANCE);
			throw new Exception(Constants.EXCEPTION_INSUFFICIENT_BALANCE);	
		}
		catch(Exception e)
		{
			logger.error(Constants.EXCEPTION_NO_ACCOUNT + e.getMessage());
			throw new Exception(Constants.EXCEPTION_NO_ACCOUNT);	
		}
	}
	
	@GetMapping(value="/dist/{idDist}")
	public Distribution getDistributionById(@PathVariable int idDist) {
		return distributionDao.findDistributionById(idDist);
	}
	
	@GetMapping(value="/allDist")
	public List<Distribution> getAllDistributions() {
		return distributionDao.findAllDistributions();
	}
	
}
