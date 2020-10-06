package com.gst.billbook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gst.billbook.dao.Firm;
import com.gst.billbook.model.FirmDetails;
import com.gst.billbook.repository.FirmRepository;

@Service
public class FirmDetailServiceImpl implements FirmDetailService {
	
	@Autowired
	private FirmRepository firmRepo;

	@Override
	public List<FirmDetails> getFirmDetails() {
		return null;
	}

	@Override
	public FirmDetails getFirmDetails(int firmId) {
		FirmDetails firmDetails = new FirmDetails();
		try {
			Firm firm = firmRepo.getOne(firmId);
			firmDetails.setTradeName(firm.getTradeName());
			firmDetails.setLegalName(firm.getLegalName());
			firmDetails.setIfscCode(firm.getIfscCode());
			firmDetails.setAccountNumber(firm.getAccountNumber());
			firmDetails.setGstn(firm.getGstn());
			firmDetails.setEmail(firm.getEmail());
			firmDetails.setAccountNumber(firm.getAccountNumber());
			firmDetails.setBankName(firm.getBankName());
			firmDetails.setBusinessDesc(firm.getBusinessDesc());
			firmDetails.setBusinessAddress(firm.getBusinessAddress());
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
			firm.setLegalName(firmDetails.getLegalName());
			firm.setIfscCode(firmDetails.getIfscCode());
			firm.setAccountNumber(firmDetails.getAccountNumber());
			firm.setGstn(firmDetails.getGstn());
			firm.setEmail(firmDetails.getEmail());
			firm.setAccountNumber(firmDetails.getAccountNumber());
			firm.setBankName(firmDetails.getBankName());
			firm.setBusinessDesc(firmDetails.getBusinessDesc());
			firm.setBusinessAddress(firmDetails.getBusinessAddress());
			firm.setRegistrationDate(firmDetails.getRegistrationDate());
			
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
			firm.setIfscCode(firmDetails.getIfscCode());
			firm.setAccountNumber(firmDetails.getAccountNumber());
			firm.setGstn(firmDetails.getGstn());
			firm.setEmail(firmDetails.getEmail());
			firm.setAccountNumber(firmDetails.getAccountNumber());
			firm.setBankName(firmDetails.getBankName());
			firm.setBusinessDesc(firmDetails.getBusinessDesc());
			firm.setBusinessAddress(firmDetails.getBusinessAddress());
			
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
