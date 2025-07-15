package com.arpan.login.OTPLogin.controller;

import com.arpan.login.OTPLogin.service.RazorPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class RazorPayController {

    @Autowired
    private RazorPayService razorPayService;

    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestParam int amount , @RequestParam String currency){
        try{
            return new ResponseEntity<>(razorPayService.createOrder(amount,currency,"r-101"),HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Some Internal Issues", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
