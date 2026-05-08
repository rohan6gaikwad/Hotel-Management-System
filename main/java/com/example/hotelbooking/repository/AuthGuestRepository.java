package com.example.hotelbooking.repository;

import com.example.hotelbooking.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthGuestRepository extends JpaRepository<Guest, String> {
    Guest findByEmailAndPass(String email, String pass);

    boolean existsByEmail(String email);
}
