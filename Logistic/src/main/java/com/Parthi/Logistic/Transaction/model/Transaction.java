package com.parthi.logistic.Transaction.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "txn_id")
    private int id;
    @Column(name = "invoice")
    private String invoice;
    @Column(name = "particulars")
    private String particular;
    @Column(name = "txn_type", length = 50)
    private String txnType;
    @Column(name = "category")
    private String txnCategory;
    @Column(name = "txn_mode", length = 20)
    private String paymentMode;
    @Column(name = "description")
    private String description;
    @Column(name = "Amount")
    private double amount;
    @Column(name = "txn_date")
    LocalDate transactionDate;

}
