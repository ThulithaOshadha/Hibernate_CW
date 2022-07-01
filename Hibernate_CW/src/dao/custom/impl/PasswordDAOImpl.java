package dao.custom.impl;

import dao.custom.PasswordDAO;
import entity.Room;
import entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PasswordDAOImpl implements PasswordDAO {
    @Override
    public ArrayList<User> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM user ";  // need to add JPA Facet to the Module
        Query query = session.createQuery(hql);
        List<User> userList = query.list();

        ArrayList arrayList = new ArrayList();

        for (User user : userList) {
//
            arrayList.add(user);
        }

        transaction.commit();
        session.close();


        return arrayList;
    }

    @Override
    public boolean save(User dto) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(dto);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(User dto) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(dto);

        transaction.commit();
        session.close();
        return true;
    }
}
