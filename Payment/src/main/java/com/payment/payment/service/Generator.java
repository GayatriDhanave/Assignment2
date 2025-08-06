package com.payment.payment.service;

import java.util.UUID;

public class Generator {
    public static String generateTransactionId() {
        UUID uuid = UUID.randomUUID();
       return uuid.toString();
    }
    public static int generateOTP() {
        int otp = (int) (Math.random() * 10000);
        return otp;
    }
}
