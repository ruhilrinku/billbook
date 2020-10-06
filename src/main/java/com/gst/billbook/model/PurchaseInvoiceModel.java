package com.gst.billbook.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class PurchaseInvoiceModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int purchaseInvoiceNumber;
	private Date purchaseDate;
	private String supplierGstn;
	private String supplierName;
	private long supplierContact;
	private String supplierEmail;
	private String supplierAddress;
	private String deliveryAddress;
	private String buyerGstn;
	private double taxableAmount;
	private double totalAmount;
	private double amountPaid;
	private double amountDue;
	private Date updatedDate;
	private Set<PurchaseInvoiceItemModel> purchaseInvoiceItems;
	private Set<PurchaseInvoicePaymentModel> purchaseInvoiceTransactionSummary;
	
	public int getPurchaseInvoiceNumber() {
		return purchaseInvoiceNumber;
	}
	public void setPurchaseInvoiceNumber(int purchaseInvoiceNumber) {
		this.purchaseInvoiceNumber = purchaseInvoiceNumber;
	}
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getSupplierGstn() {
		return supplierGstn;
	}
	public void setSupplierGstn(String supplierGstn) {
		this.supplierGstn = supplierGstn;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public long getSupplierContact() {
		return supplierContact;
	}
	public void setSupplierContact(long supplierContact) {
		this.supplierContact = supplierContact;
	}
	public String getSupplierEmail() {
		return supplierEmail;
	}
	public void setSupplierEmail(String supplierEmail) {
		this.supplierEmail = supplierEmail;
	}
	public String getSupplierAddress() {
		return supplierAddress;
	}
	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	public String getBuyerGstn() {
		return buyerGstn;
	}
	public void setBuyerGstn(String buyerGstn) {
		this.buyerGstn = buyerGstn;
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
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public Set<PurchaseInvoiceItemModel> getPurchaseInvoiceItems() {
		return purchaseInvoiceItems;
	}
	public void setPurchaseInvoiceItems(Set<PurchaseInvoiceItemModel> purchaseInvoiceItems) {
		this.purchaseInvoiceItems = purchaseInvoiceItems;
	}
	public Set<PurchaseInvoicePaymentModel> getPurchaseInvoiceTransactionSummary() {
		return purchaseInvoiceTransactionSummary;
	}
	public void setPurchaseInvoiceTransactionSummary(Set<PurchaseInvoicePaymentModel> purchaseInvoiceTransactionSummary) {
		this.purchaseInvoiceTransactionSummary = purchaseInvoiceTransactionSummary;
	}
	
	public static PurchaseInvoiceModel toObject(String json) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		try {
			return mapper.readValue(json, PurchaseInvoiceModel.class);
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
