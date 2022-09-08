package com.glady.challenge.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class User {

	String name;
	float balanceGift;
	float balanceMeal;
	int id;
	
	public User(String name, float balanceGift, float balanceMeal, int id) {
		this.name = name;
		this.balanceGift = balanceGift;
		this.balanceMeal = balanceMeal;
		this.id = id;
	}
	
}
