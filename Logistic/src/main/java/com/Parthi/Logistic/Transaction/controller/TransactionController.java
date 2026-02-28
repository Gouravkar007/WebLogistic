package com.parthi.logistic.transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import com.parthi.logistic.customer.model.Customer;
import com.parthi.logistic.customer.repository.CustomerRepository;
import com.parthi.logistic.transaction.model.Transaction;
import com.parthi.logistic.transaction.service.TransactionService;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @Autowired
    CustomerRepository customerRepository;

    /**
     * This method retrieves a product based on the provided product id.
     * If the product is not found, it returns a NOT_FOUND response
     * with an appropriate message.
     *
     * @param id The unique identifier of the product
     * @return Returns the product if found, otherwise returns a not found message
     */

    @GetMapping("/transaction/{id}")
    public ResponseEntity<?> getTransactionId(@PathVariable int id) {

        Transaction transaction = transactionService.getTransaction(id);

        if (transaction == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("transaction with id" + id + "not found");
        }

        return ResponseEntity.ok(transaction);
    }

    /**
     * This API shows all the customer present in the database
     * 
     * @return Returns a response entity ok, if found and the list with customer
     *         details
     * @throws Exception
     */
    @PostMapping("/addtransaction")
    public ResponseEntity<?> submitTransactionDetail(@Valid @RequestBody Transaction transaction, BindingResult result)
            throws Exception {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getFieldError().getDefaultMessage());
        }
        return ResponseEntity.ok(transactionService.addTransaction(transaction));
    }

    @PostMapping("/addtransaction/sales")
    public ResponseEntity<?> submitSalesDetail(@Valid @RequestBody Transaction transaction, Customer tempCustomer,
            Double sellingPrice, BindingResult result) throws Exception {
        // TODO: process POST request
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getFieldError().getDefaultMessage());
        }
        return ResponseEntity.ok(transactionService.addSaleTransaction(transaction, tempCustomer, sellingPrice));
    }

}
