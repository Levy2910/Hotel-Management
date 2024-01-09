package com.example.hotelmanagement.Controller;

import com.example.hotelmanagement.Model.Room;
import com.example.hotelmanagement.Service.IRoomService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomController {
    private final IRoomService roomService;
    @PostMapping("/add/new-room")
    public ResponseEntity<Room> addNewRoom(
            @RequestParam("photo") String photo,
            @RequestParam("roomType") String roomType,
            @RequestParam("roomPrice") BigDecimal roomPrice
    ) throws SQLException, IOException {
        Room savedRoom = roomService.addNewRoom(photo, roomType, roomPrice);
        return ResponseEntity.ok(savedRoom);
    }
    @DeleteMapping("remove/existing-room/{roomID}")
    public ResponseEntity<String> removeRoom(@PathVariable Long roomID){
       try {
           if (roomService.removeRoom(roomID)){
               return ResponseEntity.ok("Room removed successfully");
           }else{
               return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to remove room.");
           }
       }
       catch(Exception e){
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error:" + e.getMessage());
       }
    }

    @PutMapping("edit/existing-room/{roomID}")
    public ResponseEntity<Room> updateRoom(
            @PathVariable Long roomID,
            @RequestParam("roomPhoto") String roomPhoto,
            @RequestParam("roomPrice") BigDecimal roomPrice,
            @RequestParam("roomType") String roomType
    ) {
        if (roomPhoto.isEmpty() || roomType.isEmpty() || roomPrice == null) {
            return ResponseEntity.badRequest().build();
        }

        Object[] objects = new Object[]{roomPhoto, roomPrice, roomType};
        Room room = roomService.updateRoom(roomID, objects);
        if (room != null) {
            return ResponseEntity.ok(room);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


}
