package controller;

import bo.BOFactory;
import bo.custom.PasswordBO;
import com.jfoenix.controls.JFXTextField;
import dto.RoomDTO;
import dto.UserDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginFormController {

    public AnchorPane txtMainContext;
    public JFXTextField txtUserName;
    public JFXTextField txtPassword;

    private PasswordBO passwordBO = (PasswordBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PASSWORD);


    public void logOnAction(ActionEvent actionEvent) {
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

                if (txtUserName.getText().equals(userName) && txtPassword.getText().equals(password)){
                    txtMainContext.getChildren().clear();
                    Parent parent = FXMLLoader.load(getClass().getResource("../view/MainForm.fxml"));
                    txtMainContext.getChildren().add(parent);
                }else {
                    new Alert(Alert.AlertType.ERROR,"Wrong User Name or Password").show();
                }

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
