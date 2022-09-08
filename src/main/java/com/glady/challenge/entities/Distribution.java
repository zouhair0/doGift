package com.glady.challenge.entities;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Distribution {

	Company company;
	User user;
	float amoumt;
	String type;
	int id;
	Date startDate;
	Date endDate;
	
	public Distribution(Company company, User user, float amoumt, String type, int id, Date startDate, Date endDate) {
		this.company = company;
		this.user = user;
		this.amoumt = amoumt;
		this.type = type;
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
	}
}

