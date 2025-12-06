package com.example.appdevf2.paraderooct17.Controller;

import com.example.appdevf2.paraderooct17.Entity.*;
import com.example.appdevf2.paraderooct17.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // 1. GET SYSTEM REPORTS
    // URL: http://localhost:8080/admin/reports
    @GetMapping("/reports")
    public Map<String, Object> getReports() {
        return adminService.getSystemStats();
    }

    // 2. GET PENDING TUTORS
    // URL: http://localhost:8080/admin/tutors/pending
    @GetMapping("/tutors/pending")
    public List<TutorProfileEntity> getPendingTutors() {
        return adminService.getPendingTutors();
    }

    // 3. VERIFY A TUTOR
    // URL: http://localhost:8080/admin/tutors/1/verify
    @PostMapping("/tutors/{id}/verify")
    public TutorProfileEntity verifyTutor(@PathVariable int id) {
        return adminService.verifyTutor(id);
    }

    // 4. SUSPEND A USER
    // URL: http://localhost:8080/admin/users/1/suspend
    @PutMapping("/users/{id}/suspend")
    public UserEntity suspendUser(@PathVariable int id) {
        return adminService.updateUserStatus(id, false);
    }

    // 5. REACTIVATE A USER
    // URL: http://localhost:8080/admin/users/1/reactivate
    @PutMapping("/users/{id}/reactivate")
    public UserEntity reactivateUser(@PathVariable int id) {
        return adminService.updateUserStatus(id, true);
    }

    // 6. RESOLVE A DISPUTE
    // URL: http://localhost:8080/admin/disputes/1/resolve?status=RESOLVED
    @PostMapping("/disputes/{id}/resolve")
    public DisputeEntity resolveDispute(@PathVariable int id, @RequestParam String status) {
        return adminService.resolveDispute(id, status);
    }
    
    // 7. FILE A DISPUTE (For testing purposes)
    // URL: http://localhost:8080/admin/disputes/file?bookingId=1
    @PostMapping("/disputes/file")
    public DisputeEntity fileDispute(@RequestBody DisputeEntity dispute, @RequestParam int bookingId) {
        return adminService.fileDispute(dispute, bookingId);
    }
    
    // 8. GET ALL DISPUTES
    @GetMapping("/disputes")
    public List<DisputeEntity> getAllDisputes() {
        return adminService.getAllDisputes();
    }
}