package dao;

import dao.custom.impl.MakeRegistrationDAOImpl;
import dao.custom.impl.PasswordDAOImpl;
import dao.custom.impl.RoomDAOImpl;
import dao.custom.impl.StudentDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    public static DAOFactory getDaoFactory(){
        if (daoFactory==null){
            return new DAOFactory();
        }
        return daoFactory;
    }
    public enum DAOTypes{
        ROOM,STUDENT,RESERVATION,PASSWORD;
    }
    public SuperDAO getDAO(DAOTypes types){
        switch (types){
            case ROOM:
                return new RoomDAOImpl();
            case STUDENT:
                return new StudentDAOImpl();
            case RESERVATION:
                return new MakeRegistrationDAOImpl();
            case PASSWORD:
                return new PasswordDAOImpl();
            default:
                return null;
        }
    }
}
