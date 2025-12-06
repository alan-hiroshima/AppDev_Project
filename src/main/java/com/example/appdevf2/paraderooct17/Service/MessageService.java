package com.example.appdevf2.paraderooct17.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.appdevf2.paraderooct17.Entity.*;
import com.example.appdevf2.paraderooct17.Repository.*;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationService notificationService;

    // Send message
    public MessageEntity sendMessage(int bookingId, int senderId, String text) {

        BookingEntity booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        UserEntity sender = userRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        MessageEntity msg = new MessageEntity(
                booking,
                sender,
                text,
                LocalDateTime.now().toString()
        );

        msg = messageRepository.save(msg);

        // 1. Determine Receiver
        int studentId = booking.getUser().getUsersid();
        // Access the Tutor's User ID via the TutorProfile
        int tutorUserId = booking.getTutorProfile().getUser().getUsersid();

        int receiverId;
        if (senderId == studentId) {
            receiverId = tutorUserId; // If student sends, tutor receives
        } else if (senderId == tutorUserId) {
            receiverId = studentId;   // If tutor sends, student receives
        } else {
            // Security check: The sender is neither the student nor the tutor for this booking
            throw new RuntimeException("User is not part of this booking.");
        }

        // 2. Trigger the NEW_MESSAGE notification using the new method
        // We pass the sender's first name to make the notification personal
        notificationService.sendNewMessageNotification(
            receiverId, 
            sender.getFirstName(), 
            text
        );

        return msg;
    }


    // Get full chat history for booking
    public List<MessageEntity> getMessages(int bookingId) {
        return messageRepository.findByBooking_BookingIdOrderBySentAtAsc(bookingId);
    }

    // Mark message as read
    public MessageEntity markAsRead(int messageId) {
        MessageEntity msg = messageRepository.findById(messageId)
                .orElseThrow(() -> new RuntimeException("Message not found"));

        msg.setReadAt(LocalDateTime.now().toString());
        return messageRepository.save(msg);
    }
}
