package com.gst.billbook.service;

import java.util.Set;

import com.gst.billbook.dao.PurchaseInvoice;
import com.gst.billbook.model.PurchaseInvoiceItemModel;
import com.gst.billbook.model.PurchaseInvoiceModel;
import com.gst.billbook.model.PurchaseInvoicePaymentModel;

public interface PurchaseInvoiceService {
	public PurchaseInvoiceModel getPurchaseInvoiceDetails(int purchaseInvoiceNumber);
	
	public int savePurchaseInvoiceDetails(PurchaseInvoiceModel purchaseInvoiceModel);
	
	public int updatePurchaseInvoiceDetails(int purchaseInvoiceNumber, PurchaseInvoice purchaseInvoice);
	
	public Set<PurchaseInvoicePaymentModel> getPurchaseInvoicePayments(int purchaseInvoiceNumber);
	
	public Set<PurchaseInvoiceItemModel> getPurchaseInvoiceItems(int purchaseInvoiceNumber);
}
