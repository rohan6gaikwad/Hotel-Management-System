package com.example.hotelbooking.controller;

import com.example.hotelbooking.entity.Guest;
import com.example.hotelbooking.service.AuthGuestService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/guest")
public class AuthGuestController {

    @Autowired
    private AuthGuestService authGuestService;

    // Halaman Login
    @GetMapping("/login")
    public String loginPage() {
        return "guestlogin";
    }

    // Proses Login
    @PostMapping("/login")
    public String loginGuest(@RequestParam("logemail") String email,
            @RequestParam("logpass") String pass,
            HttpSession session,
            RedirectAttributes redirectAttributes) {

        Guest guest = authGuestService.login(email, pass);

        if (guest != null) {

            session.setAttribute("loggedGuest", guest);


            redirectAttributes.addFlashAttribute("infoMessage", guest.displayInfo());

            return "redirect:/guest/dashboard";

        } else {

            redirectAttributes.addFlashAttribute("errorMessage", "The email or password is incorrect!");
            return "redirect:/guest/login";
        }
    }


    @GetMapping("/signup")
    public String signUpPage() {
        return "guestsignup";
    }

    // Proses Sign Up
    @PostMapping("/signup")
    public String signUp(@RequestParam("logname") String name,
            @RequestParam("logemail") String email,
            @RequestParam("logpass") String password,
            @RequestParam("address") String address,
            @RequestParam("phone") String phone,
            RedirectAttributes redirectAttributes) {
        try {

            Guest newGuest = new Guest();
            newGuest.setName(name);
            newGuest.setEmail(email);
            newGuest.setPass(password);
            newGuest.setAddress(address);
            newGuest.setPhone(phone);


            authGuestService.signup(newGuest);


            redirectAttributes.addFlashAttribute("infoMessage", "Account created successfully. Please log in.");
            return "redirect:/guest/login";
        } catch (IllegalArgumentException e) {

            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/guest/signup";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "An error occurred while creating the account. Please try again.");
            return "redirect:/guest/signup";
        }
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        return "guestdashboard";
    }

}
