package com.glady.challenge.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.glady.challenge.entities.Company;
import com.glady.challenge.utils.Constants;

@Repository
public class CompanyDaoImpl implements CompanyDao {

	public static List<Company> companies = new ArrayList<>();
	private static final Logger logger = LogManager.getLogger(CompanyDaoImpl.class);
	
	@Override
	public void addCompany(Company company) throws Exception {
		if(companies.stream().anyMatch(c -> c.getId() == company.getId())) {
			logger.error(Constants.EXCEPTION_ID_ALEARDY_EXIST);
			throw new Exception(Constants.EXCEPTION_ID_ALEARDY_EXIST);
		} else {
			companies.add(company);	
		}		
	}

	@Override
	public Company findCompanyById(int id) throws Exception {
		List <Company> comp = companies.stream().filter(d -> d.getId() == id).collect(Collectors.toList());
		if(comp.size() > 0) {
			return comp.get(0);
		} else {
			logger.error(Constants.EXCEPTION_NO_ACCOUNT);
			throw new Exception(Constants.EXCEPTION_NO_ACCOUNT); 
		}
	}

	@Override
	public List<Company> findAllCompanies() {
		return companies;
	}

}
