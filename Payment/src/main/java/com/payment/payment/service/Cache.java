package com.payment.payment.service;

import com.payment.payment.entity.PaymentRequest;
import org.springframework.stereotype.Component;

@Component
public interface Cache {
    boolean save(String transactionId, int otp);
    int get(String transactionId);
    boolean remove(String transactionId);
}
