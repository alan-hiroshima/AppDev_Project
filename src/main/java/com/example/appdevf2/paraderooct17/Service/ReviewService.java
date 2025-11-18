package com.example.appdevf2.paraderooct17.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.appdevf2.paraderooct17.Entity.BookingEntity;
import com.example.appdevf2.paraderooct17.Entity.ReviewEntity;
import com.example.appdevf2.paraderooct17.Entity.TutorProfileEntity;
import com.example.appdevf2.paraderooct17.Repository.BookingRepository;
import com.example.appdevf2.paraderooct17.Repository.ReviewRepository;
import com.example.appdevf2.paraderooct17.Repository.TutorProfileRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private TutorProfileRepository tutorProfileRepository;

    public List<ReviewEntity> getAllReviews() {
        return reviewRepository.findAll();
    }

    public ReviewEntity getReviewById(int id) {
        return reviewRepository.findById(id).orElse(null);
    }

    @Transactional
    public ReviewEntity saveReview(ReviewEntity reviewFromRequest) {
        
        int bookingId = reviewFromRequest.getBooking().getBookingId();
        int tutorProfileId = reviewFromRequest.getTutorProfile().getTutorProfileId();

        BookingEntity managedBooking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + bookingId));
        
        TutorProfileEntity managedTutorProfile = tutorProfileRepository.findById(tutorProfileId)
                .orElseThrow(() -> new RuntimeException("TutorProfile not found with id: " + tutorProfileId));

        reviewFromRequest.setBooking(managedBooking);
        reviewFromRequest.setTutorProfile(managedTutorProfile);

        return reviewRepository.save(reviewFromRequest);
    }

    @Transactional
    public ReviewEntity updateReview(int id, ReviewEntity reviewDetails) {
        
        ReviewEntity existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));

        BookingEntity managedBooking = bookingRepository.findById(reviewDetails.getBooking().getBookingId())
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        
        TutorProfileEntity managedTutorProfile = tutorProfileRepository.findById(reviewDetails.getTutorProfile().getTutorProfileId())
                .orElseThrow(() -> new RuntimeException("TutorProfile not found"));

        existingReview.setBooking(managedBooking);
        existingReview.setTutorProfile(managedTutorProfile);
        existingReview.setRating(reviewDetails.getRating());
        existingReview.setComment(reviewDetails.getComment());
        existingReview.setCreatedAt(reviewDetails.getCreatedAt());

        return reviewRepository.save(existingReview);
    }

    @Transactional
    public void deleteReview(int id) {
        ReviewEntity reviewToDelete = reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found with id: " + id));
        
        BookingEntity booking = reviewToDelete.getBooking();
        TutorProfileEntity tutorProfile = reviewToDelete.getTutorProfile();

        if (booking != null) {
            booking.setReview(null);
        }
        
        if (tutorProfile != null && tutorProfile.getReviews() != null) {
            tutorProfile.getReviews().remove(reviewToDelete);
        }
        reviewRepository.delete(reviewToDelete);
        reviewRepository.flush();
    }
}
