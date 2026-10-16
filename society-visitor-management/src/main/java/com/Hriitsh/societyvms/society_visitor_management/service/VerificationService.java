package com.Hriitsh.societyvms.society_visitor_management.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class VerificationService {

    @Async("taskExecutor")
    public CompletableFuture<Boolean> verifyEmail(String email) {
        System.out.println("[" + Thread.currentThread().getName() + "] Verifying email: " + email + " - STARTED");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("[" + Thread.currentThread().getName() + "] Email verification done: " + email);
        return CompletableFuture.completedFuture(true);
    }

    @Async("taskExecutor")
    public CompletableFuture<Boolean> verifyPhoneNumber(String phoneNumber) {
        System.out.println("[" + Thread.currentThread().getName() + "] Verifying phone: " + phoneNumber + " - STARTED");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("[" + Thread.currentThread().getName() + "] Phone verification done: " + phoneNumber);
        return CompletableFuture.completedFuture(true);
    }
}
