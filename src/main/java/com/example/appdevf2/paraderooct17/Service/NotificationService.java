package com.example.appdevf2.paraderooct17.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.appdevf2.paraderooct17.Entity.BookingEntity;
import com.example.appdevf2.paraderooct17.Entity.NotificationEntity;
import com.example.appdevf2.paraderooct17.Entity.NotificationEntity.NotificationType;
import com.example.appdevf2.paraderooct17.Entity.UserEntity;
import com.example.appdevf2.paraderooct17.Repository.NotificationRepository;
import com.example.appdevf2.paraderooct17.Repository.UserRepository;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    // Generic notification sender
    public NotificationEntity sendNotification(int userId, NotificationType type, String title, String body) {

        UserEntity user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        NotificationEntity notification = new NotificationEntity(
            type,
            title,
            body,
            LocalDateTime.now().toString()
        );

        notification.setUser(user);

        return notificationRepository.save(notification);
    }

    // ====== Automated Notifications for System Events ======

    public NotificationEntity sendBookingCreated(int userId, BookingEntity booking) {
        return sendNotification(
            userId,
            NotificationType.NEW_BOOKING,
            "New Booking Created",
            "Your booking for " + booking.getSubject().getName() + " is confirmed."
        );
    }

    public NotificationEntity sendBookingCancelled(int userId, BookingEntity booking) {
        return sendNotification(
            userId,
            NotificationType.BOOKING_CANCELLED,
            "Booking Cancelled",
            "A booking has been cancelled."
        );
    }

    public NotificationEntity sendPayoutProcessed(int tutorId, float amount) {
        return sendNotification(
            tutorId,
            NotificationType.PAYOUT_PROCESSED,
            "Payout Sent",
            "Your payout of ₱" + amount + " has been processed."
        );
    }

    public NotificationEntity sendDisputeResolved(int userId, String resolution) {
        return sendNotification(
            userId,
            NotificationType.DISPUTE_RESOLVED,
            "Dispute Update",
            "Your dispute has been resolved: " + resolution
        );
    }

    // --- ADD THIS NEW METHOD ---
    public NotificationEntity sendNewMessageNotification(int receiverId, String senderName, String messagePreview) {
        // Truncate long messages for the notification body
        String shortBody = messagePreview.length() > 50 
            ? messagePreview.substring(0, 50) + "..." 
            : messagePreview;

        return sendNotification(
            receiverId,
            NotificationType.NEW_MESSAGE,
            "New Message from " + senderName,
            shortBody
        );
    }

    // Fetch notifications for a user
    public List<NotificationEntity> getNotifications(int userId) {
        UserEntity user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found"));

        List<NotificationEntity> list = user.getNotifications();
        list.sort(Comparator.comparing(NotificationEntity::getCreatedAt).reversed());

        return list;
    }

    // Mark as read
    public NotificationEntity markAsRead(int id) {

        NotificationEntity notif = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));

        notif.setReadAt(LocalDateTime.now().toString());
        return notificationRepository.save(notif);
    }
}
