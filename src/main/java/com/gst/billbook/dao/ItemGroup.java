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
@Table(name="item_group")
public class ItemGroup implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_seq")
	@GenericGenerator(
	        name = "group_seq", 
	        strategy = "com.gst.billbook.dao.sequenceGenerator.StringPrefixedSequenceGenerator", 
	        parameters = {@Parameter(name = StringPrefixedSequenceGenerator.VALUE_PREFIX_PARAMETER, value = "GRP-")})
	@Column(name="group_code")
	private String groupCode;
	
	@Column(name="group_title")
	private String title;
	
	@Column(name="group_description")
	private String description;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "item_group_items_map", joinColumns = { @JoinColumn(name = "group_code") }, inverseJoinColumns = { @JoinColumn(name = "item_code") })
	private Set<Item> items;
	
	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
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
	
	public static ItemGroup toObject(String json) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		try {
			return mapper.readValue(json, ItemGroup.class);
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
