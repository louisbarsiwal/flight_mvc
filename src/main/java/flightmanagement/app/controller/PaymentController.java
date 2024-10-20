package flightmanagement.app.controller;

import flightmanagement.app.dao.PaymentDao;
import flightmanagement.app.entities.Payment;
import flightmanagement.app.utilities.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentDao paymentDao; 

    @GetMapping("/openPaymentPage")
    public String openPaymentPage() {
        return "payment";
    }
    
    @GetMapping("/openBookedTicket")
    public String openBookedTicket() {
        return "flightTicket";
    }
    
    @PostMapping("/submit")
    public String submitPayment(
        @RequestParam String paymentMethod,
        @RequestParam(required = false) String bankName,
        @RequestParam(required = false) String cardNumber,
        @RequestParam(required = false) String expiryDate,
        @RequestParam(required = false) String cardHolderName,
        @RequestParam(required = false) String upiId,
        @RequestParam(required = false) String walletName,
        @RequestParam("amount")double amount,
        RedirectAttributes redirectAttributes) throws SQLException, IOException {

        System.out.println("Received payment method: " + paymentMethod);
        //String transactionId = UUID.randomUUID().toString();
        
        String transactionId = Transaction.generateNumericTransactionId(8);
        Payment payment = new Payment(transactionId, paymentMethod,
                                       bankName, cardNumber,
                                       expiryDate,
                                       cardHolderName, upiId,
                                       walletName, amount, "Pending");

       int result= paymentDao.savePayment(payment);
       if (result > 0) {  

        redirectAttributes.addFlashAttribute("message", "Payment submitted successfully with Transaction ID" + transactionId);
        return "redirect:/payment/openBookedTicket";
       }	
    	else {
    		redirectAttributes.addFlashAttribute("message", "Payment Failed");
    			return "redirect:/payment/openPaymentPage";
    	}
        
    }
    
}