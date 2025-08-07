package com.payment.payment.controller;

import com.payment.payment.entity.PaymentRequest;
import com.payment.payment.entity.PaymentRequestDTO;
import com.payment.payment.service.PaymentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RestController
@ComponentScan
@RequestMapping("/api")
public class PaymentController {
    @Autowired
    PaymentHandler paymentHandler;

    //TODO: check with @RequestBody in place of @ModelAttribute
//    request body is used to send the data in the body of the request
//    json format
//    model attribute is used to send the data in the form of a query string
//    -> http://localhost:8080/api/initiatePayment?paymentType=upi&details=asd@oksbi
//    used for multipart requests

    @GetMapping("initiatePayment")
    public String makePayment (@RequestBody PaymentRequestDTO paymentRequestDto) {
        String transactionId = paymentHandler.handlePayment(paymentRequestDto);

        if (transactionId.length() > 36 && transactionId.length() <= 40) {
            String id = transactionId.substring(0, (transactionId.length() - 4));
            String otp = transactionId.substring(id.length());
            return "Your transaction ID is: " + id + " and OTP is: " + otp;
        } else if (transactionId.length()<=36) {
            return "Your transaction ID is: "+transactionId;
        }
        return "Payment initiation failed";
    }

    @GetMapping("/completePayment")
    public String completePayment (@RequestBody PaymentRequest paymentRequest) {
        if (paymentHandler.completePayment(paymentRequest.getPaymentType(), paymentRequest.getOtp(), paymentRequest.getTransactionId())) {
            return "payment completed";
        }
        return "payment failed";
    }

}
