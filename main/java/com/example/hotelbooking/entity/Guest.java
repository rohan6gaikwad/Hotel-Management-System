package com.example.hotelbooking.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "guest")
public class Guest extends User {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    private String id;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone", nullable = false)
    private String phone;

    public Guest() {
    }

    public Guest(String id, String name, String email, String pass, String address, String phone) {
        super(name, email, pass);
        this.address = address;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String displayInfo() {
        return "Congratulations " + getName() + ", You have successfully logged in as a Guest!";
    }
}