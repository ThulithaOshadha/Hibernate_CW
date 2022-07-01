package controller;

import bo.BOFactory;
import bo.custom.PasswordBO;
import bo.custom.RoomBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.RoomDTO;
import dto.UserDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class PasswordFormController {
    public JFXTextField txtOldUserName;
    public JFXTextField txtOldPassword;
    public JFXTextField txtNewUserName;
    public JFXTextField txtNewPassword;
    public JFXButton btnConformOld;
    public JFXButton btnSaveNew;

    private PasswordBO passwordBO = (PasswordBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PASSWORD);

    public void initialize(){
        btnSaveNew.setDisable(true);
        txtNewUserName.setEditable(false);
        txtNewPassword.setEditable(false);
    }

    public void oldConfirmationOnAction(ActionEvent actionEvent) {
        String userName = "ss";
        String password = "ghjk";
        try {
            /*Get all items*/
            ArrayList<UserDTO> allUser = passwordBO.getAllRoom();
            //btnDelete.setDisable(true);
            for (UserDTO user : allUser) {
                //Button btn = new Button("Delete");
                userName = user.getUserName();
                password = user.getPassword();

                if (txtOldUserName.getText().equals(userName) && txtOldPassword.getText().equals(password)){
                    btnSaveNew.setDisable(false);
                    txtNewUserName.setEditable(true);
                    txtNewPassword.setEditable(true);
                }else {
                    new Alert(Alert.AlertType.ERROR,"Wrong User Name or Password").show();
                }

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void saveNewOnAction(ActionEvent actionEvent) {
        String userName = txtNewUserName.getText();
        String password = txtNewPassword.getText();
        try {
            if (passwordBO.addPassword(new UserDTO(userName, password))) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved.!").show();
                //clearFields();
            }
        } catch (Exception e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "Something Happened. try again carefully!").showAndWait();
        }
    }
}
