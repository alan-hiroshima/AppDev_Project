package com.example.appdevf2.paraderooct17.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.appdevf2.paraderooct17.Entity.BookingEntity;
import com.example.appdevf2.paraderooct17.Entity.SubjectEntity;
import com.example.appdevf2.paraderooct17.Entity.TutorProfileEntity;
import com.example.appdevf2.paraderooct17.Entity.UserEntity;
import com.example.appdevf2.paraderooct17.Repository.BookingRepository;
import com.example.appdevf2.paraderooct17.Repository.SubjectRepository;
import com.example.appdevf2.paraderooct17.Repository.TutorProfileRepository;
import com.example.appdevf2.paraderooct17.Repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private TutorProfileRepository tutorProfileRepository;

    public List<BookingEntity> getAllBookings() {
        return bookingRepository.findAll();
    }

    public BookingEntity getBookingById(int id) {
        return bookingRepository.findById(id).orElse(null);
    }

    @Transactional
    public BookingEntity saveBooking(BookingEntity bookingFromRequest) {
                int userId = bookingFromRequest.getUser().getUsersid();
        int subjectId = bookingFromRequest.getSubject().getSubjectId();
        int tutorProfileId = bookingFromRequest.getTutorProfile().getTutorProfileId();

        UserEntity managedUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
        
        SubjectEntity managedSubject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found with id: " + subjectId));

        TutorProfileEntity managedTutorProfile = tutorProfileRepository.findById(tutorProfileId)
                .orElseThrow(() -> new RuntimeException("TutorProfile not found with id: " + tutorProfileId));

        bookingFromRequest.setUser(managedUser);
        bookingFromRequest.setSubject(managedSubject);
        bookingFromRequest.setTutorProfile(managedTutorProfile);

        return bookingRepository.save(bookingFromRequest);
    }

    @Transactional
    public BookingEntity updateBooking(int id, BookingEntity bookingDetails) {
        
        BookingEntity existingBooking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));

        UserEntity managedUser = userRepository.findById(bookingDetails.getUser().getUsersid())
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        SubjectEntity managedSubject = subjectRepository.findById(bookingDetails.getSubject().getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        TutorProfileEntity managedTutorProfile = tutorProfileRepository.findById(bookingDetails.getTutorProfile().getTutorProfileId())
                .orElseThrow(() -> new RuntimeException("TutorProfile not found"));

        existingBooking.setUser(managedUser);
        existingBooking.setSubject(managedSubject);
        existingBooking.setTutorProfile(managedTutorProfile);
        existingBooking.setBookingDate(bookingDetails.getBookingDate());
        existingBooking.setStatus(bookingDetails.getStatus());
        existingBooking.setRemarks(bookingDetails.getRemarks());

        return bookingRepository.save(existingBooking);
    }

    @Transactional
    public void deleteBooking(int id) {
        bookingRepository.deleteById(id);
    }
}
