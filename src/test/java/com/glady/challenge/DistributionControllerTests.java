package com.glady.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.glady.challenge.dao.CompanyDaoImpl;
import com.glady.challenge.dao.UserDaoImpl;
import com.glady.challenge.entities.Company;
import com.glady.challenge.entities.Distribution;
import com.glady.challenge.entities.User;
import com.glady.challenge.utils.Constants;
import com.glady.challenge.web.controller.DistributionController;

@SpringBootTest
public class DistributionControllerTests {
	
	//TODO : Switching to JUNIT-5 instead of this embedded in spring-boot-starter-test : This can help calling distributionService directly
	// and avoid redeclare its methods here.
	
//	@Autowired
//	DistributionService distributionService;
	
	@Autowired
	DistributionController distributionController;

	static List<Distribution> distributions = new ArrayList<>();
	static {
		CompanyDaoImpl companyDao = null;
		UserDaoImpl userDao = null;

		User user = new User("Martin", 0, 0, 1);
		Company company = new Company("Apple", 8000, 1);
		
		companyDao.companies.add(company);
		userDao.users.add(user);
		
		distributions.add(new Distribution(company, user, 80, Constants.GIFT_DEPOSIT, 0, setToZeroHour(new Date()), calculateExpirationDateOfGiftDeposits(new Date())));
		distributions.add(new Distribution(company, user, 120, Constants.GIFT_DEPOSIT, 1, setToZeroHour(new Date()), calculateExpirationDateOfGiftDeposits(new Date())));
		distributions.add(new Distribution(company, user, 150, Constants.MEAL_DEPOSIT, 0, setToZeroHour(new Date()), calculateExpirationDateOfMealDeposits(new Date())));
		distributions.add(new Distribution(company, user, 200, Constants.MEAL_DEPOSIT, 1, setToZeroHour(new Date()), calculateExpirationDateOfMealDeposits(new Date())));
	}
	
	
	@Test
	void sendGiftDepositTest() throws Exception {  
		assertEquals(distributionController.sendGiftDeposit(1, 1, 80), distributions.get(0));
		assertEquals(distributionController.sendGiftDeposit(1, 1, 120), distributions.get(1));
	}
	
	@Test
	void sendMealDepositTest() throws Exception  {  
		assertEquals(distributionController.sendMealDeposit(1, 1, 150), distributions.get(2));
		assertEquals(distributionController.sendMealDeposit(1, 1, 200), distributions.get(3));
	}
	
	public static Date calculateExpirationDateOfGiftDeposits(Date startDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		cal.add(Calendar.YEAR, 1);
		cal.add(Calendar.DATE, -1);
		
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		Date endDate = cal.getTime();
		return endDate;
	}
	
	public static Date calculateExpirationDateOfMealDeposits(Date startDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.MONTH, Calendar.FEBRUARY);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));

		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date endDate = cal.getTime();
		return endDate;
	}
	
	public static Date setToZeroHour(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
}
