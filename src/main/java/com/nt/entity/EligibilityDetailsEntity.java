package com.nt.entity;

import java.time.LocalDate;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "eligibility_dtls")
public class EligibilityDetailsEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "elig_id")
	private Integer eligId;
	@Column(name = "case_no")
	private Long caseNo;
	@Column(name = "plan_name")
	private String planName;
	@Column(name = "plan_status")
	private String planStatus;
	@Column(name = "benefit_amt")
	private Double benefitAmt;
	@Column(name = "start_date")
	private LocalDate startDate;
	@Column(name = "end_date")
	private LocalDate endDate;
	@Column(name = "denial_reason")
	private String denialReason;
	@Column(name = "holder_name")
	private String holderName;
	@Column(name = "holder_ssn")
	private Long holderSsn;
}
