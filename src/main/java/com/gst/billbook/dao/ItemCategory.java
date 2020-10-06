package com.gst.billbook.dao;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.gst.billbook.dao.sequenceGenerator.StringPrefixedSequenceGenerator;

@Entity
@Table(name="item_category")
public class ItemCategory implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq")
	@GenericGenerator(
	        name = "category_seq", 
	        strategy = "com.gst.billbook.dao.sequenceGenerator.StringPrefixedSequenceGenerator", 
	        parameters = {@Parameter(name = StringPrefixedSequenceGenerator.VALUE_PREFIX_PARAMETER, value = "CAT-")})
	@Column(name="category_code")
	private String categoryCode;
	
	@Column(name="category_title")
	private String title;
	
	@Column(name="category_description")
	private String description;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "item_category_item_group_map", 
				joinColumns = { @JoinColumn(name = "category_code") }, 
				inverseJoinColumns = { @JoinColumn(name = "group_code") })
	private Set<ItemGroup> itemGroups;
	
	public Set<ItemGroup> getItemGroups() {
		return itemGroups;
	}

	public void setItemGroups(Set<ItemGroup> itemGroups) {
		this.itemGroups = itemGroups;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public static ItemCategory toObject(String json) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		try {
			return mapper.readValue(json, ItemCategory.class);
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
