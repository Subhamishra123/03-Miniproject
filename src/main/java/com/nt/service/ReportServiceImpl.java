package com.nt.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.nt.entity.EligibilityDetailsEntity;
import com.nt.repo.EligibityDetailsRepo;
import com.nt.request.SearchRequest;
import com.nt.response.SearchResponse;

@Service
public class ReportServiceImpl implements IReportService {

	@Autowired
	private EligibityDetailsRepo repo;
	
	@Override
	public List<String> getPlanNames() {
		// TODO Auto-generated method stub
		return repo.getUniquePlanNames();
	}

	@Override
	public List<String> getPlanStatus() {
		// TODO Auto-generated method stub
		return repo.getUniquePlanStatus();
	}

	@Override
	public List<SearchResponse> getRecords(SearchRequest request) {
		// TODO Auto-generated method stub
		List<SearchResponse> searchRecords=new ArrayList<SearchResponse>();
		List<EligibilityDetailsEntity> allRecords=null;
		System.out.println("inside getrecords ");
		if(isEmptyRequest(request)) {
			System.out.println("empty request");
			 allRecords = repo.findAll();
		}
		else {
			String planName = request.getPlanName();
			String planStatus = request.getPlanStatus();
			LocalDate startDate = request.getStartDate();
			LocalDate endDate = request.getEndDate();
			EligibilityDetailsEntity entity=new EligibilityDetailsEntity();
			if(planName!=null && !planName.equals("")) {
				entity.setPlanName(planName);
			}
			if(planStatus!=null && !planStatus.equals("")) {
				entity.setPlanStatus(planStatus);
			}
			if(startDate!=null && endDate!=null) {
				entity.setStartDate(startDate);
				entity.setEndDate(endDate);
			}
			Example<EligibilityDetailsEntity> example=Example.of(entity);
			allRecords = repo.findAll(example);
		}
	//	SearchResponse response=null;
		searchRecords = allRecords.stream().map(record->{
			SearchResponse response=new SearchResponse();
			BeanUtils.copyProperties(record, response);
			return response;
		}).collect(Collectors.toList());
		/*
		 * for(EligibilityDetailsEntity en:allRecords) { response=new SearchResponse();
		 * BeanUtils.copyProperties(en, response); searchRecords.add(response); }
		 */
		//allRecords.stream().map()
		return searchRecords;
	}
	
	private boolean isEmptyRequest(SearchRequest request)
	{
		if(request.getPlanName()!=null && !request.getPlanName().equals(""))
			return false;
		if(request.getPlanStatus()!=null && !request.getPlanStatus().equals(""))
			return false;
		if(request.getStartDate()!=null && !request.getStartDate().equals(""))
			return false;
		if(request.getEndDate()!=null && !request.getEndDate().equals(""))
			return false;
		return true;
	}

	

}
