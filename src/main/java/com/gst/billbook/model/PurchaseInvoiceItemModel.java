package com.gst.billbook.model;

import java.io.Serializable;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class PurchaseInvoiceItemModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String itemCode;
	private double itemRate;
	private int itemQuantity;
	private double igst;
	private double cgst;
	private double sgst;
	
	public PurchaseInvoiceItemModel() {}
	
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
	

	public static PurchaseInvoiceItemModel toObject(String json) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		try {
			return mapper.readValue(json, PurchaseInvoiceItemModel.class);
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
