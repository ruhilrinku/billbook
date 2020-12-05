package com.gst.billbook.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class InvoiceDetailsModel implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int invoiceNumber;
	
	private Date invoiceDate;
	private String sellerGstn;
	private String buyerGstn;
	private String buyerName;
	private String buyerAddress;
	private String buyerDeliveryAddress;
	private String placeOfSupply;
	private double totalAmount;
	private double amountPaid;
	private double amountDue;
	private Date updateDate;
	private double discount;
	private double taxableAmount;
	private Set<InvoiceItem> items;
	private Set<InvoiceTransaction> transactions;
	
	public InvoiceDetailsModel() {}
	
	public double getTaxableAmount() {
		return taxableAmount;
	}
	public void setTaxableAmount(double taxableAmount) {
		this.taxableAmount = taxableAmount;
	}

	public Set<InvoiceItem> getItems() {
		return items;
	}
	public void setItems(Set<InvoiceItem> items) {
		this.items = items;
	}
	public Set<InvoiceTransaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(Set<InvoiceTransaction> transactions) {
		this.transactions = transactions;
	}
	
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public String getSellerGstn() {
		return sellerGstn;
	}
	public void setSellerGstn(String sellerGstn) {
		this.sellerGstn = sellerGstn;
	}
	public String getBuyerGstn() {
		return buyerGstn;
	}
	public void setBuyerGstn(String buyerGstn) {
		this.buyerGstn = buyerGstn;
	}
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	public String getBuyerAddress() {
		return buyerAddress;
	}
	public void setBuyerAddress(String buyerAddress) {
		this.buyerAddress = buyerAddress;
	}
	public String getBuyerDeliveryAddress() {
		return buyerDeliveryAddress;
	}
	public void setBuyerDeliveryAddress(String buyerDeliveryAddress) {
		this.buyerDeliveryAddress = buyerDeliveryAddress;
	}
	public String getPlaceOfSupply() {
		return placeOfSupply;
	}
	public void setPlaceOfSupply(String placeOfSupply) {
		this.placeOfSupply = placeOfSupply;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}
	public double getAmountDue() {
		return amountDue;
	}
	public void setAmountDue(double amountDue) {
		this.amountDue = amountDue;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	public int getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(int invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	
	public static InvoiceDetailsModel toObject(String json) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		try {
			return mapper.readValue(json, InvoiceDetailsModel.class);
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
