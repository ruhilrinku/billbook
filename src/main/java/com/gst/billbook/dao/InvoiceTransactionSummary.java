package com.gst.billbook.dao;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="invoice_transaction_summary")
public class InvoiceTransactionSummary {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoice_trans_generator")
	@SequenceGenerator(name="invoice_trans_generator", sequenceName = "invoice_trans_id_seq")
	private Integer id;
	
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
	
	public InvoiceTransactionSummary() {}

	public InvoiceTransactionSummary(String paymentMode, Date paymentDate, double amountPaid, String chequeNumber,
			Date chequeDate, Date chequeClearanceDate, String transactionReference) {
		super();
		this.paymentMode = paymentMode;
		this.paymentDate = paymentDate;
		this.amountPaid = amountPaid;
		this.chequeNumber = chequeNumber;
		this.chequeDate = chequeDate;
		this.chequeClearanceDate = chequeClearanceDate;
		this.transactionReference = transactionReference;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

}
