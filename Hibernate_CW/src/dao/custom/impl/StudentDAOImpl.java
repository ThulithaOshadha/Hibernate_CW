package dao.custom.impl;

import dao.custom.StudentDAO;
import dto.StudentDTO;
import entity.Room;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import javax.swing.text.html.parser.Entity;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public ArrayList<Student> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql = "FROM student";  // need to add JPA Facet to the Module
        Query query = session.createQuery(hql);
        List<Student> studentList = query.list();

        ArrayList arrayList = new ArrayList();

        for (Student student : studentList) {
                arrayList.add(student);
        }

        transaction.commit();
        session.close();


        return arrayList;
    }

    @Override
    public boolean save(Student dto) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(dto);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Student dto) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(dto);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public Student search(String code) throws SQLException, ClassNotFoundException {


        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        /*String hql = "FROM student WHERE Sid = : student_Sid";

        Query query = session.createQuery(hql);
        query.setParameter("student_Sid", code);



        List<Student> studentList = query.list();
        ArrayList arrayList = new ArrayList();

        for (Student student : studentList) {
            //arrayList.add(student);
            studentList.get(0);
            return new Student(student);

        }*/

       Student s1 = session.get(Student.class, code);



        transaction.commit();
        session.close();
        return s1;
    }

    @Override
    public boolean exist(String code) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Student student = session.load(Student.class, code);

        session.delete(student);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
