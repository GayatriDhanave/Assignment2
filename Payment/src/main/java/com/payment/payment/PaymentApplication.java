package com.payment.payment;

import com.payment.payment.registry.PaymentProcessorRegistry;
import com.payment.payment.service.CashOnDelivery;
import com.payment.payment.service.CreditCardPayment;
import com.payment.payment.service.NetBankingPayment;
import com.payment.payment.service.UPIPayment;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.payment.payment.service", "controller"})
public class PaymentApplication {


    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication.class, args);
    }




}
