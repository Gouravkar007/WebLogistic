package com.parthi.logistic.customer.controller;

import org.springframework.web.bind.annotation.RestController;

import com.parthi.logistic.customer.model.Customer;
import com.parthi.logistic.customer.service.CustomerService;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController

public class CustomerController {

    @Autowired
    CustomerService customerService;

    /**
     * This API shows the details of a customer whose phone numer is entered
     * 
     * @param pathVariable is used to add phone number the the path
     * @return Returns a response entity ok with customer details if found and the
     *         list with customer details
     */
    @GetMapping("/getcustomer/number/{customerNumber}")
    // public ResponseEntity<?> getCustomerWithNumber(@RequestParam String
    // customerNumber) { ////also possible
    public ResponseEntity<?> getCustomerWithNumber(@PathVariable String customerNumber) {
        return ResponseEntity.ok(customerService.getCustomer(customerNumber));
    }

    /**
     * This API shows all the customer present in the database
     * 
     * @return Returns a response entity ok, if found and the list with customer
     *         details
     */
    @PostMapping("/addcustomer")
    public ResponseEntity<?> submitCustomerDetail(@Valid @RequestBody Customer customer, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getFieldError().getDefaultMessage());
        }
        return ResponseEntity.ok(customerService.addCustomer(customer));
    }

}
