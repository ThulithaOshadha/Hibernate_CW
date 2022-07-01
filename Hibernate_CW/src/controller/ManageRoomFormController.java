package controller;

import bo.BOFactory;
import bo.custom.RoomBO;
import bo.custom.impl.RoomBOImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.RoomDTO;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

public class ManageRoomFormController {
    public TableView<RoomDTO> tblRoom;
    public TableColumn colRoomID;
    public TableColumn colRoomType;
    public TableColumn colKeyMoney;
    public TableColumn colQty;
    public JFXTextField txtRoomId;
    public JFXTextField txtRoomType;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtRoomQty;
    public JFXButton btnSave;
    public JFXButton btnDelete;

    private RoomBO roomBO = (RoomBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ROOM);

    public void initialize(){
        btnDelete.setDisable(true);
        tblRoom.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("roomID"));
        tblRoom.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("type"));
        tblRoom.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        tblRoom.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("roomQty"));
        
        loadTable();

        tblRoom.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnSave.setText(newValue != null ? "Update" : "Save");
            btnDelete.setDisable(false);

            if (newValue != null) {
                txtRoomId.setText(newValue.getRoomID());
                txtRoomType.setText(newValue.getType());
                txtKeyMoney.setText(String.valueOf(newValue.getKeyMoney()));
                txtRoomQty.setText(String.valueOf(newValue.getRoomQty()));

            }
        });

    }

    public void loadTable() {
        try {
            /*Get all items*/
            ArrayList<RoomDTO> allRoom = roomBO.getAllRoom();
            btnDelete.setDisable(true);
            for (RoomDTO room : allRoom) {
                //Button btn = new Button("Delete");
                tblRoom.getItems().add(new RoomDTO(room.getRoomID(), room.getType(), room.getKeyMoney(),room.getRoomQty()));

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void saveOnAction(ActionEvent actionEvent) {
        String id = txtRoomId.getText();
        String type = txtRoomType.getText();
        BigDecimal keyMoney = new BigDecimal(txtKeyMoney.getText());
        int qty = Integer.parseInt(txtRoomQty.getText());

        if (btnSave.getText().equals("Save")) {
            try {
                if (roomBO.addRoom(new RoomDTO(id, type, keyMoney, qty))) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Saved.!").show();
                    //clearFields();
                }
            } catch (Exception e) {
                System.out.println(e);
                new Alert(Alert.AlertType.ERROR, "Something Happened. try again carefully!").showAndWait();
            }
        }else {
            try {
                if (roomBO.updateRoom(new RoomDTO(id, type, keyMoney, qty))) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Update.!").show();
                    btnSave.setText("Save");
                    tblRoom.refresh();
                    //clearFields();
                }
            } catch (Exception e) {
                System.out.println(e);
                new Alert(Alert.AlertType.ERROR, "Something Happened. try again carefully!").showAndWait();
            }
        }
    }

    public void deleteOnAction(ActionEvent actionEvent) {
        String id = txtRoomId.getText();
        try {
            if (roomBO.deleteRoom(id)) {
                new Alert(Alert.AlertType.CONFIRMATION, "DELETED").show();
                tblRoom.refresh();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
