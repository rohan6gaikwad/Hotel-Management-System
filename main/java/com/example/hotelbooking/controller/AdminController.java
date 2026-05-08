package com.example.hotelbooking.controller;

import com.example.hotelbooking.entity.Admin;
import com.example.hotelbooking.entity.Booking;
import com.example.hotelbooking.service.AdminService;
import com.example.hotelbooking.service.BookingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final BookingService bookingService;

    public AdminController(AdminService adminService, BookingService bookingService) {
        this.adminService = adminService;
        this.bookingService = bookingService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {



        model.addAttribute("bookings", bookingService.getAllBookings());
        model.addAttribute("totalRevenue", bookingService.getTotalRevenue());
        model.addAttribute("activeBookings", bookingService.countActiveBookings());
        model.addAttribute("finishedBookings", bookingService.countFinishedBookings());

        return "dashboard";
    }

    @PostMapping("/admins")
    public String addAdmin(@ModelAttribute Admin admin) {
        adminService.addAdmin(admin);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/bookings")
    public String addBooking(@ModelAttribute Booking booking) {
        bookingService.addBooking(booking);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/bookings/delete/{id}")
    public String deleteBooking(@PathVariable String id) {
        bookingService.deleteBooking(id);
        return "redirect:/admin/dashboard";
    }


    @GetMapping("/bookings/edit/{id}")
    public String editBooking(@PathVariable String id, Model model) {
        Booking booking = bookingService.getBookingById(id);

        if (booking == null) {
            model.addAttribute("error", "Booking not found!");
            return "redirect:/admin/dashboard";
        }

        model.addAttribute("booking", booking);
        return "edit-booking";
    }

    @PostMapping("/bookings/update")
    public String updateBooking(@ModelAttribute Booking booking) {
        bookingService.updateBooking(booking);
        return "redirect:/admin/dashboard";
    }

}
