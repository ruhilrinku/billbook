package com.gst.billbook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gst.billbook.dao.Firm;
import com.gst.billbook.model.FirmDetails;
import com.gst.billbook.repository.FirmRepository;
import com.gst.billbook.repository.InvoiceDetailsRepository;

@Service
public class FirmDetailServiceImpl implements FirmDetailService {
	
	@Autowired
	private FirmRepository firmRepo;
	
	@Autowired
	private InvoiceDetailsRepository invoiceDetailRepo;

	@Override
	public List<FirmDetails> getFirmDetails() {
		return null;
	}

	@Override
	public FirmDetails getFirmDetails(int firmId) {
		FirmDetails firmDetails = null;
		try {
			Firm firm = firmRepo.getById(firmId);
			int invoiceNum = invoiceDetailRepo.nextInvoiceNum();
			if(firm !=null) {
				firmDetails = new FirmDetails();
				firmDetails.setInvoiceNumber(invoiceNum);
				firmDetails.setTradeName(firm.getTradeName());
				firmDetails.setGstn(firm.getGstn());
				firmDetails.setEmail(firm.getEmail());
				firmDetails.setBusinessDesc(firm.getBusinessDesc());
				firmDetails.setBusinessAddress(firm.getBusinessAddress());
				firmDetails.setLegalName(firm.getLegalName());
				firmDetails.setContactNo(firm.getContactNo());
				firmDetails.setStateCode(firm.getStateCode());
				firmDetails.setNotes(firm.getNotes());
				firmDetails.setTermAndConditions(firm.getTermsAndConditions());
			}
		} catch(Exception ex) {
			throw ex;
		}
		return firmDetails;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int saveFirmDetais(FirmDetails firmDetails) {
		Firm firm = new Firm();
		try {
			firm.setTradeName(firmDetails.getTradeName());
			firm.setGstn(firmDetails.getGstn());
			firm.setEmail(firmDetails.getEmail());
			firm.setBusinessDesc(firmDetails.getBusinessDesc());
			firm.setBusinessAddress(firmDetails.getBusinessAddress());
			firm.setLegalName(firmDetails.getLegalName());
			firm.setStateCode(firmDetails.getStateCode());
			firm.setContactNo(firmDetails.getContactNo());
			firm.setNotes(firmDetails.getNotes());
			firm.setTermsAndConditions(firmDetails.getTermAndConditions());
			firm = firmRepo.save(firm);
			
		} catch(Exception ex) {
			throw ex;
		}
		
		return firm.getFirmId();
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean updateFirmDetais(int firmId, FirmDetails firmDetails) {
		Firm firm = null;
		try {
			firm = firmRepo.getFirmDetailsByLegalName(firmId, firmDetails.getLegalName());
			firm.setTradeName(firmDetails.getTradeName());
			firm.setLegalName(firmDetails.getLegalName());
			firm.setGstn(firmDetails.getGstn());
			firm.setEmail(firmDetails.getEmail());
			firm.setBusinessDesc(firmDetails.getBusinessDesc());
			firm.setBusinessAddress(firmDetails.getBusinessAddress());
			firm.setNotes(firmDetails.getNotes());
			firm.setTermsAndConditions(firmDetails.getTermAndConditions());
			firmDetails.setContactNo(firm.getContactNo());
			firmDetails.setStateCode(firm.getStateCode());
			
			firmRepo.save(firm);
			
		} catch(Exception ex) {
			throw ex;
		}
		return true;
	}
	
	@Transactional
	@Override
	public boolean removeFirmDetais(int firmId) {
		try {
			firmRepo.deleteById(firmId);
		} catch(Exception ex) {
			throw ex;
		}
		return false;
	}

}
