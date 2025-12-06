package com.example.appdevf2.paraderooct17.Controller;

import org.springframework.web.bind.annotation.*;

import com.example.appdevf2.paraderooct17.Entity.MessageEntity;
import com.example.appdevf2.paraderooct17.Service.MessageService;

import java.util.*;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    // Send message
    @PostMapping("/send")
    public MessageEntity sendMessage(@RequestBody Map<String, String> payload) {

        int bookingId = Integer.parseInt(payload.get("bookingId"));
        int senderId = Integer.parseInt(payload.get("senderId"));
        String text = payload.get("text");

        return messageService.sendMessage(bookingId, senderId, text);
    }

    // Fetch chat history
    @GetMapping("/booking/{bookingId}")
    public List<MessageEntity> getMessages(@PathVariable int bookingId) {
        return messageService.getMessages(bookingId);
    }

    

    // Mark message as read
    @PutMapping("/read/{messageId}")
    public MessageEntity markAsRead(@PathVariable int messageId) {
        return messageService.markAsRead(messageId);
    }
}
