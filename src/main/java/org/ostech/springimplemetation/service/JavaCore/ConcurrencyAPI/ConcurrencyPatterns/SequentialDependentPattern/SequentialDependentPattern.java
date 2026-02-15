package org.ostech.springimplemetation.service.JavaCore.ConcurrencyAPI.ConcurrencyPatterns.SequentialDependentPattern;

import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class SequentialDependentPattern {

    public final static AtomicInteger count = new AtomicInteger();



    public void sendActualEmail(String to, String message) throws InterruptedException {
        Thread.sleep(200);
        System.out.println(to + " | " + message + " | " + Thread.currentThread().getName() + " | " + count.incrementAndGet());
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
