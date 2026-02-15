package org.ostech.springimplemetation.service.JavaCore.ConcurrencyAPI.ConcurrencyPatterns.ConsumerProducerPattern;

import org.ostech.springimplemetation.service.JavaCore.ConcurrencyAPI.ConcurrencyPatterns.BackGroundQueuePattern.EmailExecutorQueuePattern;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ConsumerProducerPatternService {
    public final static AtomicInteger count = new AtomicInteger();

    private final EmailExecutorQueuePattern emailExecutorQueuePattern;

    ConsumerProducerPatternService(EmailExecutorQueuePattern emailExecutorQueuePattern) {
        this.emailExecutorQueuePattern = emailExecutorQueuePattern;
    }

    public void sendEmail(String to, String message) {
        emailExecutorQueuePattern.getEmailExecutorQueue().execute(() -> {
            try {
                sendActualEmail(to, message);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void sendActualEmail(String to, String message) throws InterruptedException {
        Thread.sleep(200);
        System.out.println(to + " | " + message + " | " + Thread.currentThread().getName() + " | " + count.incrementAndGet());
    }
}
