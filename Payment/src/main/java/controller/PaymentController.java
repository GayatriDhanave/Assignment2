package controller;

import com.payment.payment.service.PaymentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RestController
@ComponentScan
@RequestMapping("/api")
public class PaymentController {
    @Autowired
    PaymentHandler paymentHandler;

    @GetMapping("initiatePayment")
    public String makePayment(@RequestParam("paymentType") int paymentType, @RequestParam("details") String details) {
       String transactionId= paymentHandler.handlePayment(paymentType, details);
        if(transactionId!=null){
            String id=transactionId.substring(0,(transactionId.length()-4));
            String otp=transactionId.substring(id.length());
            return "Your transaction ID is: "+transactionId+" and OTP is: "+otp;
        }
        return null;
    }

    @GetMapping("/completePayment/{paymentType}/{otp}/{transactionId}")
    public String completePayment(@PathVariable int paymentType, @PathVariable int otp, @PathVariable String transactionId) {
        if(paymentHandler.completePayment(paymentType, otp, transactionId)){
            return "payment completed";
        }
        return "payment failed";
    }
    @GetMapping("/getuser")
    public String getusrename() {
        return "Covori";
    }
}
