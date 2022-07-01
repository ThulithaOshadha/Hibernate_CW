package bo.custom;

import bo.SuperBO;
import dto.ReservationDTO;
import dto.RoomDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MakeRegistrationBO extends SuperBO {
    ArrayList<ReservationDTO> getAllResevation() throws SQLException, ClassNotFoundException;
    boolean addResevation(ReservationDTO reservationDTO) throws Exception;

    boolean updateReservation(ReservationDTO reservationDTO) throws Exception;

    boolean deleteReservation(String id) throws Exception;


}
