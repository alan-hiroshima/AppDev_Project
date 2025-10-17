package com.example.appdevf2.paraderooct17.Service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.appdevf2.paraderooct17.Entity.BookingEntity;
import com.example.appdevf2.paraderooct17.Repository.BookingRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public List<BookingEntity> getAllBookings() {
        return bookingRepository.findAll();
    }

    public BookingEntity getBookingById(int id) {
        return bookingRepository.findById(id).orElse(null);
    }

    public BookingEntity saveBooking(BookingEntity booking) {
        return bookingRepository.save(booking);
    }

    public void deleteBooking(int id) {
        bookingRepository.deleteById(id);
    }
}
