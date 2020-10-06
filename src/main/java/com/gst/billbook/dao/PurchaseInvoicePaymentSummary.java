package com.gst.billbook.dao;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="purchase_invoice_payment_summary")
public class PurchaseInvoicePaymentSummary {

	@Id
	private int id;
	
	@Column(name="payment_mode")
	private String paymentMode;
	
	@Column(name="payment_date")
	private Date paymentDate;
	
	@Column(name="amount_paid")
	private double amountPaid;
	
	@Column(name="cheque_number")
	private String chequeNumber;
	
	@Column(name="cheque_date")
	private Date chequeDate;
	
	@Column(name="cheque_clearance_date")
	private Date chequeClearanceDate;
	
	@Column(name="transaction_ref")
	private String transactionReference;
	
	@ManyToOne
	@JoinColumn(name="purchase_invoice_number")
	private PurchaseInvoice purchaseInvoice;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public PurchaseInvoice getPurchaseInvoice() {
		return purchaseInvoice;
	}

	public void setPurchaseInvoice(PurchaseInvoice purchaseInvoice) {
		this.purchaseInvoice = purchaseInvoice;
	}
	
}
