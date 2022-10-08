package controller;

import bo.BOFactory;
import bo.custom.MostMovableBO;
import dto.CustomDTO;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import view.tdm.MostMovableTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class MostMovableItemFormController {
    public AnchorPane mostMovableItemContext;
    public TableView<MostMovableTM> tblMostMovableItem;

    MostMovableBO mostMovableBO = (MostMovableBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MOSTMOVABLEITEM);

    public void initialize(){
        tblMostMovableItem.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tblMostMovableItem.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblMostMovableItem.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblMostMovableItem.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        tblMostMovableItem.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("orderQty"));

        loadAllMovableItems();
    }

    private void loadAllMovableItems() {
        tblMostMovableItem.getItems().clear();
        try {
            /*Get all items*/
            ArrayList<CustomDTO> mostMovableItems = mostMovableBO.getAllMostMovableItem();
            for (CustomDTO dto : mostMovableItems) {
                tblMostMovableItem.getItems().add(new MostMovableTM(dto.getCode(), dto.getDescription(), dto.getUnitPrice(), dto.getQtyOnHand(), dto.getOrderQty()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
