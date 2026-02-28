package com.parthi.logistic.payment.service;

import java.time.LocalDate;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parthi.logistic.payment.model.Payment;
import com.parthi.logistic.payment.repository.PaymentRepository;
import com.parthi.logistic.product.Repository.ProductRepo;

@Service
public class PaymentService {
    private static final Logger logger = LoggerFactory.getLogger(PaymentService.class);

    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    ProductRepo productRepo;


    public String addPayment(Payment payment) throws Exception {
        String response = "";
        try{
            if (payment.getPaymentDate().isAfter(LocalDate.now()))
				throw new Exception("Payment date cannot be a future date");
            paymentRepository.save(payment);
            response = "payment added";
        } catch (Exception e) {
            response = e.getLocalizedMessage();
			logger.error("Exception occured while adding payment: " + e.getLocalizedMessage());
			throw e;
        }
        return response;
    }

    public Optional<Payment> getPaidAmount(Integer productId) throws Exception {
       
        try {
            Optional<Payment> amount =  paymentRepository.findById( productId);
            if (amount.isPresent()){
                logger.info("Payment [" + productId+"]");
            }
        } catch (RuntimeException e) {
            logger.error("Exception occured while retrieving paid amount: " + e.getLocalizedMessage());
            throw e;
        }
        return getPaidAmount(productId);

    }

}
