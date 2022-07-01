package bo.custom.impl;

import bo.custom.MakeRegistrationBO;
import dao.DAOFactory;
import dao.custom.MakeRegistrationDAO;
import dao.custom.RoomDAO;
import dto.ReservationDTO;
import dto.RoomDTO;
import entity.Reservation;
import entity.Room;

import java.sql.SQLException;
import java.util.ArrayList;

public class MakeRegistrationBOImpl implements MakeRegistrationBO {
    private final MakeRegistrationDAO makeRegistrationDAO = (MakeRegistrationDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RESERVATION);

    @Override
    public ArrayList<ReservationDTO> getAllResevation() throws SQLException, ClassNotFoundException {
        ArrayList<ReservationDTO> resList = new ArrayList<>();
        ArrayList<Reservation> all = makeRegistrationDAO.getAll();
        for (Reservation res: all) {
            resList.add(new ReservationDTO(res.getResID(), res.getDate(), res.getSid(), res.getRoomID(), res.getStatus()));
        }
        return resList;
    }

    @Override
    public boolean addResevation(ReservationDTO reservationDTO) throws Exception {
        return makeRegistrationDAO.save(new Reservation(
                reservationDTO.getResID(),
                reservationDTO.getDate(),
                reservationDTO.getSid(),
                reservationDTO.getRoomID(),
                reservationDTO.getStatus()
        ));
    }

    @Override
    public boolean updateReservation(ReservationDTO reservationDTO) throws Exception {
        return makeRegistrationDAO.update(new Reservation(
                reservationDTO.getResID(),
                reservationDTO.getDate(),
                reservationDTO.getSid(),
                reservationDTO.getRoomID(),
                reservationDTO.getStatus()
        ));
    }

    @Override
    public boolean deleteReservation(String id) throws Exception {
        return makeRegistrationDAO.delete(id);
    }




}
