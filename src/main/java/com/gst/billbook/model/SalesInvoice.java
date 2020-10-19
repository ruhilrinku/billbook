package com.gst.billbook.model;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class SalesInvoice implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int serialNo;
	private int invoiceNumber;
	private Date invoiceDate;
	private String buyerGSTN;
	private String buyerName;
	private String pos;
	private double taxableAmount;
	private double totalAmount;
	private double totalTax;
	
	public SalesInvoice() {
		super();
	}
	
	public SalesInvoice(int serialNo, int invoiceNumber, Date invoiceDate, String buyerGSTN, String buyerName, String pos,
			double taxableAmount, double totalAmount, double totalTax) {
		super();
		this.serialNo = serialNo;
		this.invoiceNumber = invoiceNumber;
		this.invoiceDate = invoiceDate;
		this.buyerGSTN = buyerGSTN;
		this.buyerName = buyerName;
		this.pos = pos;
		this.taxableAmount = taxableAmount;
		this.totalAmount = totalAmount;
		this.totalTax = totalTax;
	}
	
	public int getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public double getTotalTax() {
		return totalTax;
	}
	public void setTotalTax(double totalTax) {
		this.totalTax = totalTax;
	}
	public int getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(int invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getBuyerGSTN() {
		return buyerGSTN;
	}
	public void setBuyerGSTN(String buyerGSTN) {
		this.buyerGSTN = buyerGSTN;
	}
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	public String getPos() {
		return pos;
	}
	public void setPos(String pos) {
		this.pos = pos;
	}
	public double getTaxableAmount() {
		return taxableAmount;
	}
	public void setTaxableAmount(double taxableAmount) {
		this.taxableAmount = taxableAmount;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public static SalesInvoice toObject(String json) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		try {
			return mapper.readValue(json, SalesInvoice.class);
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
