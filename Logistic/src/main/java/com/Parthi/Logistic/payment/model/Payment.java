
/**
 * This is a product class that provide the defination of the product and provides all the method
 * thats required to maintain the products.
 *
 * @author Moumita Chatterjee
 * @since 2026-01-25
 */
package com.Parthi.Logistic.payment.model;

import java.time.LocalDate;

import com.Parthi.Logistic.Product.model.Product;
import com.Parthi.Logistic.customer.model.Customer;

public class Payment {
    private String paymentId;
    private LocalDate paymentDate;
    private double paidAmount;
    private Customer customerNumber;
    private Product product;

    public Payment() {
    }

    public Payment(String paymentId, LocalDate paymentDate, double paidAmount, Customer customerNumber, Product product) {
        this.paymentId = paymentId;
        this.paymentDate = paymentDate;
        this.paidAmount = paidAmount;
        this.customerNumber = customerNumber;
        this.product = product;
    }


    public String getPaymentId() {
        return this.paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public LocalDate getPaymentDate() {
        return this.paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getPaidAmount() {
        return this.paidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Customer getCustomerNumber() {
        return this.customerNumber;
    }

    public void setCustomerNumber(Customer customerNumber) {
        this.customerNumber = customerNumber;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


}
