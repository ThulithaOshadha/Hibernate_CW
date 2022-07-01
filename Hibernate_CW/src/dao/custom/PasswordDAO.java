package dao.custom;

import dao.SuperDAO;
import entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PasswordDAO extends SuperDAO<User,String> {
    ArrayList<User> getAll() throws SQLException, ClassNotFoundException;
    boolean save(User dto) throws SQLException, ClassNotFoundException;
    boolean update(User dto) throws SQLException, ClassNotFoundException;
}
