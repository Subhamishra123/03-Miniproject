package com.nt.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nt.reports.ExcelGenerator;
import com.nt.reports.PdfGenerator;
import com.nt.request.SearchRequest;
import com.nt.response.SearchResponse;
import com.nt.service.IReportService;

@RestController
public class ReportController {
	
	@Autowired
	private IReportService service;
	
	@GetMapping("/plan-names")
	public List<String> planNames(){
		return service.getPlanNames();
	}
	
	@GetMapping("/plan-status")
	public List<String> planStatus(){
		return service.getPlanStatus();
	}
	
	@PostMapping("/records")
	public List<SearchResponse> getRecords(@RequestBody SearchRequest request)
	{
		System.out.println("get records api");
		return service.getRecords(request);
	}
	
	@GetMapping("/excel-report")
	public void excelReport(HttpServletResponse response)throws ServletException,IOException
	{
		response.setContentType("application/octet-stream");
		String headerKey="Content-Disposition";
		String headerValue="attachment; filename=Plans.xls";
		response.setHeader(headerKey, headerValue);
		List<SearchResponse> records = service.getRecords(null);
		ExcelGenerator generator=new ExcelGenerator();
		generator.generateExcel(records, response);
	}
	@GetMapping("/pdf-report")
	public void pdfReport(HttpServletResponse response)throws ServletException,IOException
	{
		response.setContentType("application/pdf");
		String headerKey="Content-Disposition";
		String headerValue="attachment; filename=Plans.pdf";
		response.setHeader(headerKey, headerValue);
		List<SearchResponse> records = service.getRecords(null);
		PdfGenerator generator = new PdfGenerator();
		generator.generatePdf(records, response);
	}

}
