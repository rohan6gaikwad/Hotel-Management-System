package com.example.hotelbooking.service;

import com.example.hotelbooking.entity.Booking;
import com.example.hotelbooking.repository.BookingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookingById(String id) {
        return bookingRepository.findById(id).orElse(null);
    }

    public Booking addBooking(Booking booking) {

        if (booking.getBookingID() == null || booking.getBookingID().trim().isEmpty()) {
            booking.setBookingID(generateBookingID());
        }
        return bookingRepository.save(booking);
    }

    public void deleteBooking(String id) {
        if (bookingRepository.existsById(id)) {
            bookingRepository.deleteById(id);
        } else {
            throw new RuntimeException("Booking with ID " + id + " Not found");
        }
    }

    public void updateBooking(Booking booking) {
        bookingRepository.save(booking);
    }

    public void updateBookingStatuses() {
        LocalDate today = LocalDate.now();

        List<Booking> bookings = bookingRepository.findAll();

        for (Booking booking : bookings) {
            LocalDate checkoutDate = booking.getCheckout().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            if (checkoutDate.isBefore(LocalDate.now())) {
                booking.setStatus("FINISHED");
                bookingRepository.save(booking);
            }
        }
    }

    public double getTotalRevenue() {
        return bookingRepository.findAll().stream()
                .mapToDouble(Booking::getPayment)
                .sum();
    }

    public long countActiveBookings() {
        return bookingRepository.findAll().stream()
                .filter(b -> b.getStatus().equalsIgnoreCase("BOOKED"))
                .count();
    }

    public long countFinishedBookings() {
        return bookingRepository.findAll().stream()
                .filter(b -> b.getStatus().equalsIgnoreCase("FINISHED"))
                .count();
    }

    // Generate Booking ID dengan format BK001, BK002, dst.
    private String generateBookingID() {
        long count = bookingRepository.count() + 1;
        return String.format("BK%03d", count);
    }
}
