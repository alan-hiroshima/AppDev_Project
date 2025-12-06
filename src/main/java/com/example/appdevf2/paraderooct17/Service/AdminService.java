package com.example.appdevf2.paraderooct17.Service;

import com.example.appdevf2.paraderooct17.Entity.*;
import com.example.appdevf2.paraderooct17.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired private UserRepository userRepository;
    @Autowired private TutorProfileRepository tutorProfileRepository;
    @Autowired private BookingRepository bookingRepository;
    @Autowired private DisputeRepository disputeRepository;

    //system stats
    public Map<String, Object> getSystemStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalUsers", userRepository.count());
        stats.put("totalBookings", bookingRepository.count());
        stats.put("totalDisputes", disputeRepository.count());
        
        long activeTutors = tutorProfileRepository.findAll().stream()
                .filter(TutorProfileEntity::getIsActive)
                .count();
        stats.put("activeTutors", activeTutors);
        
        return stats;
    }

    // tutor management
    public List<TutorProfileEntity> getPendingTutors() {
        return tutorProfileRepository.findAll().stream()
                .filter(t -> !t.getIsActive())
                .collect(Collectors.toList());
    }

    // verify tutor
    public TutorProfileEntity verifyTutor(int tutorId) {
        TutorProfileEntity tutor = tutorProfileRepository.findById(tutorId)
                .orElseThrow(() -> new RuntimeException("Tutor not found"));
        tutor.setIsActive(true); 
        return tutorProfileRepository.save(tutor);
    }

    // user management
    public UserEntity updateUserStatus(int userId, boolean isActive) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setIsActive(isActive);
        return userRepository.save(user);
    }

    // dispute management
    public DisputeEntity resolveDispute(int disputeId, String resolutionStatus) {
        DisputeEntity dispute = disputeRepository.findById(disputeId)
                .orElseThrow(() -> new RuntimeException("Dispute not found"));
        
        // Status should be "RESOLVED" or "DISMISSED"
        dispute.setStatus(resolutionStatus); 
        return disputeRepository.save(dispute);
    }
    
    // Helper to create a dispute (so you can test it)
    public DisputeEntity fileDispute(DisputeEntity dispute, int bookingId) {
        BookingEntity booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        dispute.setBooking(booking);
        dispute.setStatus("PENDING"); 
        return disputeRepository.save(dispute);
    }
    
    public List<DisputeEntity> getAllDisputes() {
        return disputeRepository.findAll();
    }
}