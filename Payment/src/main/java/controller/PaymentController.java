package controller;

import com.payment.payment.service.PaymentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PaymentController {
    @Autowired
    PaymentHandler paymentHandler;

    @GetMapping("/initiatePayment")
    public String makePayment(@RequestParam int paymentType, @RequestParam String details) {
       String transactionId= paymentHandler.handlePayment(paymentType, details);
        if(transactionId==null){
            String id=transactionId.substring(0,(transactionId.length()-5));
            String otp=transactionId.substring(id.length());
            return "Your transaction ID is: "+id+" and OTP is: "+otp;
        }
        return null;
    }

    @GetMapping("/completePayment/{paymentType}/{transactionId}/{otp}")
    public String completePayment(@PathVariable int paymentType, @PathVariable int otp, @PathVariable String transactionId) {
        if(paymentHandler.completePayment(paymentType, otp, transactionId)){
            return "payment completed";
        }
        return "payment failed";
    }
}
