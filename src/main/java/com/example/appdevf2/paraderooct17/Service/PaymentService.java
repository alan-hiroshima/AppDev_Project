package com.example.appdevf2.paraderooct17.Service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.appdevf2.paraderooct17.Entity.BookingEntity;
import com.example.appdevf2.paraderooct17.Entity.PaymentEntity;
import com.example.appdevf2.paraderooct17.Repository.BookingRepository;
import com.example.appdevf2.paraderooct17.Repository.PaymentRepository;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;

    public PaymentService(PaymentRepository paymentRepository,
                          BookingRepository bookingRepository) {
        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
    }

    @Transactional
    public PaymentEntity chargeBooking(int bookingId, String method) {

        BookingEntity booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + bookingId));

        // For now, pretend payment always succeeds:
        PaymentEntity payment = new PaymentEntity();
        payment.setBooking(booking);
        payment.setAmount(booking.getPrice());
        payment.setMethod(method);
        payment.setStatus("SUCCEEDED");
        payment.setProviderRef("DUMMY-REF-" + bookingId);
        payment.setCreatedAt(LocalDateTime.now().toString());
        payment.setUpdatedAt(LocalDateTime.now().toString());

        booking.setStatus("CONFIRMED"); // or keep PENDING if you want tutor confirmation
        booking.setUpdatedAt(LocalDateTime.now().toString());

        return paymentRepository.save(payment);
    }

    @Transactional
    public PaymentEntity refundBooking(int bookingId) {

        PaymentEntity payment = paymentRepository.findByBooking_BookingId(bookingId);
        if (payment == null) {
            throw new RuntimeException("No payment found for booking id: " + bookingId);
        }

        // In real life: call gateway refund here
        payment.setStatus("REFUNDED");
        payment.setUpdatedAt(LocalDateTime.now().toString());

        return paymentRepository.save(payment);
    }
}
