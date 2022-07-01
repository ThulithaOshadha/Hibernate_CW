package bo.custom;

import bo.SuperBO;
import dto.RoomDTO;
import dto.StudentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentBO extends SuperBO {
    ArrayList<StudentDTO> getAllStudent() throws SQLException, ClassNotFoundException;
    StudentDTO searchStudent(String id) throws SQLException, ClassNotFoundException;

    boolean addStudent(StudentDTO roomDTO) throws Exception;

    boolean updateStudent(StudentDTO roomDTO) throws Exception;

    boolean deleteStudent(String id) throws Exception;
}
