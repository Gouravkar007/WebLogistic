package com.Parthi.Logistic.customer.service;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.Parthi.Logistic.Constants;
import com.Parthi.Logistic.customer.model.Customer;
import com.Parthi.Logistic.customer.repository.CustomerRepository;


public class CustomerService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    CustomerRepository customerRepository = new CustomerRepository();
    

    /**
     * This method take a customer number and retrieve the customer from the database.
     * 
     * @param String customer number with which the customer needs to be found
     * @return Returns the customer
     * @throws RuntimeException if the customer is unavailable in the database.
     */
    public Customer getCustomer(String customerNumber) {

        try {
            Customer customer = customerRepository.getCustomer(customerNumber);
            if (customer == null) {
                logger.warn("Customer with number:  " + customerNumber + "not found in the list");
            } else {
                logger.warn("Customer with number:  " + customerNumber + "found in the list");
            }
            return customer;

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
                response = customerRepository.addCustomer(customer);
            } else {
                response = "The customer with number: " + customer.getCustomerNumber() + "already exsits";
            }
        } catch (RuntimeException e) {
            response = e.getLocalizedMessage();
            logger.info("Exception occured while adding customer: " + e.getLocalizedMessage());
        }
        return response;
    }

    
    /**
     * This method take a customer contact number and update any change to the database.
     * 
     * @param Customer and contact number that needs to be updated to the database
     * @return Returns the choice of the user
     */
    public String updateCustomer(String contactNumber,Customer customer) {
        int index = getCustomerNumbers(contactNumber);
        if (index == Constants.NOT_FOUND) {
            return "Customer with number: " + contactNumber + "not found";
        }
        customerRepository.updateCustomer(index, customer);
        return "Customer updated";
      
        
    }
    
    public List<Customer> getCustomers(){
        return customerRepository.getListCustomers();
     }

     public int getCustomerNumbers(String customerNumber) {
        List<Customer> customers = getCustomers();
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getCustomerNumber().equals(customerNumber)) return i;
        }
        return Constants.NOT_FOUND;
    }

    public Customer getCustomerNumber(String number){
        int index = getCustomerNumbers(number);
        return index == Constants.NOT_FOUND ? new Customer() : getCustomer(number);
    }


}
