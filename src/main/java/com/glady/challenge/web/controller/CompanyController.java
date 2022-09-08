package com.glady.challenge.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.glady.challenge.dao.CompanyDao;
import com.glady.challenge.entities.Company;

@RestController
public class CompanyController {

	@Autowired
	CompanyDao companyDao;
	
	@GetMapping(value="/company/{idCp}")
	public Company getCompanyById(@PathVariable int idCp) throws Exception {
		return companyDao.findCompanyById(idCp);
	}
	
	@GetMapping(value="/allCompanies")
	public List<Company> getAllCompanies() {
		return companyDao.findAllCompanies();
	}
	
	@PostMapping(value="/addCompany")
	public void setCompany(@RequestBody Company company) throws Exception {
		companyDao.addCompany(company);
	}
	
}
