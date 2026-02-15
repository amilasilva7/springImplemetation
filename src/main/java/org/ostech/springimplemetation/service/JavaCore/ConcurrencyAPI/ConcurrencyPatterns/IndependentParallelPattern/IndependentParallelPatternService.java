package org.ostech.springimplemetation.service.JavaCore.ConcurrencyAPI.ConcurrencyPatterns.IndependentParallelPattern;

import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class IndependentParallelPatternService {
    public void sendNotifications() {
        CompletableFuture<Void> emailFuture = CompletableFuture.runAsync(() -> sendEmail(1, "message"));

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

    private void sendEmail(int i, String shortMessage) {
        System.out.println("sendEmail " + shortMessage + Thread.currentThread().getName());
    }
}
