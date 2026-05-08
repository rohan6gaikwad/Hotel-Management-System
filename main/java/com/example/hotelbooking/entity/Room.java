package com.example.hotelbooking.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @Column(name = "room_no")
    private int roomNo;

    @Column(name = "is_available")
    private boolean isAvailable;

    @Column(name = "room_type")
    private String roomType;


    public Room() {
    }


    public Room(int roomNo, boolean isAvailable, String roomType) {
        this.roomNo = roomNo;
        this.isAvailable = isAvailable;
        this.roomType = roomType;
    }


    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }
}
