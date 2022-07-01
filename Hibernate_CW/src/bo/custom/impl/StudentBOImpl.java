package bo.custom.impl;

import bo.custom.StudentBO;
import dao.DAOFactory;
import dao.custom.RoomDAO;
import dao.custom.StudentDAO;
import dto.RoomDTO;
import dto.StudentDTO;
import entity.Room;
import entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentBOImpl implements StudentBO {
    private final StudentDAO studentDAO = (StudentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STUDENT);

    @Override
    public ArrayList<StudentDTO> getAllStudent() throws SQLException, ClassNotFoundException {
        ArrayList<StudentDTO> allStudent = new ArrayList<>();
        ArrayList<Student> all = studentDAO.getAll();
        for (Student student: all) {
            allStudent.add(new StudentDTO(student.getSid(), student.getName(), student.getAddress(), student.getContactNo(), student.getDob(),
                    student.getGender()));
        }
        return allStudent;
    }

    @Override
    public StudentDTO searchStudent(String id) throws SQLException, ClassNotFoundException {
        Student s = studentDAO.search(id);
        return new StudentDTO(s.getSid(),s.getName(),s.getAddress(),s.getContactNo(),s.getDob(),s.getGender());
    }

    @Override
    public boolean addStudent(StudentDTO studentDTO) throws Exception {
        return studentDAO.save(new Student(
                studentDTO.getSid(),
                studentDTO.getName(),
                studentDTO.getAddress(),
                studentDTO.getContactNo(),
                studentDTO.getContactNo(),
                studentDTO.getGender()
        ));
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) throws Exception {
        return studentDAO.update(new Student(
                studentDTO.getSid(),
                studentDTO.getName(),
                studentDTO.getAddress(),
                studentDTO.getContactNo(),
                studentDTO.getContactNo(),
                studentDTO.getGender()
        ));
    }

    @Override
    public boolean deleteStudent(String id) throws Exception {
        return studentDAO.delete(id);
    }
}
