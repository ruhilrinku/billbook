package com.gst.billbook.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.gst.billbook.dao.sequenceGenerator.StringPrefixedSequenceGenerator;

@Entity
@Table(name="item")
public class Item implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq")
	@GenericGenerator(
	        name = "item_seq", 
	        strategy = "com.gst.billbook.dao.sequenceGenerator.StringPrefixedSequenceGenerator", 
	        parameters = {@Parameter(name = StringPrefixedSequenceGenerator.VALUE_PREFIX_PARAMETER, value = "ITM-")})
	@Column(name="item_code")
	private String itemCode;
	
	@Column(name="item_name")
	private String itemName;
	
	@Column(name="item_type")
	private String itemType;
	
	@Column(name="item_desc")
	private String itemDesc;
	
	@Column(name="item_hsn")
	private String hsn;
	
	@Column(name="brand")
	private String brandName;
	
	@Column(name="available_quantity")
	private int availableQty;
	
	@Column(name="item_rate")
	private double itemRate;

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

	public double getItemRate() {
		return itemRate;
	}

	public void setItemRate(double itemRate) {
		this.itemRate = itemRate;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public String getHsn() {
		return hsn;
	}

	public void setHsn(String hsn) {
		this.hsn = hsn;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public int getAvailableQty() {
		return availableQty;
	}

	public void setAvailableQty(int availableQty) {
		this.availableQty = availableQty;
	}
	
	public static Item toObject(String json) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		try {
			return mapper.readValue(json, Item.class);
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
