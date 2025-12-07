package com.example.appdevf2.paraderooct17.Controller;

import org.springframework.web.bind.annotation.*;

import com.example.appdevf2.paraderooct17.Entity.PayoutEntity;
import com.example.appdevf2.paraderooct17.Service.PayoutService;

@RestController
@RequestMapping("/payout")
public class PayoutController {

    private final PayoutService payoutService;

    public PayoutController(PayoutService payoutService) {
        this.payoutService = payoutService;
    }

    @GetMapping("/balance/{tutorProfileId}")
    public float getAvailableBalance(@PathVariable int tutorProfileId) {
        return payoutService.getAvailableBalance(tutorProfileId);
    }

    @PostMapping("/request/{tutorProfileId}")
    public PayoutEntity requestPayout(
            @PathVariable int tutorProfileId,
            @RequestParam float amount) {

        return payoutService.requestPayout(tutorProfileId, amount);
    }
}
