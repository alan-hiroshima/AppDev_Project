package com.example.appdevf2.paraderooct17.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.appdevf2.paraderooct17.Entity.BookingEntity;
import com.example.appdevf2.paraderooct17.Entity.SubjectEntity;
import com.example.appdevf2.paraderooct17.Entity.TutorProfileEntity;
import com.example.appdevf2.paraderooct17.Entity.UserEntity;
import com.example.appdevf2.paraderooct17.Repository.BookingRepository;
import com.example.appdevf2.paraderooct17.Repository.SubjectRepository;
import com.example.appdevf2.paraderooct17.Repository.TutorProfileRepository;
import com.example.appdevf2.paraderooct17.Repository.UserRepository;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;
    private final TutorProfileRepository tutorProfileRepository;
    private final PaymentService paymentService;

    public BookingService(BookingRepository bookingRepository,
                          UserRepository userRepository,
                          SubjectRepository subjectRepository,
                          TutorProfileRepository tutorProfileRepository,
                          PaymentService paymentService) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.subjectRepository = subjectRepository;
        this.tutorProfileRepository = tutorProfileRepository;
        this.paymentService = paymentService;
    }

    public List<BookingEntity> getAllBookings() {
        return bookingRepository.findAll();
    }

    public BookingEntity getBookingById(int id) {
        return bookingRepository.findById(id).orElse(null);
    }

    /**
     * Create a booking AND automatically charge it.
     * Flow:
     * 1. Resolve user/subject/tutor from DB (managed entities)
     * 2. Create Booking with status = "PENDING"
     * 3. Save booking to get bookingId
     * 4. Call paymentService.chargeBooking(bookingId, "CARD")
     * 5. Return the updated booking (status should now be "CONFIRMED")
     */
    @Transactional
    public BookingEntity saveBooking(BookingEntity bookingFromRequest) {

        // 1. Resolve related entities by ID (we don't trust raw nested objects)
        if (bookingFromRequest.getUser() == null ||
            bookingFromRequest.getUser().getUsersid() == 0) {
            throw new RuntimeException("User is required for booking");
        }
        if (bookingFromRequest.getSubject() == null ||
            bookingFromRequest.getSubject().getSubjectId() == 0) {
            throw new RuntimeException("Subject is required for booking");
        }
        if (bookingFromRequest.getTutorProfile() == null ||
            bookingFromRequest.getTutorProfile().getTutorProfileId() == 0) {
            throw new RuntimeException("Tutor profile is required for booking");
        }

        int userId = bookingFromRequest.getUser().getUsersid();
        int subjectId = bookingFromRequest.getSubject().getSubjectId();
        int tutorProfileId = bookingFromRequest.getTutorProfile().getTutorProfileId();

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        SubjectEntity subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new RuntimeException("Subject not found with id: " + subjectId));

        TutorProfileEntity tutorProfile = tutorProfileRepository.findById(tutorProfileId)
                .orElseThrow(() -> new RuntimeException("Tutor profile not found with id: " + tutorProfileId));

        // 2. Create a new BookingEntity and copy over fields
        BookingEntity booking = new BookingEntity();
        booking.setUser(user);
        booking.setSubject(subject);
        booking.setTutorProfile(tutorProfile);

        booking.setBookingDate(bookingFromRequest.getBookingDate());
        booking.setStartTime(bookingFromRequest.getStartTime());
        booking.setEndTime(bookingFromRequest.getEndTime());
        booking.setPrice(bookingFromRequest.getPrice());
        booking.setLocationType(bookingFromRequest.getLocationType());
        booking.setLocationDetails(bookingFromRequest.getLocationDetails());
        booking.setRemarks(bookingFromRequest.getRemarks());

        booking.setStatus("PENDING"); // initial status
        booking.setCreatedAt(LocalDateTime.now().toString());
        booking.setUpdatedAt(null);

        // 3. Save booking first to get bookingId
        BookingEntity saved = bookingRepository.save(booking);

        // 4. Charge booking (mock payment). Method is hardcoded to "CARD" for now.
        paymentService.chargeBooking(saved.getBookingId(), "CARD");

        // 5. Reload the booking to get updated status/fields set by PaymentService
        return bookingRepository.findById(saved.getBookingId())
                .orElseThrow(() -> new RuntimeException("Booking not found after payment, id: " + saved.getBookingId()));
    }

    @Transactional
    public BookingEntity updateBooking(int id, BookingEntity details) {

        BookingEntity existing = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));

        // Update basic fields (do NOT change user/subject/tutor here unless you want to)
        existing.setBookingDate(details.getBookingDate());
        existing.setStartTime(details.getStartTime());
        existing.setEndTime(details.getEndTime());
        existing.setPrice(details.getPrice());
        existing.setLocationType(details.getLocationType());
        existing.setLocationDetails(details.getLocationDetails());
        existing.setStatus(details.getStatus());
        existing.setRemarks(details.getRemarks());
        existing.setUpdatedAt(LocalDateTime.now().toString());

        return bookingRepository.save(existing);
    }

    @Transactional
    public void deleteBooking(int id) {
        bookingRepository.deleteById(id);
    }
}
