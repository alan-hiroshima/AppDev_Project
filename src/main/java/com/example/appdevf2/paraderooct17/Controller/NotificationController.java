package com.example.appdevf2.paraderooct17.Controller;

import org.springframework.web.bind.annotation.*;

import com.example.appdevf2.paraderooct17.Entity.NotificationEntity;
import com.example.appdevf2.paraderooct17.Entity.NotificationEntity.NotificationType;
import com.example.appdevf2.paraderooct17.Service.NotificationService;

import java.util.*;

@RestController
@RequestMapping("/notification")
public class NotificationController {
    
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // Get notifications for a user
    @GetMapping("/user/{userId}")
    public List<NotificationEntity> getUserNotifications(@PathVariable int userId) {
        return notificationService.getNotifications(userId);
    }

    // Send a notification manually (optional)
    @PostMapping("/send")
    public NotificationEntity sendNotification(@RequestBody Map<String, String> payload) {

        int userId = Integer.parseInt(payload.get("userId"));
        String typeString = payload.get("type");
        String title = payload.get("title");
        String body = payload.get("body");

        NotificationType type = NotificationType.valueOf(typeString);

        return notificationService.sendNotification(userId, type, title, body);
    }

    // Mark a notification as read
    @PutMapping("/read/{id}")
    public NotificationEntity markNotificationAsRead(@PathVariable int id) {
        return notificationService.markAsRead(id);
    }
}
