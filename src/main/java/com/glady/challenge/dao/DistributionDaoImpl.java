package com.glady.challenge.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.glady.challenge.entities.Distribution;

@Repository
public class DistributionDaoImpl implements DistributionDao{
	
	public static List<Distribution> distributions = new ArrayList<>();

	@Override
	public void addDistribution(Distribution distribution) {
		distributions.add(distribution);
	}

	@Override
	public Distribution findDistributionById(int id) {
		return distributions.stream().filter(d -> d.getId() == id).collect(Collectors.toList()).get(0);
	}

	@Override
	public List<Distribution> findAllDistributions() {
		return distributions;
	}

}
