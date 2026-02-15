package org.ostech.springimplemetation.controller;

import org.ostech.springimplemetation.JavaCore.ConcurrencyAPI.SingleThreadExecutor.BackGroundQue.EmailService;
import org.ostech.springimplemetation.JavaCore.ConcurrencyAPI.SingleThreadExecutor.SequentialProcessingGuarantee.OrderProcessor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/concurrent")
@RestController
public class ConcurrentAPIController {

    EmailService emailService;
    OrderProcessor orderProcessor;

    ConcurrentAPIController(EmailService emailService, OrderProcessor orderProcessor) {
        this.emailService = emailService;
        this.orderProcessor = orderProcessor;
    }

    @PostMapping("/send-email")
    public String sendEmail(String[] args) throws InterruptedException {//newSingleThreadExecutor
        emailService.sendEmail("test@email.com", "Message");
        return "Success";
    }

    @PostMapping("/create-order")
    public String createOrder(String[] args) {//newSingleThreadExecutor
        orderProcessor.processOrder("Bunch of items");
        return "Success";
    }

    @PostMapping("/independent-parallel-pattern")
    public String independentParallelPattern(String[] args) {//newSingleThreadExecutor
        emailService.sendNotifications();
        return "Success";
    }

    @PostMapping("/sequential-dependent-pattern")
    public String SequentialDependent(String[] args) {//newSingleThreadExecutor
        emailService.sendEmailSequentialDependent();
        return "Success";
    }
}
