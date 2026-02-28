package com.parthi.logistic.transaction.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "transactions")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "txn_id")
    private Integer id;
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
    @Column(name = "amount")
    private double amount;
    @Column(name = "txn_date")
    LocalDate transactionDate;

}
