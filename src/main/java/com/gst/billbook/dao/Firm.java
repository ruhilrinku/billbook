package com.gst.billbook.dao;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="firm_details")
public class Firm {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="firm_id")
	private int firmId;
	
	@Column(name="gstn")
	private String gstn;
	
	@Column(name="legal_name")
	private String legalName;
	
	@Column(name="trade_name")
	private String tradeName;
	
	@Column(name="business_desc")
	private String businessDesc;
	
	@Column(name="business_address")
	private String businessAddress;
	
	@Column(name="contact_no")
	private long contactNo;
	
	@Column(name="email")
	private String email;
	
	@Column(name="bank_name")
	private String bankName;
	
	@Column(name="bank_account_number")
	private String accountNumber;
	
	@Column(name="bank_ifsc_code")
	private String ifscCode;
	
	@Column(name="registration_date")
	private Date registrationDate;
	
	@Column(name="notes")
	public String notes;
	
	@Column(name="terms_and_conditions")
	public String termsAndConditions;
	
	@Column(name="bank_account_holder")
	private String accountHolderName;
	
	@Column(name="bank_address")
	private String bankAddress;
	
	@Column(name="state_code")
	private int stateCode;
	
	public int getStateCode() {
		return stateCode;
	}

	public void setStateCode(int stateCode) {
		this.stateCode = stateCode;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getTermsAndConditions() {
		return termsAndConditions;
	}

	public void setTermsAndConditions(String termsAndConditions) {
		this.termsAndConditions = termsAndConditions;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Firm() {}
	
	public int getFirmId() {
		return firmId;
	}

	public void setFirmId(int firmId) {
		this.firmId = firmId;
	}

	public String getGstn() {
		return gstn;
	}

	public void setGstn(String gstn) {
		this.gstn = gstn;
	}

	public String getLegalName() {
		return legalName;
	}

	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public String getBusinessDesc() {
		return businessDesc;
	}

	public void setBusinessDesc(String businessDesc) {
		this.businessDesc = businessDesc;
	}

	public String getBusinessAddress() {
		return businessAddress;
	}

	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}

	public long getContactNo() {
		return contactNo;
	}

	public void setContactNo(long contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public String getBankAddress() {
		return bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}
}
