package com.glady.challenge.dao;

import java.util.List;

import com.glady.challenge.entities.Company;

public interface CompanyDao {
	
	public void addCompany(Company company) throws Exception;
	public Company findCompanyById(int id) throws Exception;
	public List<Company> findAllCompanies();

}
