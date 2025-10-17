package com.example.appdevf2.paraderooct17.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.appdevf2.paraderooct17.Entity.BookingEntity;
import com.example.appdevf2.paraderooct17.Service.BookingService;

@RestController
@RequestMapping("/booking")
@CrossOrigin(origins = "*")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public List<BookingEntity> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public BookingEntity getBookingById(@PathVariable int id) {
        return bookingService.getBookingById(id);
    }

    @PostMapping
    public BookingEntity createBooking(@RequestBody BookingEntity booking) {
        return bookingService.saveBooking(booking);
    }

    @PutMapping("/{id}")
    public BookingEntity updateBooking(@PathVariable int id, @RequestBody BookingEntity updatedBooking) {
        BookingEntity existing = bookingService.getBookingById(id);
        if (existing != null) {
            updatedBooking.setBookingId(id);
            return bookingService.saveBooking(updatedBooking);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable int id) {
        bookingService.deleteBooking(id);
    }
}
