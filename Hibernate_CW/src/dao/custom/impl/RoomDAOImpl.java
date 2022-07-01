package dao.custom.impl;

import dao.custom.RoomDAO;
import entity.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {
    @Override
    public ArrayList<Room> getAll() throws SQLException, ClassNotFoundException {

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM room";  // need to add JPA Facet to the Module
        Query query = session.createQuery(hql);
        List<Room> roomList = query.list();

        ArrayList arrayList = new ArrayList();

        for (Room room : roomList) {
//
            arrayList.add(room);
        }

        transaction.commit();
        session.close();


        return arrayList;

    }

    @Override
    public boolean save(Room dto) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(dto);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Room dto) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(dto);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Room search(String code) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean exist(String code) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Room room = session.load(Room.class, code);

        session.delete(room);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
