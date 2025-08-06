package com.payment.payment.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentHandler {
    PaymentProcessor processor = null;
    String transactionID = null;

    public String handlePayment (int paymentType, String details) {
        switch (paymentType) {
            case 1://UPI payment
                processor = new UPIPayment();
                transactionID = processor.initiatePayment(details);
                break;

            case 2:
                processor = new CreditCardPayment();
                transactionID = processor.initiatePayment(details);
                break;

            case 3:
                processor = new NetBankingPayment();
                transactionID = processor.initiatePayment(details);
                break;

            case 4:
                processor = new CashOnDelivery();
                transactionID = processor.initiatePayment(details);
                break;

            default:
                transactionID = null;
                break;
        }
        return transactionID;
    }

    public boolean completePayment (int paymentType, int otp, String transactionID) {
        boolean status = false;
        switch (paymentType) {
            case 1://UPI payment
                processor = new UPIPayment();
                status = processor.completePayment(otp, transactionID);
                break;

            case 2:
                processor = new CreditCardPayment();
                status = processor.completePayment(otp, transactionID);
                break;

            case 3:
                processor = new NetBankingPayment();
                status = processor.completePayment(otp, transactionID);
                break;

            case 4:
                processor = new CashOnDelivery();
                status = processor.completePayment(otp, transactionID);
                break;

            default:
                status = false;
                break;
        }
        return status;
    }
}
