package com.Parthi.Logistic.payment.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.Parthi.Logistic.payment.model.Payment;

public class PaymentRepository {
    private static final Logger logger = LoggerFactory.getLogger(PaymentRepository.class);
    private List<Payment> paymentList = new ArrayList<>();
    
    public double getPaidAmount(String paymentId) {
        logger.info("Retriving paid amount for a product/s" + paymentId);
        Payment payment = paymentList.stream().filter(p -> p.getPaymentId().equals(paymentId)).findFirst().orElse(null);

        return payment != null ? payment.getPaidAmount() : 0.0;
    }
}
