package org.ostech.springimplemetation.JavaCore.ConcurrencyAPI.SingleThreadExecutor.BackGroundQue;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class EmailService {

    public final static AtomicInteger count = new AtomicInteger();

    private final EmailExecutorQueue emailExecutorQueue;

    EmailService(EmailExecutorQueue emailExecutorQueue) {
        this.emailExecutorQueue = emailExecutorQueue;
    }

    public void sendEmail(String to, String message) {
        emailExecutorQueue.getEmailExecutorQueue().execute(() -> {
            try {
                sendActualEmail(to, message);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void sendActualEmail(String to, String message) throws InterruptedException {
        Thread.sleep(2000);
        System.out.println(to + " | " + message + " | " + Thread.currentThread().getName() + " | " + count.incrementAndGet());
    }

}
