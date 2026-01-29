package org.ostech.springimplemetation.controller;

import org.ostech.springimplemetation.JavaCore.ConcurrencyAPI.SingleThreadExecutor.BackGroundQue.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/concurrent")
@RestController
public class ConcurrentAPIController {

    EmailService emailService;

    ConcurrentAPIController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send-email")
    public String sendEmail(String[] args) throws InterruptedException {//newSingleThreadExecutor
        emailService.sendEmail("test@email.com", "Message");
        return "Success";
    }
}
