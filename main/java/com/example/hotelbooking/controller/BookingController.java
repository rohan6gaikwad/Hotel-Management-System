package com.example.hotelbooking.controller;

import com.example.hotelbooking.entity.Booking;
import com.example.hotelbooking.entity.Guest;
import com.example.hotelbooking.entity.Room;
import com.example.hotelbooking.service.BookingService;
import com.example.hotelbooking.service.RoomService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Controller
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private RoomService roomService;

    // =============================
    // 🔹 FORM ADD BOOKING
    // =============================
    @GetMapping("/add")
    public String showAddBookingForm(
            @RequestParam(required = false) String roomType,
            @RequestParam(required = false) String guestID,
            @RequestParam(required = false) Integer roomNo,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkin,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date checkout,
            HttpSession session,
            Model model) {

        Booking booking = new Booking();


        if (guestID != null) {
            booking.setGuestID(guestID);
        } else {
            Guest loggedGuest = (Guest) session.getAttribute("loggedGuest");
            if (loggedGuest != null) {
                booking.setGuestID(loggedGuest.getId());
            }
        }


        if (roomNo != null && roomNo > 0) {
            booking.setRoomNo(roomNo);
        } else if (roomType != null && !roomType.isEmpty()) {

            Room availableRoom = roomService.findFirstAvailableRoomByType(roomType);
            if (availableRoom != null) {
                booking.setRoomNo(availableRoom.getRoomNo());
            }
        }


        if (checkin != null)
            booking.setCheckin(checkin);
        if (checkout != null)
            booking.setCheckout(checkout);

        model.addAttribute("booking", booking);
        return "booking-form";
    }

    @GetMapping("/admin/dashboard")
    public String showDashboard(Model model) {
        bookingService.updateBookingStatuses(); // auto update status
        model.addAttribute("bookings", bookingService.getAllBookings());
        return "dashboard";
    }


    @PostMapping("/confirm")
    public String confirmBooking(@ModelAttribute("booking") Booking booking, Model model) {
        if (booking.getCheckin() != null && booking.getCheckout() != null) {
            LocalDate checkinDate = booking.getCheckin().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate checkoutDate = booking.getCheckout().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            long daysBetween = ChronoUnit.DAYS.between(checkinDate, checkoutDate);
            if (daysBetween < 1)
                daysBetween = 1;

            Room bookedRoom = roomService.getRoomByRoomNo(booking.getRoomNo());
            double pricePerNight = 0;

            if (bookedRoom != null) {
                switch (bookedRoom.getRoomType().toLowerCase()) {
                    case "standard":
                        pricePerNight = 100000;
                        break;
                    case "deluxe":
                        pricePerNight = 200000;
                        break;
                    case "suite":
                        pricePerNight = 300000;
                        break;
                }
            }

            double totalPayment = daysBetween * pricePerNight;
            booking.setPayment(totalPayment);
            model.addAttribute("daysBetween", daysBetween);
        }

        model.addAttribute("booking", booking);
        return "confirm-booking";
    }


    @PostMapping("/save")
    public String saveBooking(@ModelAttribute("booking") Booking booking, Model model) {
        booking.setStatus("BOOKED");
        bookingService.addBooking(booking);
        roomService.updateRoomAvailability(booking.getRoomNo(), false);
        return "booking-success";
    }

}
