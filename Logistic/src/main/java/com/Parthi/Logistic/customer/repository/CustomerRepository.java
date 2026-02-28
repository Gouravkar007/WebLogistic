package com.parthi.logistic.customer.repository;


import org.springframework.data.repository.CrudRepository;

import com.parthi.logistic.customer.model.Customer;

import java.util.Optional;



public interface CustomerRepository extends CrudRepository<Customer, String> {
    
    Optional<Customer> findFirstByCustomerNumber(String customerNumber);

}
    



