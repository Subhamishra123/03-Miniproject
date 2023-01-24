package com.nt.service;

import java.util.List;

import com.nt.request.SearchRequest;
import com.nt.response.SearchResponse;

public interface IReportService {
	
	public List<String> getPlanNames();
	public List<String> getPlanStatus();
	public List<SearchResponse> getRecords(SearchRequest request);
	
}
