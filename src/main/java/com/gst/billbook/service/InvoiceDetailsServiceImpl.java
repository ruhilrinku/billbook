package com.gst.billbook.service;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gst.billbook.dao.InvoiceDetails;
import com.gst.billbook.dao.InvoiceItems;
import com.gst.billbook.dao.InvoiceTransactionSummary;
import com.gst.billbook.model.InvoiceDetailsModel;
import com.gst.billbook.model.InvoiceItem;
import com.gst.billbook.model.InvoiceTransaction;
import com.gst.billbook.repository.InvoiceDetailsRepository;

@Service
public class InvoiceDetailsServiceImpl implements InvoiceDetailsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(InvoiceDetailsServiceImpl.class);
	
	@Autowired
	private InvoiceDetailsRepository invoiceDetailsRepo;

	@Override
	public InvoiceDetailsModel getInvoiceDetails(int invoiceNumber) {
		InvoiceDetails invoiceDetails = null;
		InvoiceDetailsModel detailModel = null;
		try {
			invoiceDetails = invoiceDetailsRepo.getOne(invoiceNumber);
			detailModel = convertDAOtoModel(invoiceDetails);
		} catch(Exception ex) {
			LOGGER.error("Not able to fetch Invoice details: ", ex);
			throw ex;
		}
		return detailModel;
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int saveInvoiceDetails(InvoiceDetailsModel invoiceDetails) {
		InvoiceDetails persistenceInvoiceDetails = null;
		try {
			persistenceInvoiceDetails = converModelToDAO(invoiceDetails);
			persistenceInvoiceDetails = invoiceDetailsRepo.save(persistenceInvoiceDetails);
		} catch(Exception ex) {
			LOGGER.error("Not able to save Invoice details to DB: ", ex);
			throw ex;
		}
		return persistenceInvoiceDetails.getInvoiceNumber();
	}
	
	@Transactional
	@Override
	public int updateInvoiceDetails(int invoiceNumber, InvoiceDetailsModel invoiceDetailsModel) {
		
		return 0;
	}
	
	private InvoiceDetailsModel convertDAOtoModel(InvoiceDetails details) {
		InvoiceDetailsModel invoiceDetailsModel = new InvoiceDetailsModel();
		try {
			invoiceDetailsModel.setInvoiceNumber(details.getInvoiceNumber());
			invoiceDetailsModel.setInvoiceDate(details.getInvoiceDate());
			invoiceDetailsModel.setBuyerAddress(details.getBuyerAddress());
			invoiceDetailsModel.setBuyerDeliveryAddress(details.getBuyerDeliveryAddress());
			invoiceDetailsModel.setBuyerGstn(details.getBuyerGstn());
			invoiceDetailsModel.setBuyerName(details.getBuyerName());
			
			invoiceDetailsModel.setDiscount(details.getDiscount());
			
			invoiceDetailsModel.setSellerGstn(details.getSellerGstn());
			invoiceDetailsModel.setNameOfState(details.getNameOfState());
			invoiceDetailsModel.setPlaceOfSupply(details.getPlaceOfSupply());
			invoiceDetailsModel.setTermAndConditions(details.getTermAndConditions());
			
			invoiceDetailsModel.setUpdateDate(details.getUpdateDate());
			
			Set<InvoiceItem> invoiceItems = new HashSet<InvoiceItem>();
			Set<InvoiceItems> items = details.getItems();
			
			for(InvoiceItems item: items) {
				InvoiceItem invoiceItem = new InvoiceItem();
				invoiceItem.setItemCode(item.getItemCode());
				invoiceItem.setItemRate(item.getItemRate());
				invoiceItem.setQuantity(item.getQuantity());
				invoiceItem.setIgst(item.getIgst());
				invoiceItem.setCgst(item.getCgst());
				invoiceItem.setSgst(item.getSgst());
				
				invoiceItems.add(invoiceItem);
			}
			
			invoiceDetailsModel.setItems(invoiceItems);
			
			Set<InvoiceTransaction> transactions = new HashSet<InvoiceTransaction>();
			Set<InvoiceTransactionSummary> transactionSummarys = details.getInvoiceTransactions();
			
			for(InvoiceTransactionSummary summary: transactionSummarys) {
				InvoiceTransaction transaction = new InvoiceTransaction();
				transaction.setAmountPaid(summary.getAmountPaid());
				transaction.setPaymentMode(summary.getPaymentMode());
				transaction.setPaymentDate(summary.getPaymentDate());
				transaction.setTransactionReference(summary.getTransactionReference());
				transaction.setChequeClearanceDate(summary.getChequeClearanceDate());
				transaction.setChequeNumber(summary.getChequeNumber());
				transaction.setChequeDate(summary.getChequeDate());
				
				transactions.add(transaction);
			}
			
			invoiceDetailsModel.setTransactions(transactions);
			
			invoiceDetailsModel.setTaxableAmount(details.getTaxableAmount());
			invoiceDetailsModel.setTotalAmount(details.getTotalAmount());
			invoiceDetailsModel.setAmountDue(details.getAmountDue());
			
		} catch(Exception ex) {
			LOGGER.error("Not able to convert Invoice details to Model: ", ex);
			throw ex;
		}
		
		return invoiceDetailsModel;
	}
	
	private InvoiceDetails converModelToDAO(InvoiceDetailsModel invoiceModel) {
		InvoiceDetails details = new InvoiceDetails();
		try {
			details.setInvoiceNumber(invoiceModel.getInvoiceNumber());
			details.setInvoiceDate(invoiceModel.getInvoiceDate());
			details.setBuyerAddress(invoiceModel.getBuyerAddress());
			details.setBuyerDeliveryAddress(invoiceModel.getBuyerDeliveryAddress());
			details.setBuyerGstn(invoiceModel.getBuyerGstn());
			details.setBuyerName(invoiceModel.getBuyerName());
			
			details.setDiscount(invoiceModel.getDiscount());
			
			details.setSellerGstn(invoiceModel.getSellerGstn());
			details.setNameOfState(invoiceModel.getNameOfState());
			details.setPlaceOfSupply(invoiceModel.getPlaceOfSupply());
			details.setTermAndConditions(invoiceModel.getTermAndConditions());
			
			details.setUpdateDate(invoiceModel.getUpdateDate());
			
			Set<InvoiceItems> invoiceItems = new HashSet<InvoiceItems>();
			Set<InvoiceItem> items = invoiceModel.getItems();
	
			double taxableAmount = 0;
			double totalTaxableAmount = 0;
			double totalAmount = 0;
			double gstAmount = 0;
	
			
			for(InvoiceItem item: items) {
				InvoiceItems invoiceItem = new InvoiceItems();
				invoiceItem.setItemCode(item.getItemCode());
				invoiceItem.setItemRate(item.getItemRate());
				invoiceItem.setQuantity(item.getQuantity());
				invoiceItem.setIgst(item.getIgst());
				invoiceItem.setCgst(item.getCgst());
				invoiceItem.setSgst(item.getSgst());
				
				//Taxable amount and GST Calculation
				taxableAmount = item.getQuantity() * item.getItemRate();
				invoiceItem.setTaxableAmount(taxableAmount);

				if(invoiceModel.getPlaceOfSupply().equals(invoiceModel.getNameOfState())) {
					gstAmount = taxableAmount * item.getCgst() + taxableAmount * item.getSgst();
				}
				else {
					gstAmount = taxableAmount * item.getIgst();
				}
				totalTaxableAmount = totalTaxableAmount + taxableAmount;
				totalAmount = totalAmount + taxableAmount + gstAmount;
				
				taxableAmount = 0;
				gstAmount = 0;
				
				invoiceItems.add(invoiceItem);
			}
			
			details.setTaxableAmount(totalTaxableAmount);
			details.setTotalAmount(totalAmount);
			details.setItems(invoiceItems);
			
			Set<InvoiceTransactionSummary> transactionSummarys = new HashSet<InvoiceTransactionSummary>();
			Set<InvoiceTransaction> transactions = invoiceModel.getTransactions();
			
			double totalAmountPaid = 0;
			for(InvoiceTransaction transaction: transactions) {
				InvoiceTransactionSummary summary = new InvoiceTransactionSummary();
				summary.setAmountPaid(transaction.getAmountPaid());
				summary.setPaymentMode(transaction.getPaymentMode());
				summary.setPaymentDate(transaction.getPaymentDate());
				summary.setTransactionReference(transaction.getTransactionReference());
				summary.setChequeClearanceDate(transaction.getChequeClearanceDate());
				summary.setChequeNumber(transaction.getChequeNumber());
				summary.setChequeDate(transaction.getChequeDate());
				
				totalAmountPaid = totalAmountPaid + transaction.getAmountPaid();
				
				transactionSummarys.add(summary);
			}
			
			details.setInvoiceTransactions(transactionSummarys);
			details.setAmountDue(totalAmount - totalAmountPaid);
			
		} catch(Exception ex) {
			LOGGER.error("Not able to convert Model to model: ", ex);
			throw ex;
		}
		return details;
	}

	@Override
	public Set<InvoiceTransaction> getInvoiceTransactions(int invoiceNumber) {
		Set<InvoiceTransaction> invoiceTransactionSet = new HashSet<InvoiceTransaction>();
		try {
			Set<InvoiceTransactionSummary> invoiceTransactions = invoiceDetailsRepo.getInvoiceTransactions(invoiceNumber);
			for(InvoiceTransactionSummary summary : invoiceTransactions) {
				InvoiceTransaction transaction = new InvoiceTransaction();
				transaction.setAmountPaid(summary.getAmountPaid());
				transaction.setPaymentMode(summary.getPaymentMode());
				transaction.setPaymentDate(summary.getPaymentDate());
				transaction.setTransactionReference(summary.getTransactionReference());
				transaction.setChequeClearanceDate(summary.getChequeClearanceDate());
				transaction.setChequeNumber(summary.getChequeNumber());
				transaction.setChequeDate(summary.getChequeDate());
				
				invoiceTransactionSet.add(transaction);
				
			}
		} catch (Exception e) {
			LOGGER.error("Exception while fetching Invoice Item details: ", e);
		}
		return invoiceTransactionSet;
	}

	@Override
	public Set<InvoiceItem> getInvoiceItems(int invoiceNumber) {
		Set<InvoiceItem> invoiceItemSet = new HashSet<InvoiceItem>();
		try {
			Set<InvoiceItems> invoiceItems = invoiceDetailsRepo.getInvoiceItems(invoiceNumber);
			for(InvoiceItems invItem: invoiceItems) {
				InvoiceItem item = new InvoiceItem();
				
				item.setItemCode(invItem.getItemCode());
				item.setItemRate(invItem.getItemRate());
				item.setQuantity(invItem.getQuantity());
				item.setIgst(invItem.getIgst());
				item.setCgst(invItem.getCgst());
				item.setSgst(invItem.getSgst());
				item.setTaxableAmount(invItem.getTaxableAmount());
				
				invoiceItemSet.add(item);
				
			}
		} catch (Exception e) {
			LOGGER.error("Exception while fetching Invoice Item details: ", e);
		}
		return invoiceItemSet;
	}

}
