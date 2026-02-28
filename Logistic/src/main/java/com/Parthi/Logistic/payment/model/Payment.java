
/**
 * This is a product class that provide the defination of the product and provides all the method
 * thats required to maintain the products.
 *
 * @author Moumita Chatterjee
 * @since 2026-01-25
 */
package com.parthi.logistic.payment.model;

import java.time.LocalDate;

import com.parthi.logistic.customer.model.Customer;
import com.parthi.logistic.product.model.Product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "payments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_id")
	private Integer paymentId;
	// @Column(name = "product_id")
	// private String productId;
	@Column(name = "payment_date")
	private LocalDate paymentDate;
	@Column(name = "paid_amount")
	private double paidAmount;
	// @Column(name = "customer_number")
	// private String customerNumber;

	@ManyToOne()
	@JoinColumn(name = "customer_number", referencedColumnName = "contact_number")
	private Customer customerNumber;

	@ManyToOne()
	@JoinColumn(name = "product_id", referencedColumnName = "product_id")
	private Product product;

	public int getPaymentId() {
		return this.paymentId;
	}

	public void setPaymentId(int paymentId) {
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

	public void setProductId(Product product) {
		this.product = product;
	}

}
