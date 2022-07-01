package controller;

import bo.BOFactory;
import bo.custom.MakeRegistrationBO;
import bo.custom.RoomBO;
import bo.custom.StudentBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.ReservationDTO;
import dto.RoomDTO;
import dto.StudentDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.ArrayList;

import static sun.net.www.MimeTable.loadTable;

public class ReservationDetailsFormController {
    public TableView<ReservationDTO> tblReservation;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXTextField txtResId;
    public JFXTextField txtDate;
    public JFXTextField txtRoomId;
    public JFXTextField txtStudentId;
    public JFXTextField txtStatus;
    public Label lblId;
    public Label lblName;
    public Label lblAddress;
    public Label lblConNo;
    public Label lblDob;
    public Label lblGender;


    private MakeRegistrationBO makeRegistrationBO = (MakeRegistrationBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RESERVATION);
    private StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STUDENT);

    public void initialize(){
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        tblReservation.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("resID"));
        tblReservation.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("date"));
        tblReservation.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Sid"));
        tblReservation.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("roomID"));
        tblReservation.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("status"));

        loadTble();

        tblReservation.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            //btnUpdate.setText(newValue != null ? "Update" : "Save");
            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);

            if (newValue != null) {
                txtResId.setText(newValue.getResID());
                txtDate.setText(newValue.getDate());
                txtRoomId.setText(newValue.getRoomID());
                txtStudentId.setText(newValue.getSid());
                txtStatus.setText(newValue.getStatus());
                setStudentDetails(newValue.getSid());

            }
        });
    }

    private void setStudentDetails(String sid) {
        try {

            StudentDTO search = studentBO.searchStudent(sid);
            lblId.setText(search.getSid());
            lblName.setText(search.getName());
            lblAddress.setText(search.getAddress());
            lblConNo.setText(search.getContactNo());
            lblDob.setText(search.getDob());
            lblGender.setText(search.getGender());

        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to find the student " +e).show();
        }
    }

    private void loadTble() {
        try {
            /*Get all items*/
            ArrayList<ReservationDTO> allResevation = makeRegistrationBO.getAllResevation();
            btnDelete.setDisable(true);
            for (ReservationDTO res : allResevation) {
                tblReservation.getItems().add(new ReservationDTO(res.getResID(), res.getDate(), res.getSid(), res.getRoomID(), res.getStatus()));

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateOnAction(ActionEvent actionEvent) {
        String Resid = txtResId.getText();
        String date = txtDate.getText();
        String Sid = txtStudentId.getText();
        String roomID = txtRoomId.getText();
        String payment = txtStatus.getText();
        try {
            if (makeRegistrationBO.updateReservation(new ReservationDTO(Resid,date,Sid,roomID,payment))) {
                new Alert(Alert.AlertType.CONFIRMATION, "Update.!").show();
                clearFields();
            }
        } catch (Exception e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "Something Happened. try again carefully!").showAndWait();
        }
    }

    private void clearFields() {
        txtResId.clear();
        txtDate.clear();
        txtStudentId.clear();
        txtRoomId.clear();
        txtStatus.clear();
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String id = txtResId.getText();
        try {
            if (makeRegistrationBO.deleteReservation(id)) {
                new Alert(Alert.AlertType.CONFIRMATION, "DELETED").show();
                tblReservation.refresh();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
