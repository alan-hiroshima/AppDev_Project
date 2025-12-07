package com.example.appdevf2.paraderooct17.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.appdevf2.paraderooct17.Entity.PaymentEntity;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Integer> {

    PaymentEntity findByBooking_BookingId(int bookingId);
}
