package com.nt.response;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SearchResponse {
	
	private Long caseNo;
	private String planName;
	private String planStatus;
	private Double benefitAmt;
	private LocalDate startDate;
	private LocalDate endDate;
	private String denialReason;
	private String holderName;
	private Long holderSsn;

}
