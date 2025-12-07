package com.example.appdevf2.paraderooct17.Controller;

import org.springframework.web.bind.annotation.*;

import com.example.appdevf2.paraderooct17.Entity.PaymentEntity;
import com.example.appdevf2.paraderooct17.Service.PaymentService;

@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = "*")
public class PaymentController {

    private final PaymentService paymentService;
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/charge/{bookingId}")
    public PaymentEntity chargeBooking(@PathVariable int bookingId,
                                       @RequestParam(defaultValue = "CARD") String method) {
        return paymentService.chargeBooking(bookingId, method);
    }

    @PostMapping("/refund/{bookingId}")
    public PaymentEntity refundBooking(@PathVariable int bookingId) {
        return paymentService.refundBooking(bookingId);
    }
}
