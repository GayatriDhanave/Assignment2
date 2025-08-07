package com.payment.payment.service;

import org.springframework.stereotype.Component;

@Component
public interface Cache {
    boolean save(String transactionId, String otp);
    String get(String transactionId);
    boolean remove(String transactionId);
}
