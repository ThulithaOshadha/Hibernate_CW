package bo.custom;

import bo.SuperBO;
import dto.RoomDTO;
import dto.UserDTO;
import entity.User;

import javax.jws.soap.SOAPBinding;
import java.sql.SQLException;
import java.util.ArrayList;

public interface PasswordBO extends SuperBO {
    ArrayList<UserDTO> getAllRoom() throws SQLException, ClassNotFoundException;

    boolean addPassword(UserDTO userDTO) throws Exception;

    boolean updatePassword(UserDTO userDTO) throws Exception;
}
