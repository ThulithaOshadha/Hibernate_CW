package controller;

import bo.BOFactory;
import bo.custom.MakeRegistrationBO;
import bo.custom.RoomBO;
import bo.custom.StudentBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.ReservationDTO;
import dto.RoomDTO;
import dto.StudentDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class MakeRegistrationFormController {
    public JFXButton btnAddStudent;
    public AnchorPane mainContext;
    public JFXComboBox<String> cmbStudentID;
    public JFXTextField txtStudentId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContactNo;
    public JFXTextField txtDob;
    public JFXComboBox<String> cmbGender;
    public JFXButton btnSave;
    public Label lblresevation;
    public RadioButton radBtnNow;
    public RadioButton radBtnLater;
    public JFXComboBox<String> cmbRoomType;
    public JFXButton btnRegConfirm;

    private StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);
    private RoomBO roomBO = (RoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);
    private MakeRegistrationBO makeRegistrationBO = (MakeRegistrationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RESERVATION);

    public void initialize(){
        loadAllStudendID();
        loadGenderCombo();
        loadRoomType();

        cmbStudentID.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            //enableOrDisablePlaceOrderButton();

            if (newValue != null) {
                btnSave.setDisable(true);
                try {
                    /*Search Customer*/
                   // Connection connection = DBConnection.getDbConnection().getConnection();
                    try {
                        /*if (!existCustomer(newValue + "")) {
//                            "There is no such customer associated with the id " + id
                            new Alert(Alert.AlertType.ERROR, "There is no such customer associated with the id " + newValue + "").show();
                        }*/

                        StudentDTO search = studentBO.searchStudent(newValue + "");
                        txtStudentId.setText(search.getSid());
                        txtName.setText(search.getName());
                        txtAddress.setText(search.getAddress());
                        txtContactNo.setText(search.getContactNo());
                        txtDob.setText(search.getDob());
                        cmbGender.setValue(search.getGender());

                    } catch (SQLException e) {
                        new Alert(Alert.AlertType.ERROR, "Failed to find the customer " + newValue + "" + e).show();
                    }

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                //txt.clear();
            }
        });


    }

    private void loadRoomType() {
        try {
            ArrayList<RoomDTO> all = roomBO.getAllRoom();
            for (RoomDTO roomDTO : all) {
                cmbRoomType.getItems().add(roomDTO.getRoomID());
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load customer ids").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void loadGenderCombo() {
        ObservableList<String> cmb = FXCollections.observableArrayList();
        cmb.add("Male");
        cmb.add("Female");
        cmbGender.setItems(cmb);
    }

    private void loadAllStudendID() {
        try {
            ArrayList<StudentDTO> all = studentBO.getAllStudent();
            for (StudentDTO studentDTO : all) {
                cmbStudentID.getItems().add(studentDTO.getSid());
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load customer ids").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void addStudentOnAction(ActionEvent actionEvent) throws IOException {
        mainContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/StudentForm.fxml"));
        mainContext.getChildren().add(parent);
    }

    public void studentSaveOnAction(ActionEvent actionEvent) {
        String id = txtStudentId.getText();
        String name= txtName.getText();
        String address = txtAddress.getText();
        String contactNo = txtContactNo.getText();
        String dob = txtDob.getText();
        String gender = cmbGender.getValue();
        
            try {
                if (studentBO.addStudent(new StudentDTO(id, name, address, contactNo,dob,gender))) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Saved.!").show();


                    //clearFields();
                }
            } catch (Exception e) {
                System.out.println(e);
                new Alert(Alert.AlertType.ERROR, "Something Happened. try again carefully!").showAndWait();
            }
        
    }

    public void setPaymentNowonAction(ActionEvent actionEvent) {
    }

    public void setPaymentLaterwonAction(ActionEvent actionEvent) {
    }

    public void regConfirmationOnAction(ActionEvent actionEvent) {
        String Resid = "RES-001";
        String date = "2022-11-25";
        String Sid = cmbStudentID.getValue();
        String roomID = cmbRoomType.getValue();
        String payment = "Pay Now";

        if (radBtnNow.isSelected()){
             payment = "Pay Now";
        }else if (radBtnLater.isSelected()){
            payment = "Pay Later";
        }


            try {
                if (makeRegistrationBO.addResevation(new ReservationDTO(Resid, date, Sid, roomID,payment))) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Saved.!").show();


                    //clearFields();
                }
            } catch (Exception e) {
                System.out.println(e);
                new Alert(Alert.AlertType.ERROR, "Something Happened. try again carefully!").showAndWait();
            }

    }
}
