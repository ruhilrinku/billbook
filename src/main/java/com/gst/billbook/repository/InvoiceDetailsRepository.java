package com.gst.billbook.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gst.billbook.dao.InvoiceDetails;
import com.gst.billbook.dao.InvoiceItems;
import com.gst.billbook.dao.InvoiceTransactionSummary;

@Repository
public interface InvoiceDetailsRepository extends JpaRepository<InvoiceDetails, Integer> {
	
	@Query(nativeQuery = true, value = "select * from invoice_transaction_summary where invoice_number = ?1")
	public Set<InvoiceTransactionSummary> getInvoiceTransactions(int invoiceNumber);
	
	@Query(nativeQuery = true, value = "select * from invoice_item_map where invoice_number = ?1")
	public Set<InvoiceItems> getInvoiceItems(int invoiceNumber);
	
}