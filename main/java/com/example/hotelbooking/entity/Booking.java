package com.example.hotelbooking.entity;

import jakarta.persistence.*;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Booking")
public class Booking {

    @Id
    @Column(name = "bookingID")
    private String bookingID;

    @Column(name = "guestID")
    private String guestID;

    @Column(name = "roomNo")
    private int roomNo;

    @Column(name = "checkin")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkin;

    @Column(name = "checkout")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date checkout;

    @Column(name = "payment")
    private double payment;

    @Column(name = "status")
    private String status;

    // Constructor
    public Booking(String bookingID, String guestID, int roomNo, Date checkin, Date checkout, int payment,
            String status) {
        this.bookingID = bookingID;
        this.guestID = guestID;
        this.roomNo = roomNo;
        this.checkin = checkin;
        this.checkout = checkout;
        this.payment = payment;
        this.status = status;
    }

    public Booking() {
    }

    // Getters and Setters
    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public String getGuestID() {
        return guestID;
    }

    public void setGuestID(String guestID) {
        this.guestID = guestID;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public Date getCheckin() {
        return checkin;
    }

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
