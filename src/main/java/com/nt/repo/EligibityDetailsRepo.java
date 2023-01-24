package com.nt.repo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nt.entity.EligibilityDetailsEntity;

public interface EligibityDetailsRepo extends JpaRepository<EligibilityDetailsEntity, Integer> {
	
	@Query(value="select distinct(plan_name) from ELIGIBILITY_DTLS",nativeQuery = true)
	public List<String> getUniquePlanNames();
	
	@Query(value="select distinct(plan_status) from ELIGIBILITY_DTLS",nativeQuery = true)
	public List<String> getUniquePlanStatus();

}
