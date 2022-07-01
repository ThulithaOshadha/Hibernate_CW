package bo.custom.impl;

import bo.custom.RoomBO;
import dao.DAOFactory;
import dao.custom.RoomDAO;
import dto.RoomDTO;
import entity.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.FactoryConfiguration;

import java.sql.SQLException;
import java.util.ArrayList;

public class RoomBOImpl implements RoomBO {

    private final RoomDAO roomDAO = (RoomDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ROOM);

    @Override
    public ArrayList<RoomDTO> getAllRoom() throws SQLException, ClassNotFoundException {
        ArrayList<RoomDTO> allRoom = new ArrayList<>();
        ArrayList<Room> all = roomDAO.getAll();
        for (Room room: all) {
            allRoom.add(new RoomDTO(room.getId(), room.getType(), room.getKeyMoney(), room.getQty()));
        }
        return allRoom;
    }

    @Override
    public boolean addRoom(RoomDTO roomDTO) throws Exception {
        return roomDAO.save(new Room(
                roomDTO.getRoomID(),
                roomDTO.getType(),
                roomDTO.getKeyMoney(),
                roomDTO.getRoomQty()
        ));
    }

    @Override
    public boolean updateRoom(RoomDTO roomDTO) throws Exception {
        return roomDAO.update(new Room(
                roomDTO.getRoomID(),
                roomDTO.getType(),
                roomDTO.getKeyMoney(),
                roomDTO.getRoomQty()
        ));
    }

    @Override
    public boolean deleteRoom(String id) throws Exception {
        return roomDAO.delete(id);
    }
}
