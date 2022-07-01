package bo.custom.impl;

import bo.custom.PasswordBO;
import dao.DAOFactory;
import dao.custom.PasswordDAO;
import dao.custom.RoomDAO;
import dto.RoomDTO;
import dto.UserDTO;
import entity.Room;
import entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class PasswordBOImpl implements PasswordBO {
    private final PasswordDAO passwordDAO = (PasswordDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PASSWORD);
    @Override
    public ArrayList<UserDTO> getAllRoom() throws SQLException, ClassNotFoundException {
        ArrayList<UserDTO> allUser = new ArrayList<>();
        ArrayList<User> all = passwordDAO.getAll();
        for (User user: all) {
            allUser.add(new UserDTO(user.getUserName(), user.getPassword()));
        }
        return allUser;
    }

    @Override
    public boolean addPassword(UserDTO userDTO) throws Exception {
        return passwordDAO.save(new User(
                userDTO.getUserName(),
                userDTO.getPassword()
        ));
    }

    @Override
    public boolean updatePassword(UserDTO userDTO) throws Exception {
        return passwordDAO.update(new User(
                userDTO.getUserName(),
                userDTO.getPassword()
        ));
    }
}
