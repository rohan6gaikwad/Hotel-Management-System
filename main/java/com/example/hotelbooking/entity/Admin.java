package com.example.hotelbooking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin extends User {
    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @Column(name = "status")
    private String status;

    public Admin() {
    }

    public Admin(String id, String name, String email, String pass, String status) {
        super(name, email, pass);
        this.id = id;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String displayInfo() {
        return "Congratulations " + getName() + ",  Successfully logged in to the admin dashboard!";
    }
}