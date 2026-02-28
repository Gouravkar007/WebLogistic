package com.parthi.logistic.transaction.service;

import java.time.LocalDate;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.parthi.logistic.common.model.PaymentMode;
import com.parthi.logistic.common.model.TransactionCategory;
import com.parthi.logistic.common.model.TransactionType;
import com.parthi.logistic.customer.model.Customer;
import com.parthi.logistic.customer.repository.CustomerRepository;
import com.parthi.logistic.payment.model.Payment;
import com.parthi.logistic.payment.service.PaymentService;
import com.parthi.logistic.product.Repository.ProductRepo;
import com.parthi.logistic.product.Service.ProductService;
import com.parthi.logistic.product.model.Product;
import com.parthi.logistic.transaction.model.Transaction;
import com.parthi.logistic.transaction.repository.TransactionRepository;

@Service
public class TransactionService {

	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

	@Autowired
	TransactionRepository transactionRepository;
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	ProductRepo productRepo;
	@Autowired
	PaymentService paymentService;
	@Autowired
	ProductService productService;

	/**
	 * This method take a Transaction details and add it to the database.
	 * 
	 * @param Transaction that need to be added to the database
	 * @return Returns the Response msg of the user
	 * @throws Exception
	 */

	public String addTransaction(Transaction transaction) throws Exception {
		String response = "";
		try {
			if (transaction.getTransactionDate().isAfter(LocalDate.now()))
				throw new Exception("Transaaction date can't be a future date");

			transactionRepository.save(transaction);
			response = "Transaction added successfully";
		} catch (Exception e) {
			response = e.getLocalizedMessage();
			logger.error("Exception occured while adding Transaction: " + e.getLocalizedMessage());
			throw e;
		}
		return response;

	}

	/**
	 * This method take a a transaction id and retrieve the transaction from the database.
	 * 
	 * @param int id with which the transaction needs to be found
	 * @return Returns a transaction
	 * @throws RuntimeException if the transaction is unavailable in the database.
	 */

	public Transaction getTransaction(int id) {
		try {
			Optional<Transaction> transaction = transactionRepository.findById(id);

			if (transaction.isPresent()) {
				logger.info("Transaction with id:  " + id + "found in the list");
			} else {
				logger.warn("Transaction with id: " + id + "not found");

			}
			return transaction.get();
		} catch (RuntimeException e) {
			logger.error("Exception occured while retrieveing transaction: " + e.getLocalizedMessage());
			return null;
		}
	}

	public String addSaleTransaction(Transaction transaction, Customer tmpCustomer, Double sellingPrice)
			throws Exception {
		String response = "";
		Customer customer = null;
		Product product = null;
		try {
			if (transaction.getTransactionDate().isAfter(LocalDate.now()))
				throw new Exception("Transaction date cannot be a future date");

			if (transaction.getTxnCategory().equalsIgnoreCase(TransactionCategory.SALES.toString())) {

				// add customer if the customer is new or update the deposite if required.
				Optional<Customer> optionalCustomer = customerRepository.findById(tmpCustomer.getCustomerNumber());
				if (optionalCustomer.isEmpty()) {
					customer = tmpCustomer;
					customerRepository.save(customer);

				} else {
					customer = optionalCustomer.get();
				}

				// Enter a transaction with debit the deposite amount in transaction
				if (customer.getDepositeAmount() > 0) {
					Transaction withdraw_deposite = new Transaction();

					double depositBalance = customer.getDepositeAmount();
					double txnAmount = transaction.getAmount();

					double deductAmount = Math.min(depositBalance, txnAmount);

					// transaction entry for deposit usage
					withdraw_deposite.setAmount(deductAmount);

					// update customer balance
					customer.setDepositeAmount(depositBalance - deductAmount);

					withdraw_deposite.setTransactionDate(LocalDate.now());
					withdraw_deposite.setDescription("Withdraw amount from deposte for " + transaction.getParticular()
							+ " by " + customer.getName());
					withdraw_deposite.setPaymentMode(PaymentMode.ONLINE.toString());
					withdraw_deposite.setTxnCategory(TransactionCategory.DEPOSITE.toString());
					withdraw_deposite.setTxnType(TransactionType.DEBIT.toString());
					addTransaction(withdraw_deposite);
				}
				customerRepository.save(customer);
			}

			// Update the product status to sold, scustomerelling price, checkout date
			if (sellingPrice == null) {
			    sellingPrice = transaction.getAmount(); // default behavior (same as old system)
			}
			productService.updateSoldProduct(transaction.getParticular(), transaction.getTransactionDate(),sellingPrice);

			// make an entry in payment
			Payment payment = new Payment();
			payment.setPaymentDate(transaction.getTransactionDate());
			payment.setPaidAmount(transaction.getAmount());
			payment.setCustomerNumber(customer);
			Optional<Product> optionalProduct = productRepo.findById(transaction.getParticular());

			if (optionalProduct.isEmpty())
				throw new Exception("Product not found");

			product = optionalProduct.get();

			payment.setProductId(product);
			paymentService.addPayment(payment);
			transactionRepository.save(transaction);
			response = "payment added successfully";

		} catch (Exception e) {
			response = e.getLocalizedMessage();
			logger.error("Exception occured while adding Transaction: " + e.getLocalizedMessage());
			throw e;
		}
		return response;
	}
}
