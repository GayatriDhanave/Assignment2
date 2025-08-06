package com.payment.payment.service;

import com.payment.payment.entity.PaymentRequest;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InMemoryCache implements Cache {
    Map<String, Object> cache = new HashMap<>();

    @Override
    public boolean save (String transactionId, int otp) {
        cache.put(transactionId, otp);
        return true;
    }

    @Override
    public int get (String transactionId) {
        return (int) cache.get(transactionId);

    }

    @Override
    public boolean remove (String transactionId) {
        cache.remove(transactionId);
        return true;
    }
}
