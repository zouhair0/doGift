package com.glady.challenge.dao;

import java.util.List;

import com.glady.challenge.entities.Distribution;

public interface DistributionDao {
	
	public void addDistribution(Distribution distribution);
	public Distribution findDistributionById(int id);
	public List<Distribution> findAllDistributions();
}