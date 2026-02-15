package org.ostech.springimplemetation.JavaCore.ConcurrencyAPI.SingleThreadExecutor.BackGroundQue;

import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
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
        Thread.sleep(200);
        System.out.println(to + " | " + message + " | " + Thread.currentThread().getName() + " | " + count.incrementAndGet());
    }

    public void sendNotifications() {
        CompletableFuture<Void> emailFuture = CompletableFuture.runAsync(() -> sendEmail("email", "message"));

        CompletableFuture<Void> appNotificationFuture = CompletableFuture.runAsync(() -> sendAppNotificationFuture(1, "short message"));

        CompletableFuture<Void> smsFuture = CompletableFuture.runAsync(() -> sendSms(1, "short message"));

        CompletableFuture.allOf(emailFuture, appNotificationFuture, smsFuture).join();
    }

    private void sendAppNotificationFuture(int i, String shortMessage) {
        System.out.println("sendAppNotificationFuture " + shortMessage + Thread.currentThread().getName());
    }

    private void sendSms(int i, String shortMessage) {
        System.out.println("sendSms " + shortMessage + Thread.currentThread().getName());
    }

    public CompletableFuture<Boolean> sendEmailSequentialDependent() {
        System.out.println("Start sendEmail Sequential Dependent");
        return validatePayload().thenCompose(this::generateAttachments).thenCompose(this::sendEmail1).exceptionally(ex -> {
            System.err.println("Error: " + ex.getMessage());
            return false;
        });
    }

    private CompletableFuture<Boolean> generateAttachments(Boolean payload) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Start - generateAttachments - Thread - " + Thread.currentThread().getName());
            try {
                Thread.sleep(15);
                return true;
            } catch (InterruptedException e) {
                System.out.println("Exception: " + e.getMessage());
                return false;
            }
        });
    }

    private CompletableFuture<Boolean> validatePayload() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("Start - Validate Payload - Thread - " + Thread.currentThread().getName());
                Thread.sleep(5);
                return true;
            } catch (InterruptedException e) {
                System.out.println("Exception: " + e.getMessage());
                return true;
            }
        });
    }

    private CompletableFuture<Boolean> sendEmail1(Boolean payload) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("Start - sendEmail - Thread - " + Thread.currentThread().getName());

                Thread.sleep(10);
                return Boolean.TRUE;
            } catch (InterruptedException e) {
                System.out.println("Exception: " + e.getMessage());
                return Boolean.TRUE;
            }
        });
    }
}
