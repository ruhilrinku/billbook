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

import com.gst.billbook.model.PurchaseInvoiceItemModel;
import com.gst.billbook.model.PurchaseInvoiceModel;
import com.gst.billbook.model.PurchaseInvoicePaymentModel;
import com.gst.billbook.service.PurchaseInvoiceService;

@RestController
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class PurchaseInvoiceController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseInvoiceController.class);
	
	@Autowired
	private PurchaseInvoiceService purchaseInvoiceService;
	
	@GetMapping(path = "/purchaseInvoice/{purchaseInvoiceNum}", produces = "application/json")
	public ResponseEntity<PurchaseInvoiceModel> getPurchaseInvoice(@PathVariable("purchaseInvoiceNum") int purchaseInvoiceNum) {
		PurchaseInvoiceModel purchaseInvoice = null;
		try {
			purchaseInvoice = purchaseInvoiceService.getPurchaseInvoiceDetails(purchaseInvoiceNum);
			return new ResponseEntity<>(purchaseInvoice, HttpStatus.OK);
		} catch(Exception e) {
			LOGGER.error("", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(path = "/purchaseInvoice/save", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Integer> savePurchaseInvoice(@RequestBody String json) {
		PurchaseInvoiceModel purchaseInvoice = null;
		try {
			purchaseInvoice = PurchaseInvoiceModel.toObject(json);
			int invoiceNum = purchaseInvoiceService.savePurchaseInvoiceDetails(purchaseInvoice);
			return new ResponseEntity<Integer>(invoiceNum, HttpStatus.OK);
		} catch(Exception e) {
			LOGGER.error("", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(path="/purchaseInvoice/items/{purchaseInvoiceNumber}", produces = "application/json")
	public ResponseEntity<Set<PurchaseInvoiceItemModel>> getItemsByInvoice(@PathVariable("purchaseInvoiceNumber") int invoiceNumber) {
		Set<PurchaseInvoiceItemModel> invoiceItemSet = null;
		try {
			invoiceItemSet = purchaseInvoiceService.getPurchaseInvoiceItems(invoiceNumber);
			return new ResponseEntity<>(invoiceItemSet, HttpStatus.OK);
		} catch(Exception ex) {
			LOGGER.error("Failed to fetch Invoice Item details: ", ex);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(path="/purchaseInvoice/payments/{purchaseInvoiceNumber}", produces = "application/json")
	public ResponseEntity<Set<PurchaseInvoicePaymentModel>> getInvoiceTransactionsByInvoice(@PathVariable("purchaseInvoiceNumber") int invoiceNumber) {
		Set<PurchaseInvoicePaymentModel> invoiceTransactionSet = null;
		try {
			invoiceTransactionSet = purchaseInvoiceService.getPurchaseInvoicePayments(invoiceNumber);
			return new ResponseEntity<>(invoiceTransactionSet, HttpStatus.OK);
		} catch(Exception ex) {
			LOGGER.error("Failed to fetch Invoice Transaction details: ", ex);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
