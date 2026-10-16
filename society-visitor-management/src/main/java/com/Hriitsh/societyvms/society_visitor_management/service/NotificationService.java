package com.Hriitsh.societyvms.society_visitor_management.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service 
public class NotificationService {
    @Async("taskExecutor")
    public void sendWelcomeNotification(String residentName, String email){
        System.out.println("[" + Thread.currentThread().getName() + "] Sending notification to "
        + residentName + " (" + email + ") - STARTED");
        try{
            Thread.sleep(3000);
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
        }

        System.out.println("[" + Thread.currentThread().getName() + "] Notification sent to "
                + residentName + " - DONE");
    }
}
