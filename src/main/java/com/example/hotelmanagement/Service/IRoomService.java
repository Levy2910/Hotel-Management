package com.example.hotelmanagement.Service;

import com.example.hotelmanagement.Model.Room;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

public interface IRoomService {
    Room addNewRoom(MultipartFile photo, String roomType, BigDecimal roomPrice) throws IOException, SQLException;
    Boolean removeRoom(Long roomID);

    Boolean updateRoom(Long roomID, Object...param);
}
