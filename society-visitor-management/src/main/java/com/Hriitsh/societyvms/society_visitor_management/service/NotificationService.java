package com.Hriitsh.societyvms.society_visitor_management.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private VerificationService verificationService;

    @Async("taskExecutor")
    public void sendWelcomeNotification(String residentName, String email) {
        System.out.println("[" + Thread.currentThread().getName() + "] Sending notification to "
                + residentName + " (" + email + ") - STARTED");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("[" + Thread.currentThread().getName() + "] Notification sent to "
                + residentName + " - DONE");
    }

    public String verifyResidentDetails(String email, String phoneNumber) {
        long startTime = System.currentTimeMillis();

        CompletableFuture<Boolean> emailCheck = verificationService.verifyEmail(email);
        CompletableFuture<Boolean> phoneCheck = verificationService.verifyPhoneNumber(phoneNumber);

        CompletableFuture<String> combined = emailCheck.thenCombine(phoneCheck, (emailValid, phoneValid) -> {
            return "Email valid: " + emailValid + ", Phone valid: " + phoneValid;
        });

        String result = combined.join();

        long endTime = System.currentTimeMillis();
        System.out.println("Total verification time: " + (endTime - startTime) + "ms");

        return result;
    }
}
