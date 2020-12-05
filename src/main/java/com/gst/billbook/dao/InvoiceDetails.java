package com.gst.billbook.dao;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="invoice_details")
public class InvoiceDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoice_num_generator")
	@SequenceGenerator(name="invoice_num_generator", sequenceName = "invoice_num_seq")
	@Column(name="invoice_number")
	private int invoiceNumber;
	
	@Column(name="invoice_date", columnDefinition = "DATE NOT NULL DEFAULT (DATE(CURRENT_TIMESTAMP))")
	private Date invoiceDate;
	
	@Column(name="seller_gstn")
	private String gstn;
	
	@Column(name="buyer_gstn")
	private String buyerGstn;
	
	@Column(name="buyer_name")
	private String buyerName;
	
	@Column(name="buyer_address")
	private String buyerAddress;
	
	@Column(name="buyer_delivery_address")
	private String buyerDeliveryAddress;
		
	@Column(name="pos")
	private String placeOfSupply;
	
	@Column(name="total_amount")
	private double totalAmount;
	
	@Column(name="term_and_conditions")
	private String termAndConditions;
	
	@Column(name="amount_paid")
	private double amountPaid;
	
	@Column(name="amount_due")
	private double amountDue;
	
	@Column(name="updated_date", columnDefinition = "DATE DEFAULT (DATE(CURRENT_TIMESTAMP))")
	private Date updateDate;
	
	@Column(name="discount")
	private double discount;
	
	@Column(name="taxable_amount")
	private double taxableAmount;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="invoice_number", referencedColumnName = "invoice_number")
	private Set<InvoiceItems> items;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name="invoice_number", referencedColumnName = "invoice_number")
	private Set<InvoiceTransactionSummary> invoiceTransactions;
	
	public InvoiceDetails() {}
	
	public InvoiceDetails(Date invoiceDate, String gstn, String buyerGstn, String buyerName, String buyerAddress,
			String buyerDeliveryAddress, int stateCode, String placeOfSupply, double totalAmount,
			String termAndConditions, double amountPaid, double amountDue, Date updateDate, double discount,
			double taxableAmount) {
		super();
		this.invoiceDate = invoiceDate;
		this.gstn = gstn;
		this.buyerGstn = buyerGstn;
		this.buyerName = buyerName;
		this.buyerAddress = buyerAddress;
		this.buyerDeliveryAddress = buyerDeliveryAddress;
		this.placeOfSupply = placeOfSupply;
		this.totalAmount = totalAmount;
		this.termAndConditions = termAndConditions;
		this.amountPaid = amountPaid;
		this.amountDue = amountDue;
		this.updateDate = updateDate;
		this.discount = discount;
		this.taxableAmount = taxableAmount;
	}

	public Set<InvoiceTransactionSummary> getInvoiceTransactions() {
		return invoiceTransactions;
	}

	public void setInvoiceTransactions(Set<InvoiceTransactionSummary> invoiceTransactions) {
		this.invoiceTransactions = invoiceTransactions;
	}

	public Set<InvoiceItems> getItems() {
		return items;
	}

	public void setItems(Set<InvoiceItems> items) {
		this.items = items;
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

	public String getGstn() {
		return gstn;
	}

	public void setGstn(String gstn) {
		this.gstn = gstn;
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

	public String getTermAndConditions() {
		return termAndConditions;
	}

	public void setTermAndConditions(String termAndConditions) {
		this.termAndConditions = termAndConditions;
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
	
	public double getTaxableAmount() {
		return taxableAmount;
	}

	public void setTaxableAmount(double taxableAmount) {
		this.taxableAmount = taxableAmount;
	}
	
}
