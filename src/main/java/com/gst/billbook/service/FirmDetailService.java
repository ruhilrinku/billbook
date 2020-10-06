package com.gst.billbook.service;

import java.util.List;

import com.gst.billbook.model.FirmDetails;

public interface FirmDetailService {
	
	public List<FirmDetails> getFirmDetails();
	
	public FirmDetails getFirmDetails(int firmId);
	
	public int saveFirmDetais(FirmDetails firmDetails);
	
	public boolean updateFirmDetais(int firmId, FirmDetails firmDetails);
	
	public boolean removeFirmDetais(int firmId);
}
