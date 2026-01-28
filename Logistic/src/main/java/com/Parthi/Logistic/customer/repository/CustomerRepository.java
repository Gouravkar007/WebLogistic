package com.parthi.logistic.customer.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.parthi.logistic.customer.model.Customer;

public class CustomerRepository {
    
    private static final Logger logger = LoggerFactory.getLogger(CustomerRepository.class);
    private List<Customer> customerList = new ArrayList<>();

    /* This method take a customer number and retrieve the customer from the list.
     * 
     * @param String customerNumber is the number which is needed to find the cutomer
     * @return Returns the customer number
     */
      public Customer getCustomer(String customerNumber) {
          logger.info("Getting cutomer with contact number" + customerNumber);
          return customerList.stream().filter(c -> c.getCustomerNumber().equals(customerNumber)).findFirst().orElse(null);
      }
    
     /**
     * This method take a customer details and add it to the List.
     * 
     * @param Customer that need to be added to the database
     * @return Returns the choice of the user
     * @throws RuntimeException if the customer is already available in the database.
     */
     public String addCustomer(Customer customer) throws RuntimeException {
         logger.info("adding customer to the list" + customer.toString());
         String response = "";
         customerList.add(customer);
         response = "Customer added successfully";
         logger.info("Customer with number: " + customer.getCustomerNumber() + "added to the list");

         return response;
     }

     /**
     * This method take a customer details and index number and check with the database.
     * 
     * @param Customer index that needs to be checked to the database
     * @return Returns responce
     * @throws RuntimeException if the customer is not available in the database.
     */
    public void updateCustomer(int index, Customer customer){
        customerList.set(index,customer);
    }

    public List<Customer> getListCustomers(){
        return customerList;
    }



}
