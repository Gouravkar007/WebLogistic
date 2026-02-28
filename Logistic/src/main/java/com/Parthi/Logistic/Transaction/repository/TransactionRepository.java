package com.parthi.logistic.transaction.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.parthi.logistic.transaction.model.Transaction;
import java.util.List;



@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
    List<Transaction> findAllById(Integer id);
    

}


