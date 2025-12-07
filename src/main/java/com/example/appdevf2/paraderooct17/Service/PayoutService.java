package com.example.appdevf2.paraderooct17.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.appdevf2.paraderooct17.Entity.BookingEntity;
import com.example.appdevf2.paraderooct17.Entity.PaymentEntity;
import com.example.appdevf2.paraderooct17.Entity.PayoutEntity;
import com.example.appdevf2.paraderooct17.Entity.TutorProfileEntity;
import com.example.appdevf2.paraderooct17.Repository.BookingRepository;
import com.example.appdevf2.paraderooct17.Repository.PaymentRepository;
import com.example.appdevf2.paraderooct17.Repository.PayoutRepository;
import com.example.appdevf2.paraderooct17.Repository.TutorProfileRepository;

@Service
public class PayoutService {

    private final PayoutRepository payoutRepository;
    private final TutorProfileRepository tutorProfileRepository;
    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;

    // example policy: minimum 100 units before payout
    private static final float MINIMUM_PAYOUT = 100.0f;

    public PayoutService(PayoutRepository payoutRepository,
                         TutorProfileRepository tutorProfileRepository,
                         BookingRepository bookingRepository,
                         PaymentRepository paymentRepository) {
        this.payoutRepository = payoutRepository;
        this.tutorProfileRepository = tutorProfileRepository;
        this.bookingRepository = bookingRepository;
        this.paymentRepository = paymentRepository;
    }

    // Compute total earnings and available balance
    public float getAvailableBalance(int tutorProfileId) {

        TutorProfileEntity tutorProfile = tutorProfileRepository.findById(tutorProfileId)
                .orElseThrow(() -> new RuntimeException("TutorProfile not found with id: " + tutorProfileId));

        // 1. Sum all SUCCEEDED payments for this tutor's bookings
        List<BookingEntity> bookings = tutorProfile.getBookings();
        float totalEarned = 0f;

        if (bookings != null) {
            for (BookingEntity booking : bookings) {
                PaymentEntity payment = paymentRepository.findByBooking_BookingId(booking.getBookingId());
                if (payment != null && "SUCCEEDED".equalsIgnoreCase(payment.getStatus())) {
                    totalEarned += payment.getAmount();
                }
            }
        }

        // 2. Sum all payouts (PAID or PROCESSING) for this tutor
        List<PayoutEntity> payouts = payoutRepository.findByTutorProfile(tutorProfile);
        float totalPayouts = 0f;
        if (payouts != null) {
            for (PayoutEntity payout : payouts) {
                if ("PAID".equalsIgnoreCase(payout.getStatus()) ||
                    "PROCESSING".equalsIgnoreCase(payout.getStatus())) {
                    totalPayouts += payout.getAmount();
                }
            }
        }

        return totalEarned - totalPayouts;
    }

    @Transactional
    public PayoutEntity requestPayout(int tutorProfileId, float amount) {

        TutorProfileEntity tutorProfile = tutorProfileRepository.findById(tutorProfileId)
                .orElseThrow(() -> new RuntimeException("TutorProfile not found with id: " + tutorProfileId));

        float available = getAvailableBalance(tutorProfileId);

        if (amount < MINIMUM_PAYOUT) {
            throw new RuntimeException("Below minimum payout amount of " + MINIMUM_PAYOUT);
        }

        if (amount > available) {
            throw new RuntimeException("Insufficient balance. Available: " + available);
        }

        PayoutEntity payout = new PayoutEntity();
        payout.setTutorProfile(tutorProfile);
        payout.setAmount(amount);
        payout.setStatus("REQUESTED");
        payout.setCreatedAt(LocalDateTime.now().toString());
        payout.setProcessedAt(null);

        return payoutRepository.save(payout);
    }
}
