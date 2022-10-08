package controller;

import bo.BOFactory;
import bo.custom.LeastMovableBO;
import bo.custom.MostMovableBO;
import dto.CustomDTO;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import view.tdm.MostMovableTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class LeastMovableItemFormController {
    public AnchorPane leastMovableItemContext;
    public TableView<MostMovableTM> tblLeastMovableItem;

    LeastMovableBO leastMovableBO = (LeastMovableBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LEASTMOVABLEITEM);

    public void initialize(){
        tblLeastMovableItem.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tblLeastMovableItem.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblLeastMovableItem.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblLeastMovableItem.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        tblLeastMovableItem.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("orderQty"));

        loadAllMovableItems();
    }

    private void loadAllMovableItems() {
        tblLeastMovableItem.getItems().clear();
        try {
            /*Get all items*/
            ArrayList<CustomDTO> leastMovableItems = leastMovableBO.getAllLeastMovableItem();
            for (CustomDTO dto : leastMovableItems) {
                tblLeastMovableItem.getItems().add(new MostMovableTM(dto.getCode(), dto.getDescription(), dto.getUnitPrice(), dto.getQtyOnHand(), dto.getOrderQty()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
