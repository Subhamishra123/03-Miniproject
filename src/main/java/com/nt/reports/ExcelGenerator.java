package com.nt.reports;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.nt.response.SearchResponse;

public class ExcelGenerator {
	
	public void generateExcel(List<SearchResponse> response,HttpServletResponse httpResponse)throws ServletException,IOException
	{
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("plans");
		HSSFRow headerRow = sheet.createRow(0);
		
		headerRow.createCell(0).setCellValue("S.No");
		headerRow.createCell(1).setCellValue("Holder Name");
	    headerRow.createCell(2).setCellValue("Holder SSN");
	    headerRow.createCell(3).setCellValue("Plan Name");
	    headerRow.createCell(4).setCellValue("Plan Status");
	    headerRow.createCell(5).setCellValue("Start Date");
	    headerRow.createCell(6).setCellValue("End Date");
	    headerRow.createCell(7).setCellValue("Benefit Amount");
	    headerRow.createCell(8).setCellValue("Denial Reason");
	    for(int i=0;i<response.size();i++) {
	    	HSSFRow row = sheet.createRow(i+1);
	    	SearchResponse searchResponse = response.get(i);
	    	row.createCell(0).setCellValue(i+1);
	    	if(searchResponse.getHolderName()!=null)
	    		row.createCell(1).setCellValue(searchResponse.getHolderName());
	    	if(searchResponse.getHolderSsn()!=null)
	    		row.createCell(2).setCellValue(searchResponse.getHolderSsn());
	    	row.createCell(3).setCellValue(searchResponse.getPlanName());
	    	row.createCell(4).setCellValue(searchResponse.getPlanStatus());
	    	if(searchResponse.getStartDate()!=null)
	    		row.createCell(5).setCellValue(searchResponse.getStartDate());
	    	if(searchResponse.getEndDate()!=null)
	    		row.createCell(6).setCellValue(searchResponse.getEndDate());
	    	if(searchResponse.getBenefitAmt()!=null)
	    		row.createCell(7).setCellValue(searchResponse.getBenefitAmt());
	    	if(searchResponse.getDenialReason()!=null)
	    		row.createCell(8).setCellValue(searchResponse.getDenialReason());
	    }
	    workbook.write(httpResponse.getOutputStream());
	    workbook.close();
	}

}
