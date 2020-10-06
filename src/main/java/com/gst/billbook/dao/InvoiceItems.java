package com.gst.billbook.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="invoice_item_map")
public class InvoiceItems{
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoice_item_id_gen")
	@SequenceGenerator(name="invoice_item_id_gen", sequenceName = "invoice_item_id_seq")
	private int id;
		
	@Column(name="item_code")
	private String itemCode;
	
	@Column(name="item_qty")
	private int quantity;
	
	@Column(name="item_rate")
	private double itemRate;

	@Column(name="igst")
	private double igst;
	
	@Column(name="cgst")
	private double cgst;
	
	@Column(name="sgst")
	private double sgst;
	
	@Column(name="taxable_amount")
	private double taxableAmount;
	
	public InvoiceItems() {}
	
	public InvoiceItems(String itemCode, int quantity, double itemRate, double igst, double cgst, double sgst,
			double taxableAmount) {
		super();
		this.itemCode = itemCode;
		this.quantity = quantity;
		this.itemRate = itemRate;
		this.igst = igst;
		this.cgst = cgst;
		this.sgst = sgst;
		this.taxableAmount = taxableAmount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getIgst() {
		return igst;
	}

	public void setIgst(double igst) {
		this.igst = igst;
	}

	public double getCgst() {
		return cgst;
	}

	public void setCgst(double cgst) {
		this.cgst = cgst;
	}

	public double getSgst() {
		return sgst;
	}

	public void setSgst(double sgst) {
		this.sgst = sgst;
	}
	
	public double getItemRate() {
		return itemRate;
	}

	public void setItemRate(double itemRate) {
		this.itemRate = itemRate;
	}
	
	public double getTaxableAmount() {
		return taxableAmount;
	}

	public void setTaxableAmount(double taxableAmount) {
		this.taxableAmount = taxableAmount;
	}

}
