package com.example.hotelbooking.service;

import com.example.hotelbooking.entity.Guest;
import com.example.hotelbooking.repository.GuestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GuestService {
    @Autowired
    private GuestRepository guestRepository;

    public List<Guest> getAllGuests() {
        return guestRepository.findAll();
    }

    // Updated to use String as the ID type
    public Guest getGuestById(String id) {
        return guestRepository.findById(id).orElse(null);
    }

    public Guest addGuest(Guest guest) {
        return guestRepository.save(guest);
    }

    // Method to get the display info for a specific guest, updated to use String
    public String getDisplayInfoById(String id) {
        Optional<Guest> guest = guestRepository.findById(id);
        return guest.map(Guest::displayInfo).orElse("Guest not found");
    }
}
