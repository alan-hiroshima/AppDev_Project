package com.example.appdevf2.paraderooct17.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.appdevf2.paraderooct17.Entity.NotificationEntity;
import com.example.appdevf2.paraderooct17.Entity.UserEntity;
import com.example.appdevf2.paraderooct17.Repository.NotificationRepository;
import com.example.appdevf2.paraderooct17.Repository.UserRepository;


@Service
public class NotificationService {
     @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    // Create a new notification
    public NotificationEntity sendNotification(int userId, String type, String title, String body) {

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        NotificationEntity notif = new NotificationEntity(
                type,
                title,
                body,
                LocalDateTime.now().toString()
        );

        notif.setUser(user);

        return notificationRepository.save(notif);
    }

    // Get notifications for a user
    public List<NotificationEntity> getNotifications(int userId) {
        return notificationRepository.findByUser_UsersidOrderByCreatedAtDesc(userId);
    }

    // Mark specific notification as read
    public NotificationEntity markAsRead(int id) {
        NotificationEntity notif = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        notif.setReadAt(LocalDateTime.now().toString());
        return notificationRepository.save(notif);
    }

}
