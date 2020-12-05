package com.gst.billbook.service;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.gst.billbook.dao.State;
import com.gst.billbook.model.InvoiceDetailsModel;
import com.gst.billbook.model.InvoiceItem;
import com.gst.billbook.model.InvoiceTransaction;
import com.gst.billbook.model.SalesInvoice;

public interface InvoiceDetailsService {
	
	public List<InvoiceDetailsModel> getInvoices();
	
	public InvoiceDetailsModel getInvoiceDetails(int invoiceNumber);
	
	public int saveInvoiceDetails(InvoiceDetailsModel invoiceDetails);
	
	public int updateInvoiceDetails(int invoiceNumber, InvoiceDetailsModel invoiceDetails);
	
	public Set<InvoiceTransaction> getInvoiceTransactions(int invoiceNumber);
	
	public Set<InvoiceItem> getInvoiceItems(int invoiceNumber);
	
	public List<SalesInvoice> getInvoiceList(Date fromDate, Date toDate);
	
	public List<State> getStates();
	
}
