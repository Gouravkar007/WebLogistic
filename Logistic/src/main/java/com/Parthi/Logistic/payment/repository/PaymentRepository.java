package com.parthi.logistic.payment.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.parthi.logistic.payment.model.Payment;
import java.util.List;


@Repository

public interface PaymentRepository extends CrudRepository<Payment, Integer>{
    Optional<Payment> findById(Integer id);

    List <Payment>  findByPaymentId(int paymentId);
}

