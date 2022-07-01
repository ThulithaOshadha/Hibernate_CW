package dao.custom.impl;

import dao.custom.MakeRegistrationDAO;
import entity.Reservation;
import entity.Room;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MakeRegistrationDAOImpl implements MakeRegistrationDAO {

    @Override
    public ArrayList<Reservation> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM reservation ";  // need to add JPA Facet to the Module
        Query query = session.createQuery(hql);
        List<Reservation> reservationList = query.list();

        ArrayList arrayList = new ArrayList();

        for (Reservation reservation : reservationList) {
//
            arrayList.add(reservation);
        }

        transaction.commit();
        session.close();


        return arrayList;
    }

    @Override
    public boolean save(Reservation dto) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(dto);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Reservation dto) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(dto);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Reservation search(String code) throws SQLException, ClassNotFoundException {
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

        Reservation reservation = session.load(Reservation.class, code);

        session.delete(reservation);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        //String hql = "FROM reservation r ORDER BY r.resID setMaxResults";
        //String id = session.createQuery(hql);

        transaction.commit();
        session.close();
        return null;

    }
}
