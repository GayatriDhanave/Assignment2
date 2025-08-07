package com.payment.payment.service;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InMemoryCache implements Cache {
    Map<String, String> cache = new HashMap<>();

    @Override
    public boolean save (String transactionId, String otp) {
        cache.put(transactionId, otp);
        return true;
    }

    @Override
    public String get (String transactionId) {
        return cache.get(transactionId);

    }

    @Override
    public boolean remove (String transactionId) {
        cache.remove(transactionId);
        return true;
    }
}
