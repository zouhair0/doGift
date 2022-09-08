package com.glady.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.glady.challenge.utils.Constants;
import com.glady.challenge.web.service.DistributionService;

@SpringBootTest
public class DistributionServiceTests {
	
	@Autowired
	DistributionService distributionService;
	
	@Test
	void expirationDateOfGiftDepositsShoudBeAfter365DaysSinceDeposit() throws ParseException {  
		assertEquals(distributionService.calculateExpirationDateOfGiftDeposits(convertStringToDate("31/12/1998")), convertStringToDate("30/12/1999"));
		assertEquals(distributionService.calculateExpirationDateOfGiftDeposits(convertStringToDate("15/06/2021")), convertStringToDate("14/06/2022"));
		assertEquals(distributionService.calculateExpirationDateOfGiftDeposits(convertStringToDate("01/01/2022")), convertStringToDate("31/12/2022"));
	}
	
	@Test
	void expirationDateOfMealtDepositsShoudBeTheEndOfFebruaryOfTheYearFollowing( ) throws ParseException {
		assertEquals(distributionService.calculateExpirationDateOfMealDeposits(convertStringToDate("01/01/2020")), convertStringToDate("28/02/2021"));
		assertEquals(distributionService.calculateExpirationDateOfMealDeposits(convertStringToDate("30/12/2021")), convertStringToDate("28/02/2022"));
		assertEquals(distributionService.calculateExpirationDateOfMealDeposits(convertStringToDate("01/10/2023")), convertStringToDate("29/02/2024"));
	}
	
	Date convertStringToDate(String strDate) throws ParseException {
        Date date = new SimpleDateFormat(Constants.DATE_FORMAT).parse(strDate);
        return date;
	}
}
