package com.payment.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.payment.payment.service", "controller"})
public class PaymentApplication {


    public static void main(String[] args) {
        SpringApplication.run(PaymentApplication.class, args);
    }




}
