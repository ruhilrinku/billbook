package com.gst.billbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gst.billbook.dao.Firm;

@Repository
public interface FirmRepository extends JpaRepository<Firm, Integer> {
	
	@Query(nativeQuery = true, value = "select * from Firm_details where firm_id = ?1 and legal_name = ?2 ")
	public Firm getFirmDetailsByLegalName(int firmId, String legalName);
	
	@Query(nativeQuery = true, value = "select * from Firm_details where firm_id = ?1")
	public Firm getById(int firmId);
}
