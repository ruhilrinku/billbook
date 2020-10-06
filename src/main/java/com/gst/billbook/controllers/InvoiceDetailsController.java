package com.gst.billbook.controllers;

import java.util.Set;

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
import com.gst.billbook.service.InvoiceDetailsService;

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
}
