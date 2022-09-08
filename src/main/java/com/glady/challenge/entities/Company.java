package com.glady.challenge.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data

public class Company {

	String name;
	float balance;
	int id;
	
	public Company(String name, float balance, int id) {
		this.name = name;
		this.balance = balance;
		this.id = id;
	}
}