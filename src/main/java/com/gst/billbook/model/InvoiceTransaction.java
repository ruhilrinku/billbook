package com.gst.billbook.model;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class InvoiceTransaction implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String paymentMode;
	private Date paymentDate;
	private double amountPaid;
	private String chequeNumber;
	private Date chequeDate;
	private Date chequeClearanceDate;
	private String transactionReference;
	
	public InvoiceTransaction() {}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public String getChequeNumber() {
		return chequeNumber;
	}

	public void setChequeNumber(String chequeNumber) {
		this.chequeNumber = chequeNumber;
	}

	public Date getChequeDate() {
		return chequeDate;
	}

	public void setChequeDate(Date chequeDate) {
		this.chequeDate = chequeDate;
	}

	public Date getChequeClearanceDate() {
		return chequeClearanceDate;
	}

	public void setChequeClearanceDate(Date chequeClearanceDate) {
		this.chequeClearanceDate = chequeClearanceDate;
	}

	public String getTransactionReference() {
		return transactionReference;
	}

	public void setTransactionReference(String transactionReference) {
		this.transactionReference = transactionReference;
	}
	
	public static InvoiceTransaction toObject(String json) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		try {
			return mapper.readValue(json, InvoiceTransaction.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String toJsonString() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		try {
			return mapper.writeValueAsString(this);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
}
