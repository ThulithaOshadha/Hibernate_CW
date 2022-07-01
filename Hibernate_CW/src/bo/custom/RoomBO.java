package bo.custom;

import bo.SuperBO;
import dto.RoomDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RoomBO extends SuperBO {

    ArrayList<RoomDTO> getAllRoom() throws SQLException, ClassNotFoundException;
    boolean addRoom(RoomDTO roomDTO) throws Exception;

    boolean updateRoom(RoomDTO roomDTO) throws Exception;

    boolean deleteRoom(String id) throws Exception;
}
