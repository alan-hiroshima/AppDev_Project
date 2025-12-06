package com.example.appdevf2.paraderooct17.Repository;

import com.example.appdevf2.paraderooct17.Entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Integer> {

    List<MessageEntity> findByBooking_BookingIdOrderBySentAtAsc(int bookingId);
}
