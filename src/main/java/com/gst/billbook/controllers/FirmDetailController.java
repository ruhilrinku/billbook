package com.gst.billbook.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gst.billbook.model.FirmDetails;
import com.gst.billbook.service.FirmDetailService;
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
public class FirmDetailController {
	
	private static final Logger logger = LogManager.getLogger(FirmDetailController.class);
	
	@Autowired
	private FirmDetailService firmDetailService;
	
	@GetMapping(path = "/firm/{firmId}", produces = "application/json")
	public ResponseEntity<FirmDetails> getFirmDetails(@PathVariable("firmId") int firmId) {
		FirmDetails firmDetails = null;
		try {
			firmDetails = firmDetailService.getFirmDetails(firmId);
			return new ResponseEntity<FirmDetails>(firmDetails, HttpStatus.OK);
		} catch(Exception ex) {
			logger.error("Failed to Fetch Firm details by id: "+firmId, ex);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(path="/firm/save", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Integer> saveFirmDetails(@RequestBody String json) {
		FirmDetails firmDetails = null;
		try {
			firmDetails = FirmDetails.toObject(json);
			int firmId = firmDetailService.saveFirmDetais(firmDetails);
			return new ResponseEntity<>(firmId, HttpStatus.OK);
		} catch(Exception ex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(path="/firm/update/{firmId}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Boolean> updateFirmDetails(@PathVariable("firmId") int firmId, @RequestBody String json) {
		FirmDetails firmDetails = null;
		try {
			firmDetails = FirmDetails.toObject(json);
			boolean updated = firmDetailService.updateFirmDetais(firmId, firmDetails);
			return new ResponseEntity<>(updated, HttpStatus.OK);
		} catch(Exception ex) {
			return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
