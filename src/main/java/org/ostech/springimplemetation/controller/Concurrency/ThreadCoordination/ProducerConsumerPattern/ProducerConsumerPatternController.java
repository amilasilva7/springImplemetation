package org.ostech.springimplemetation.controller.Concurrency.ThreadCoordination.ProducerConsumerPattern;

import org.ostech.springimplemetation.service.JavaCore.ConcurrencyAPI.ConcurrencyPatterns.ConsumerProducerPattern.ConsumerProducerPatternService;
import org.ostech.springimplemetation.service.JavaCore.ConcurrencyAPI.ConcurrencyPatterns.ConsumerProducerPattern.OrderProcessor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/concurrent/producer-consumer-pattern")
@RestController
public class ProducerConsumerPatternController {

    OrderProcessor orderProcessor;
    ConsumerProducerPatternService consumerProducerPatternService;

    ProducerConsumerPatternController(OrderProcessor orderProcessor, ConsumerProducerPatternService consumerProducerPatternService) {
        this.orderProcessor = orderProcessor;
        this.consumerProducerPatternService = consumerProducerPatternService;
    }

    @PostMapping("/send-email")
    public String sendEmail(String[] args) throws InterruptedException {//newSingleThreadExecutor
        consumerProducerPatternService.sendEmail("test@email.com", "Message");
        return "Success";
    }

    @PostMapping("/create-order")
    public String createOrder(String[] args) {//newSingleThreadExecutor
        orderProcessor.processOrder("Bunch of items");
        return "Success";
    }
}
