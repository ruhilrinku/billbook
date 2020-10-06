package com.gst.billbook.service;

import java.util.Set;

import com.gst.billbook.model.InvoiceDetailsModel;
import com.gst.billbook.model.InvoiceItem;
import com.gst.billbook.model.InvoiceTransaction;

public interface InvoiceDetailsService {
	
	public InvoiceDetailsModel getInvoiceDetails(int invoiceNumber);
	
	public int saveInvoiceDetails(InvoiceDetailsModel invoiceDetails);
	
	public int updateInvoiceDetails(int invoiceNumber, InvoiceDetailsModel invoiceDetails);
	
	public Set<InvoiceTransaction> getInvoiceTransactions(int invoiceNumber);
	
	public Set<InvoiceItem> getInvoiceItems(int invoiceNumber);
}
