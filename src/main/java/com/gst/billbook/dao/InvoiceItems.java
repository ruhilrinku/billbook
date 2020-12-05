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
	
	@Column(name="item_name")
	private String itemName;
	
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
	
	@Column(name="total_amount")
	private double totalAmount;
	
	public InvoiceItems() {}
	
	public InvoiceItems(String itemCode, String itemName, int quantity, double itemRate, double igst, double cgst, double sgst,
			double totalAmount) {
		super();
		this.itemCode = itemCode;
		this.itemName = itemName;
		this.quantity = quantity;
		this.itemRate = itemRate;
		this.igst = igst;
		this.cgst = cgst;
		this.sgst = sgst;
		this.totalAmount = totalAmount;
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

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
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

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
}
