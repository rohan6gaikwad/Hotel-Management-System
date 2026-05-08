package com.example.hotelbooking.controller;

import com.example.hotelbooking.entity.Admin;
import com.example.hotelbooking.service.AuthAdminService;
// import com.example.hotelbooking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthAdminController {

    @Autowired
    private AuthAdminService authAdminService;


    @GetMapping("/adminlogin")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
            Model model) {

        if (error != null) {
            model.addAttribute("error", "The email or password is incorrect. Please try again.");
        }
        return "adminlogin";
    }

    // Proses login
    @PostMapping("/adminlogin")
    public String login(@RequestParam("email") String email,
            @RequestParam("password") String password,
            RedirectAttributes redirectAttributes) {

        Admin admin = authAdminService.login(email, password);

        if (admin != null) {
            redirectAttributes.addFlashAttribute("infoMessage", admin.displayInfo());
            return "redirect:/admin/dashboard";
        } else {
            redirectAttributes.addFlashAttribute("error", "The email or password is incorrect. Please try again..");
            redirectAttributes.addFlashAttribute("email", email);
            return "redirect:/adminlogin";
        }
    }

    // Dashboard admin: tarik data booking
    @GetMapping("/dashboard")
    public String panel() {
        return "redirect:/admin/dashboard";
    }

}
