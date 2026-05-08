package com.example.hotelbooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.hotelbooking.entity.Admin;
import com.example.hotelbooking.repository.AdminRepository;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    // Adjusted the parameter type from int to String to match the Admin id type
    public Admin getAdminById(String id) {
        Optional<Admin> admin = adminRepository.findById(id);
        if (admin.isPresent()) {
            return admin.get();
        } else {
            // Handle the case where no admin is found
            throw new RuntimeException("Admin not found for id: " + id);
        }
    }

    public Admin addAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    // Method to update an existing admin
    public Admin updateAdmin(String id, Admin updatedAdmin) {
        Admin admin = getAdminById(id); // This will throw if the admin does not exist
        admin.setName(updatedAdmin.getName());
        admin.setEmail(updatedAdmin.getEmail());
        admin.setPass(updatedAdmin.getPass());
        admin.setStatus(updatedAdmin.getStatus());
        return adminRepository.save(admin);
    }

    // Method to delete an admin
    public void deleteAdmin(String id) {
        Admin admin = getAdminById(id); // This will throw if the admin does not exist
        adminRepository.delete(admin);
    }
}
