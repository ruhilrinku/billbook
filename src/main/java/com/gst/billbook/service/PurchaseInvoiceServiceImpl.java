package com.gst.billbook.service;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gst.billbook.dao.PurchaseInvoice;
import com.gst.billbook.dao.PurchaseInvoiceItems;
import com.gst.billbook.dao.PurchaseInvoicePaymentSummary;
import com.gst.billbook.model.PurchaseInvoiceItemModel;
import com.gst.billbook.model.PurchaseInvoiceModel;
import com.gst.billbook.model.PurchaseInvoicePaymentModel;
import com.gst.billbook.repository.PurchaseInvoiceRepository;

@Service
public class PurchaseInvoiceServiceImpl implements PurchaseInvoiceService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseInvoiceServiceImpl.class);
	
	@Autowired
	private PurchaseInvoiceRepository purchaseInvoiceRepo;
	
	@Override
	public PurchaseInvoiceModel getPurchaseInvoiceDetails(int purchaseInvoiceNumber) {
		PurchaseInvoiceModel invoiceModel = null;
		PurchaseInvoice puchsaseInvoice = null;
		try {
			puchsaseInvoice = purchaseInvoiceRepo.getOne(purchaseInvoiceNumber);
			invoiceModel = convertDAOtoModel(puchsaseInvoice);
		} catch(Exception e) {
			LOGGER.error("Exception while Fetching Purchase invoice details: ", e);
			throw e;
		}
		return invoiceModel;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int savePurchaseInvoiceDetails(PurchaseInvoiceModel purchaseInvoiceModel) {
		PurchaseInvoice purchaseInvoice = null;
		try {
			purchaseInvoice = converModelToDAO(purchaseInvoiceModel);
			purchaseInvoice = purchaseInvoiceRepo.save(purchaseInvoice);
		} catch(Exception e) {
			LOGGER.error("Exception while Saving Purchase invoice details: ", e);
			throw e;
		}
		return purchaseInvoice.getPurchaseInvoiceNumber();
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int updatePurchaseInvoiceDetails(int purchaseInvoiceNumber, PurchaseInvoice purchaseInvoice) {
		return 0;
	}

	@Override
	public Set<PurchaseInvoicePaymentModel> getPurchaseInvoicePayments(int purchaseInvoiceNumber) {
		
		Set<PurchaseInvoicePaymentModel> payments = new HashSet<PurchaseInvoicePaymentModel>();
		try {
			Set<PurchaseInvoicePaymentSummary> paymentSummarys = purchaseInvoiceRepo.getPurchaseInvoicePayments(purchaseInvoiceNumber);
			for(PurchaseInvoicePaymentSummary summary: paymentSummarys) {
				PurchaseInvoicePaymentModel payment = new PurchaseInvoicePaymentModel();
				payment.setAmountPaid(summary.getAmountPaid());
				payment.setPaymentMode(summary.getPaymentMode());
				payment.setPaymentDate(summary.getPaymentDate());
				payment.setTransactionReference(summary.getTransactionReference());
				payment.setChequeClearanceDate(summary.getChequeClearanceDate());
				payment.setChequeNumber(summary.getChequeNumber());
				payment.setChequeDate(summary.getChequeDate());
				
				payments.add(payment);
			}
			
		} catch (Exception e) {
			LOGGER.error("Exception while Fetching Purchase invoice Payments details: ", e);
		}
		return payments;
	}

	@Override
	public Set<PurchaseInvoiceItemModel> getPurchaseInvoiceItems(int purchaseInvoiceNumber) {
		Set<PurchaseInvoiceItemModel> purchaseItems = new HashSet<PurchaseInvoiceItemModel>();
		Set<PurchaseInvoiceItems> items = null;
		try {
		items = purchaseInvoiceRepo.getPurchaseInvoiceItems(purchaseInvoiceNumber);
		
		for(PurchaseInvoiceItems item: items) {
			PurchaseInvoiceItemModel purchaseInvoiceItem = new PurchaseInvoiceItemModel();
			purchaseInvoiceItem.setItemCode(item.getItemCode());
			purchaseInvoiceItem.setItemRate(item.getItemRate());
			purchaseInvoiceItem.setItemQuantity(item.getItemQuantity());
			purchaseInvoiceItem.setIgst(item.getIgst());
			purchaseInvoiceItem.setCgst(item.getCgst());
			purchaseInvoiceItem.setSgst(item.getSgst());
			
			purchaseItems.add(purchaseInvoiceItem);
		}
		} catch (Exception e) {
			LOGGER.error("Exception while Fetching Purchase invoice item details: ", e);
			throw e;
		}
		return purchaseItems;
	}
	
	private PurchaseInvoiceModel convertDAOtoModel(PurchaseInvoice puchsaseInvoice) {
		PurchaseInvoiceModel invoiceModel = new PurchaseInvoiceModel();
		try {
			invoiceModel.setPurchaseInvoiceNumber(puchsaseInvoice.getPurchaseInvoiceNumber());
			invoiceModel.setPurchaseDate(puchsaseInvoice.getPurchaseDate());
			
			invoiceModel.setSupplierGstn(puchsaseInvoice.getSupplierGstn());
			invoiceModel.setSupplierName(puchsaseInvoice.getSupplierName());
			invoiceModel.setSupplierContact(puchsaseInvoice.getSupplierContact());
			invoiceModel.setSupplierEmail(puchsaseInvoice.getSupplierEmail());
			invoiceModel.setSupplierAddress(puchsaseInvoice.getSupplierAddress());
			
			invoiceModel.setBuyerGstn(puchsaseInvoice.getBuyerGstn());
			invoiceModel.setDeliveryAddress(puchsaseInvoice.getDeliveryAddress());
			invoiceModel.setUpdatedDate(puchsaseInvoice.getUpdatedDate());
						
			Set<PurchaseInvoiceItemModel> purchaseItems = new HashSet<PurchaseInvoiceItemModel>();
			Set<PurchaseInvoiceItems> items = puchsaseInvoice.getPurchaseInvoiceItems();
			
			for(PurchaseInvoiceItems item: items) {
				PurchaseInvoiceItemModel purchaseInvoiceItem = new PurchaseInvoiceItemModel();
				purchaseInvoiceItem.setItemCode(item.getItemCode());
				purchaseInvoiceItem.setItemRate(item.getItemRate());
				purchaseInvoiceItem.setItemQuantity(item.getItemQuantity());
				purchaseInvoiceItem.setIgst(item.getIgst());
				purchaseInvoiceItem.setCgst(item.getCgst());
				purchaseInvoiceItem.setSgst(item.getSgst());
				
				purchaseItems.add(purchaseInvoiceItem);
			}
			
			invoiceModel.setPurchaseInvoiceItems(purchaseItems);
			
			Set<PurchaseInvoicePaymentModel> payments = new HashSet<PurchaseInvoicePaymentModel>();
			Set<PurchaseInvoicePaymentSummary> paymentSummarys = puchsaseInvoice.getPurchaseInvoiceTransactionSummary();
			
			for(PurchaseInvoicePaymentSummary summary: paymentSummarys) {
				PurchaseInvoicePaymentModel payment = new PurchaseInvoicePaymentModel();
				payment.setAmountPaid(summary.getAmountPaid());
				payment.setPaymentMode(summary.getPaymentMode());
				payment.setPaymentDate(summary.getPaymentDate());
				payment.setTransactionReference(summary.getTransactionReference());
				payment.setChequeClearanceDate(summary.getChequeClearanceDate());
				payment.setChequeNumber(summary.getChequeNumber());
				payment.setChequeDate(summary.getChequeDate());
				
				payments.add(payment);
			}
			
			invoiceModel.setPurchaseInvoiceTransactionSummary(payments);
			
			invoiceModel.setTaxableAmount(puchsaseInvoice.getTaxableAmount());
			invoiceModel.setTotalAmount(puchsaseInvoice.getTotalAmount());
			
			invoiceModel.setAmountDue(puchsaseInvoice.getAmountDue());
			
		} catch(Exception ex) {
			LOGGER.error("Not able to convert purchase invoice details to Model: ", ex);
			throw ex;
		}
		
		return invoiceModel;
	}
	
	private PurchaseInvoice converModelToDAO(PurchaseInvoiceModel invoiceModel) {
		PurchaseInvoice details = new PurchaseInvoice();
		try {
			details.setPurchaseInvoiceNumber(invoiceModel.getPurchaseInvoiceNumber());
			details.setPurchaseDate(invoiceModel.getPurchaseDate());
			
			details.setSupplierGstn(invoiceModel.getSupplierGstn());
			details.setSupplierName(invoiceModel.getSupplierName());
			details.setSupplierContact(invoiceModel.getSupplierContact());
			details.setSupplierEmail(invoiceModel.getSupplierEmail());
			details.setSupplierAddress(invoiceModel.getSupplierAddress());
			
			details.setBuyerGstn(invoiceModel.getBuyerGstn());
			details.setDeliveryAddress(invoiceModel.getDeliveryAddress());
			details.setUpdatedDate(invoiceModel.getUpdatedDate());
			
			Set<PurchaseInvoiceItems> items = new HashSet<PurchaseInvoiceItems>();
			Set<PurchaseInvoiceItemModel> purchaseItems = invoiceModel.getPurchaseInvoiceItems();
			
			for(PurchaseInvoiceItemModel item: purchaseItems) {
				PurchaseInvoiceItems purchaseInvoiceItem = new PurchaseInvoiceItems();
				purchaseInvoiceItem.setItemCode(item.getItemCode());
				purchaseInvoiceItem.setItemRate(item.getItemRate());
				purchaseInvoiceItem.setItemQuantity(item.getItemQuantity());
				purchaseInvoiceItem.setIgst(item.getIgst());
				purchaseInvoiceItem.setCgst(item.getCgst());
				purchaseInvoiceItem.setSgst(item.getSgst());
				
				items.add(purchaseInvoiceItem);
			}
			
			details.setPurchaseInvoiceItems(items);
			
			Set<PurchaseInvoicePaymentSummary> paymentSummarys = new HashSet<PurchaseInvoicePaymentSummary>();
			Set<PurchaseInvoicePaymentModel> payments= invoiceModel.getPurchaseInvoiceTransactionSummary();
			
			for(PurchaseInvoicePaymentModel payment: payments) {
				PurchaseInvoicePaymentSummary paymentSummary = new PurchaseInvoicePaymentSummary();
				paymentSummary.setAmountPaid(payment.getAmountPaid());
				paymentSummary.setPaymentMode(payment.getPaymentMode());
				paymentSummary.setPaymentDate(payment.getPaymentDate());
				paymentSummary.setTransactionReference(payment.getTransactionReference());
				paymentSummary.setChequeClearanceDate(payment.getChequeClearanceDate());
				paymentSummary.setChequeNumber(payment.getChequeNumber());
				paymentSummary.setChequeDate(payment.getChequeDate());
				
				paymentSummarys.add(paymentSummary);
			}
			
			details.setPurchaseInvoiceTransactionSummary(paymentSummarys);
			
			details.setTaxableAmount(invoiceModel.getTaxableAmount());
			details.setTotalAmount(invoiceModel.getTotalAmount());
			
			details.setAmountPaid(invoiceModel.getAmountPaid());
			details.setAmountDue(invoiceModel.getTotalAmount() - invoiceModel.getAmountPaid());
			
		} catch(Exception ex) {
			LOGGER.error("Not able to convert purchase invoice model to dao: ", ex);
			throw ex;
		}
		return details;
	}

}
