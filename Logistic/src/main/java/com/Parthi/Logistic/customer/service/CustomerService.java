package com.parthi.logistic.customer.service;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parthi.logistic.customer.model.Customer;
import com.parthi.logistic.customer.repository.CustomerRepository;


@Service
public class CustomerService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    CustomerRepository customerRepository;

    /**
     * This method take a customer number and retrieve the customer from the database.
     * 
     * @param String customer number with which the customer needs to be found
     * @return Returns the customer
     * @throws RuntimeException if the customer is unavailable in the database.
     */
    public Customer getCustomer(String customerNumber) {
        try {
            Optional<Customer> customer = customerRepository.findById(customerNumber);
            
            if (customer.isPresent()) {
                logger.info("Customer with number:  " + customerNumber + "found in the list");
               
            } else {
                 logger.warn("Customer with number:  " + customerNumber + "not found in the list");
            }
            return customer.get();

        } catch (RuntimeException e) {
            logger.error("Exception occured while retrieveing customer: " + e.getLocalizedMessage());
            return null;
        }

    }

    /**
     * This method take a customer details and add it to the database.
     * 
     * @param Customer that need to be added to the database
     * @return Returns the choice of the user
     */

    public String addCustomer(Customer customer) {
        String response = "";
        Customer tempCustomer = null;
        try {
            //Check customer phone number is already present or not
            tempCustomer = this.getCustomer(customer.getCustomerNumber());
            if (tempCustomer == null) {
                customerRepository.save(customer);
                response = "Customer added successfully";
            } else {
                response = "The customer with number: " + customer.getCustomerNumber() + "already exsits";
            }
        } catch (RuntimeException e) {
            response = e.getLocalizedMessage();
            logger.info("Exception occured while adding customer: " + e.getLocalizedMessage());
        }
        return response;
    }
}