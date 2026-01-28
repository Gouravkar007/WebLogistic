package com.Parthi.Logistic.payment.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.Parthi.Logistic.payment.repository.PaymentRepository;


public class PaymentService {
    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);
    PaymentRepository paymentRepository = new PaymentRepository();

    public double getPaidAmount(String paymentId) throws Exception {
        try{
            double amount = paymentRepository.getPaidAmount(paymentId);
            if(amount == 0.0){
                throw new RuntimeException( "Payment not found: " + paymentId);
            }
            return amount;
        } catch (Exception e) {
            logger.error("Exception occured while retrieving payment for id {}", paymentId, e);
            throw e;
        }

    }
}
