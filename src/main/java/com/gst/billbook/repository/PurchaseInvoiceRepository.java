package com.gst.billbook.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gst.billbook.dao.PurchaseInvoice;
import com.gst.billbook.dao.PurchaseInvoiceItems;
import com.gst.billbook.dao.PurchaseInvoicePaymentSummary;

@Repository
public interface PurchaseInvoiceRepository extends JpaRepository<PurchaseInvoice, Integer> {
	
	@Query(nativeQuery = true, value = "select * from purchase_invoice_payment_summary where purchase_invoice_number = ?1")
	public Set<PurchaseInvoicePaymentSummary> getPurchaseInvoicePayments(int purchaseInvoiceNumber);
	
	@Query(nativeQuery = true, value = "select * from purchase_invoice_item_map where purchase_invoice_number = ?1")
	public Set<PurchaseInvoiceItems> getPurchaseInvoiceItems(int purchaseInvoiceNumber);
}
