package com.gst.billbook.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gst.billbook.model.InvoiceDetailsModel;
import com.gst.billbook.model.InvoiceItem;
import com.gst.billbook.model.InvoiceTransaction;
import com.gst.billbook.model.SalesInvoice;
import com.gst.billbook.service.InvoiceDetailsService;

import java.io.FileOutputStream;
import java.sql.Date;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class InvoiceDetailsController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceDetailsController.class);
	
	@Autowired
	private InvoiceDetailsService invoiceDetailService;
	
	@GetMapping(path="/invoiceDetails/{invoiceNumber}", produces = "application/json")
	public ResponseEntity<InvoiceDetailsModel> getInvoiceDetails(@PathVariable("invoiceNumber") int invoiceNumber) {
		InvoiceDetailsModel detailsModel = null;
		try {
			detailsModel = invoiceDetailService.getInvoiceDetails(invoiceNumber);
			return new ResponseEntity<>(detailsModel, HttpStatus.OK);
		} catch(Exception ex) {
			LOGGER.error("Failed to fetch details: ", ex);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(path="/invoiceDetails/save", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Integer> saveInvoiceDetails(@RequestBody String json) {
		InvoiceDetailsModel detailsModel = null;
		try {
			detailsModel = InvoiceDetailsModel.toObject(json);
			int invoiceNum = invoiceDetailService.saveInvoiceDetails(detailsModel);
			return new ResponseEntity<Integer>(invoiceNum, HttpStatus.OK);
			
		} catch(Exception ex) {
			LOGGER.error("Failed to save details: ", ex);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(path="/invoiceItemDetails/{invoiceNumber}", produces = "application/json")
	public ResponseEntity<Set<InvoiceItem>> getItemsByInvoice(@PathVariable("invoiceNumber") int invoiceNumber) {
		Set<InvoiceItem> invoiceItemSet = null;
		try {
			invoiceItemSet = invoiceDetailService.getInvoiceItems(invoiceNumber);
			return new ResponseEntity<>(invoiceItemSet, HttpStatus.OK);
		} catch(Exception ex) {
			LOGGER.error("Failed to fetch Invoice Item details: ", ex);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(path="/invoiceTransactions/{invoiceNumber}", produces = "application/json")
	public ResponseEntity<Set<InvoiceTransaction>> getInvoiceTransactionsByInvoice(@PathVariable("invoiceNumber") int invoiceNumber) {
		Set<InvoiceTransaction> invoiceTransactionSet = null;
		try {
			invoiceTransactionSet = invoiceDetailService.getInvoiceTransactions(invoiceNumber);
			return new ResponseEntity<>(invoiceTransactionSet, HttpStatus.OK);
		} catch(Exception ex) {
			LOGGER.error("Failed to fetch Invoice Transaction details: ", ex);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/salesInvoiceReport")
	public ResponseEntity<Void> generateSalesInvoiceReport(HttpServletResponse response) {
		try {
			response.setContentType("application/octet-stream");
			String headerKey = "Content-Disposition";
			String headerValue = "attachement; filename=sales.xlsx";
			
			response.setHeader(headerKey, headerValue);
			
			
			List<SalesInvoice> salesInvoices = new ArrayList<SalesInvoice>();
			
			SalesInvoice invoice = new SalesInvoice(1, 1, new Date(2020, 10, 10), "GSTN1234", "Nishant Kumar", "Haryana", 2000, 3000, 1000);
			salesInvoices.add(invoice);
			
			exportExcelReportPOI(response, salesInvoices);
		} catch (Exception e) {
			LOGGER.error("Failed to generate Sales Invoice Report: ", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return null;
	}
	
	public void exportExcelReportPOI(HttpServletResponse response, List<SalesInvoice> salesInvoices) throws Exception {
		
		String[] columns = {"Serial Number", "Invoice Number", "Invoice Date", "Buyer GSTN", "Buyer Name", "pos", "Taxable Amount", "Total Tax", "Total Amount"};
		
		Workbook workbook = new XSSFWorkbook();
		
		CreationHelper createHelper = workbook.getCreationHelper();
		
		Sheet sheet = workbook.createSheet("Sales invoice");
		
		Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());
        
        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        
        // Create a header Row
        Row headerRow = sheet.createRow(0);
        
        // Create Header cells
        for(int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }
        
        // Create Cell Style for formatting Date
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
        
        int rownum = 1;
        for(SalesInvoice invoice: salesInvoices) {
        	Row row = sheet.createRow(rownum++);
        	row.createCell(0).setCellValue(invoice.getSerialNo());
        	row.createCell(1).setCellValue(invoice.getInvoiceNumber());
        	
        	Cell dateCell = row.createCell(2);
        	dateCell.setCellStyle(dateCellStyle);
        	dateCell.setCellValue(invoice.getInvoiceDate());
        	
        	row.createCell(3).setCellValue(invoice.getBuyerGSTN());
        	row.createCell(4).setCellValue(invoice.getBuyerName());
        	row.createCell(5).setCellValue(invoice.getPos());
        	row.createCell(6).setCellValue(invoice.getTaxableAmount());
        	row.createCell(7).setCellValue(invoice.getTotalTax());
        	row.createCell(8).setCellValue(invoice.getTotalAmount());
        }
        
        for(int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }
        
        // Write the output to a file
        
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();

	} 
}
