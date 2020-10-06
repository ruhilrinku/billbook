package com.gst.billbook.dao;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="purchase_invoice_details")
public class PurchaseInvoice {
	
	@Id
	@Column(name="purchase_invoice_number")
	private int purchaseInvoiceNumber;
	
	@Column(name="purchase_date")
	private Date purchaseDate;
	
	@Column(name="supplier_gstn")
	private String supplierGstn;
	
	@Column(name="supplier_name")
	private String supplierName;
	
	@Column(name="supplier_contact")
	private long supplierContact;
	
	@Column(name="supplier_email")
	private String supplierEmail;
	
	@Column(name="supplier_address")
	private String supplierAddress;
	
	@Column(name="delivery_address")
	private String deliveryAddress;
	
	@Column(name="buyer_gstn")
	private String buyerGstn;

	@Column(name="taxable_amount")
	private double taxableAmount;
	
	@Column(name="total_amount")
	private double totalAmount;
	
	@Column(name="amount_paid")
	private double amountPaid;
	
	@Column(name="amount_due")
	private double amountDue;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@OneToMany(mappedBy = "purchaseInvoice", cascade = CascadeType.ALL)
	private Set<PurchaseInvoiceItems> purchaseInvoiceItems;
	
	@OneToMany(mappedBy = "purchaseInvoice", cascade = CascadeType.ALL)
	private Set<PurchaseInvoicePaymentSummary> purchaseInvoiceTransactionSummary;
	
	public Set<PurchaseInvoicePaymentSummary> getPurchaseInvoiceTransactionSummary() {
		return purchaseInvoiceTransactionSummary;
	}

	public void setPurchaseInvoiceTransactionSummary(Set<PurchaseInvoicePaymentSummary> purchaseInvoiceTransactionSummary) {
		this.purchaseInvoiceTransactionSummary = purchaseInvoiceTransactionSummary;
	}

	public Set<PurchaseInvoiceItems> getPurchaseInvoiceItems() {
		return purchaseInvoiceItems;
	}

	public void setPurchaseInvoiceItems(Set<PurchaseInvoiceItems> purchaseInvoiceItems) {
		this.purchaseInvoiceItems = purchaseInvoiceItems;
	}

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

}
