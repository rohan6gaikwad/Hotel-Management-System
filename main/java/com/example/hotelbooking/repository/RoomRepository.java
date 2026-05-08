package com.example.hotelbooking.repository;

import com.example.hotelbooking.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    List<Room> findByIsAvailableTrue();

    Room findFirstByRoomTypeAndIsAvailableTrue(String roomType);

    Room findByRoomNo(int roomNo);

}