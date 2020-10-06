package com.gst.billbook.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="purchase_invoice_item_map")
public class PurchaseInvoiceItems {
	
	@Id
	private int id;
	
	@Column(name="item_code")
	private String itemCode;
	
	@Column(name="item_rate")
	private double itemRate;
	
	@Column(name="item_quantity")
	private int itemQuantity;
	
	@Column(name="igst")
	private double igst;
	
	@Column(name="cgst")
	private double cgst;
	
	@Column(name="sgst")
	private double sgst;
	
	@ManyToOne
	@JoinColumn(name="purchase_invoice_number")
	private PurchaseInvoice purchaseInvoice;
	
	public PurchaseInvoice getPurchaseInvoice() {
		return purchaseInvoice;
	}

	public void setPurchaseInvoice(PurchaseInvoice purchaseInvoice) {
		this.purchaseInvoice = purchaseInvoice;
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

	public double getItemRate() {
		return itemRate;
	}

	public void setItemRate(double itemRate) {
		this.itemRate = itemRate;
	}

	public int getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
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
}
