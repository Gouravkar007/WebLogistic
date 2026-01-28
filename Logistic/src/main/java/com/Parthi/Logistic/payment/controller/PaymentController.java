package com.Parthi.Logistic.payment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.Parthi.Logistic.payment.service.PaymentService;

@RestController
public class PaymentController {
    PaymentService paymentService = new PaymentService();

/**
 * This API shows the details of a payment whose payment id is entered
 * @param pathVariable is used to add id to  the path
 * @return Returns a response entity ok with payment details if found
 */
    @GetMapping("/getpayment/paymentid/{paymentId}")
    public ResponseEntity<Double> getPaidAmount(@PathVariable String paymentId) throws Exception{
        return ResponseEntity.ok(paymentService.getPaidAmount(paymentId));
    }

}
