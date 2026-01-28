/**
 * This is a customer class that provide the defination of the customer and provides empty constructor, field constructor and getter setters
 * thats required to maintain the customer details.
 *
 * @author Moumita Chatterjee
 * @since 2026-01-16
 */

package com.Parthi.Logistic.customer.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Customer {
    @NotBlank(message = "Id can't be blank")
    private String id;
    
    @NotBlank(message = "Number can't be blank")
    private String customerNumber;
    
    @NotBlank(message = "Name can't be blank")
    private String name;
  
    @NotBlank(message = "Address can't be blank")
    private String address;
   
    // NotBlank is used for String
    @NotNull(message = "DOB can't be blank")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    
    private double depositeAmount;

    //Empty constructor
    public Customer() {
    }

    //constructor with all feilds
    public Customer(String id, String customerNumber, String name, String address, LocalDate dateOfBirth,
            double depositeAmount) {
        this.id = id;
        this.customerNumber = customerNumber;
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.depositeAmount = depositeAmount;
    }

    //Getter and setter's
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerNumber() {
        return this.customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public double getDepositeAmount() {
        return this.depositeAmount;
    }

    public void setDepositeAmount(double depositeAmount) {
        this.depositeAmount = depositeAmount;
    }

}
