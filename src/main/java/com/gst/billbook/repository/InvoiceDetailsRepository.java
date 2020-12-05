package com.gst.billbook.repository;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gst.billbook.dao.InvoiceDetails;
import com.gst.billbook.dao.InvoiceItems;
import com.gst.billbook.dao.InvoiceTransactionSummary;
import com.gst.billbook.dao.State;

@Repository
public interface InvoiceDetailsRepository extends JpaRepository<InvoiceDetails, Integer> {
	
	@Query(nativeQuery = true, value = "select * from invoice_transaction_summary where invoice_number = ?1")
	public Set<InvoiceTransactionSummary> getInvoiceTransactions(int invoiceNumber);
	
	@Query(nativeQuery = true, value = "select * from invoice_item_map where invoice_number = ?1")
	public Set<InvoiceItems> getInvoiceItems(int invoiceNumber);
	
	@Query(nativeQuery = true, value = "select invoice_number, invoice_date, buyer_gstn, buyer_name, pos, taxable_amount, total_amount from invoice_details where invoice_date between ?1 and ?2")
	public List<Object[]> getInvoiceDetailForPeriod(Date invoiceStartDate, Date invoiceEndDate);
	
	@Query(nativeQuery = true, value = "select next_val from invoice_num_seq")
	public int nextInvoiceNum();
	
	@Query(nativeQuery = true, value = "Select * from state")
	public List<State> getStates();
}
