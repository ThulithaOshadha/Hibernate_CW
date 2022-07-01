package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainFormController {
    public JFXButton btnManageRooms;
    public JFXButton btnMakeRegist;
    public AnchorPane subContext;
    public JFXButton btnResDetails;

    public void manageRoomsOnAction(ActionEvent actionEvent) throws IOException {
        //setButtonColour(makeCustOrderBtn);
        subContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/ManageRoomForm.fxml"));
        subContext.getChildren().add(parent);
    }

    public void makeRegistOnAction(ActionEvent actionEvent) throws IOException {
        subContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/MakeRegistrationForm.fxml"));
        subContext.getChildren().add(parent);
    }


    public void resDetailsOnAction(ActionEvent actionEvent) throws IOException {
        subContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/ReservationDetailsForm.fxml"));
        subContext.getChildren().add(parent);
    }

    public void changePasswordOnAction(ActionEvent actionEvent) throws IOException {
        subContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/PasswordForm.fxml"));
        subContext.getChildren().add(parent);
    }
}
