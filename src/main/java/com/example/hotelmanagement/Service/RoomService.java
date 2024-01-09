package com.example.hotelmanagement.Service;

import com.example.hotelmanagement.Model.Room;
import com.example.hotelmanagement.Repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService implements IRoomService{
    private final RoomRepository roomRepository;

    @Override
    public Room addNewRoom(String photo, String roomType, BigDecimal roomPrice) throws IOException, SQLException {
        Room room = new Room();
        room.setRoomType(roomType);
        room.setRoomPrice(roomPrice);
        room.setPhoto(photo);
        return roomRepository.save(room);
    }

    @Override
    public Boolean removeRoom(Long roomID) {
        if (roomRepository.findById(roomID).isPresent()){
            roomRepository.deleteById(roomID);
            return true;
        }
        return false;
    }

    @Override
    public Room updateRoom(Long roomID, Object[] param) {
        if (param.length < 3) {
            throw new IllegalArgumentException("Insufficient parameters provided");
        }

        Optional<Room> roomOptional = roomRepository.findById(roomID);
        Room foundRoom = roomOptional.orElseThrow(() -> new NoSuchElementException("Room not found"));

        try {
            foundRoom.setPhoto((String) param[0]);
            foundRoom.setRoomPrice((BigDecimal) param[1]);
            foundRoom.setRoomType((String) param[2]);
            return roomRepository.save(foundRoom); // Return the updated room
        } catch (ClassCastException | DataAccessException e) {
            // Handle potential exceptions like ClassCastException or DataAccessException
            throw new IllegalArgumentException("Invalid parameter types or database issue");
        }
    }




}
