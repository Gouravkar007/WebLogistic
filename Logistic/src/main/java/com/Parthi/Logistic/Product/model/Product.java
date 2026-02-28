package com.parthi.logistic.product.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Product {

    @NotBlank(message = "Product id cannot be empty")
    @Id
    @Column(name = "product_id", length = 30)
    private String id;

    @NotBlank(message = "Category is required")
    @Column(name = "product_type")
    private String category;

    @NotBlank(message = "Product name is required")
    @Column(name = "product_name")
    private String name;

    @NotBlank(message = "Description is required")
    @Column(name = "description")
    private String description;

    @Positive(message = "Cost price must be greater than 0")
    @Column(name = "cost_price")
    private double costPrice = 0;

    @Positive(message = "Selling price must be greater than 0")
    @Column(name = "actual_selling_price")
    private double sellingPrice = 0;

    @Positive(message = "MRP must be greater than 0")
    @Column(name = "calculated_selling_price")
    private double mrp;

    @Column(name = "status", length = 50)
    String status; // TODO Create a enum for the status with AVAILABLE, SOLDOUT, RETURNED

    @Column(name = "checkin_date")
    LocalDate stockInDate;

    @Column(name = "checkout_date")
    LocalDate stockOutDate;

}
