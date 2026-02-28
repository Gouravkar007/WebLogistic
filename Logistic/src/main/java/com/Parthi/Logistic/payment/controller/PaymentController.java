package com.parthi.logistic.payment.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.parthi.logistic.common.model.SuccessResponse;
import com.parthi.logistic.payment.model.Payment;
import com.parthi.logistic.payment.service.PaymentService;
import com.parthi.logistic.product.Controller.ProductController;

import javax.validation.Valid;

@RestController
public class PaymentController {

    static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    PaymentService paymentService;

    /**
     * This API shows the details of a payment whose payment id is entered
     * 
     * @param pathVariable is used to add id to the path
     * @return Returns a response entity ok with payment details if found
     * @throws Exception
     */
    // @GetMapping("/getpayment/paymentid/{productId}")
    // public ResponseEntity<List<Payment>> getPaidAmount(@PathVariable Integer
    // productId) throws Exception {
    // logger.info("Request received for retrieve payment");
    // Payment payment = paymentService.getPaidAmount(productId)
    // return ;
    // }

    /**
     * @param payment
     * @return
     * @throws Exception
     */
    // @PostMapping("/addpayment")
    // @ResponseBody
    // public ResponseEntity<SuccessResponse> submitPaymentDeatil(@Valid
    // @RequestBody Payment payment,
    // BindingResult result) throws Exception {
    // logger.info("Request recived for Payment");
    // SuccessResponse response = paymentService.addPayment(payment);
    // logger.info("Payment added successfully");
    // return new ResponseEntity<>(response, HttpStatus.CREATED);

}
