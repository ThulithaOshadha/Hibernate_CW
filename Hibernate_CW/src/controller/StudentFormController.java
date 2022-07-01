package controller;

import bo.BOFactory;
import bo.custom.RoomBO;
import bo.custom.StudentBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.RoomDTO;
import dto.StudentDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.ArrayList;

public class StudentFormController {
    public JFXTextField txtStudentId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContactNo;
    public JFXTextField txtDob;
    public JFXComboBox<String> cmbGender;
    public JFXButton btnAddStudent;
    public TableView<StudentDTO> tblStudent;
    public JFXButton btnDelete;

    private StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);

    public void initialize(){


        btnDelete.setDisable(true);
        tblStudent.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("Sid"));
        tblStudent.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblStudent.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblStudent.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        tblStudent.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("dob"));
        tblStudent.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("gender"));

        loadTable();
        loadCombo();
        cmbGender.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

        });

        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnAddStudent.setText(newValue != null ? "Update" : "Save");
            btnDelete.setDisable(false);

            if (newValue != null) {
                txtStudentId.setText(newValue.getSid());
                txtName.setText(newValue.getName());
                txtAddress.setText(newValue.getAddress());
                txtContactNo.setText(newValue.getContactNo());
                txtDob.setText(newValue.getDob());

            }
        });
    }

    private void loadCombo() {
        ObservableList<String> cmb = FXCollections.observableArrayList();
        cmb.add("Male");
        cmb.add("Female");
        cmbGender.setItems(cmb);

    }

    private void loadTable() {
        try {
            /*Get all items*/
            ArrayList<StudentDTO> allStudent = studentBO.getAllStudent();
            btnDelete.setDisable(true);
            for (StudentDTO student : allStudent) {
                //Button btn = new Button("Delete");
                tblStudent.getItems().add(new StudentDTO(student.getSid(), student.getName(), student.getAddress(), student.getContactNo(),
                        student.getDob(), student.getGender()));

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void addStudentOnAction(ActionEvent actionEvent) {
        String id = txtStudentId.getText();
        String name= txtName.getText();
        String address = txtAddress.getText();
        String contactNo = txtContactNo.getText();
        String dob = txtDob.getText();
        String gender = cmbGender.getValue();

        if (btnAddStudent.getText().equals("Save")) {
            try {
                if (studentBO.addStudent(new StudentDTO(id, name, address, contactNo,dob,gender))) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Saved.!").show();
                    //clearFields();
                }
            } catch (Exception e) {
                System.out.println(e);
                new Alert(Alert.AlertType.ERROR, "Something Happened. try again carefully!").showAndWait();
            }
        }else {
            try {
                if (studentBO.updateStudent(new StudentDTO(id, name, address, contactNo,dob,gender))) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Update.!").show();
                    btnAddStudent.setText("Save");
                    tblStudent.refresh();
                    //clearFields();
                }
            } catch (Exception e) {
                System.out.println(e);
                new Alert(Alert.AlertType.ERROR, "Something Happened. try again carefully!").showAndWait();
            }
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
    }
}
