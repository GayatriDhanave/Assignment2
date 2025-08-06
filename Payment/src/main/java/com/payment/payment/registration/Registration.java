//package com.payment.payment.configuration;
//
//import com.payment.payment.registry.PaymentProcessorRegistry;
//import com.payment.payment.service.*;
//import jakarta.annotation.PostConstruct;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class Registration {
//    @Autowired
//    PaymentProcessorRegistry registry;
//
//    @PostConstruct
//    public void registerPaymentProcessors () {
//        System.out.println("Registering payment processors...");
//        registry.put("UPI", new UPIPayment());
//        registry.put("Card", new CreditCardPayment());
//        registry.put("NetBanking", new NetBankingPayment());
//        registry.put("COD", new CashOnDelivery());
//    }
//
//    public PaymentProcessor get (String key) {
//        return registry.get(key);
//    }
//
//}
