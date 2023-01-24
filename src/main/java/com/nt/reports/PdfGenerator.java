package com.nt.reports;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.nt.response.SearchResponse;

public class PdfGenerator {

	@SuppressWarnings("resource")
	public void generatePdf(List<SearchResponse> response, HttpServletResponse httpResponse)
			throws ServletException, IOException {
		Font font = new Font(Font.HELVETICA, 12, Font.BOLDITALIC);
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, httpResponse.getOutputStream());
		document.open();
		Paragraph para = new Paragraph("Eligibility Details", font);
		document.add(para);
		PdfPTable table = new PdfPTable(9);
		table.setWidthPercentage(100);
		// setting column widths
		table.addCell("S.No");
		table.addCell("Holder Name");
		table.addCell("Holder SSN");
		table.addCell("Plan Name");
		table.addCell("Plan Status");
		table.addCell("Start Date");
		table.addCell("End Date");
		table.addCell("Benefit Amount");
		table.addCell("Denial Reason");
		int sno=1;

		// adding table rows
		for (SearchResponse user : response) {
			table.addCell(String.valueOf(sno));
			table.addCell(user.getHolderName());
			table.addCell(String.valueOf(user.getHolderSsn()));
			table.addCell(user.getPlanName());
			table.addCell(user.getPlanStatus());
			table.addCell(String.valueOf(user.getStartDate()));
			table.addCell(String.valueOf(user.getEndDate()));
			table.addCell(String.valueOf(user.getBenefitAmt()));
			table.addCell(user.getDenialReason());
			//table.addCell(new SimpleDateFormat("dd/MM/yyyy").format(user.getDob()));
			sno++;
		}

		// adding table to document
		document.add(table);
		document.close();
		writer.close();
		System.out.println("PDF using OpenPDF created successfully");

	}

}
