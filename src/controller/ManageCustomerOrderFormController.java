package controller;

import bo.BOFactory;
import bo.custom.OrderBO;
import bo.custom.OrderDetailBO;
import bo.custom.PurchaseOrderBO;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import db.DBConnection;
import dto.CustomerDTO;
import dto.OrderDTO;
import dto.OrderDetailDTO;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import view.tdm.OrderDetailTM;
import view.tdm.OrderTM;
import view.tdm.SearchOrderDetailsTM;
import view.tdm.SearchOrderTM;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ManageCustomerOrderFormController {
    public AnchorPane manageCustomerOrderContext;
    public TableView<OrderDetailTM> tblOrderDetails;
    public JFXTextField txtQty;
    public TableView<OrderTM> tblOrders;
    public TextField txtSearchByOrderIdCustomerId;
    public TextField txtSearchOrderDetails;
    public JFXTextField txtOrderId;
    public JFXTextField txtDiscount;
    public JFXTextField txtItemCode;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtTotal;
    public Label lblTotal;

    OrderBO orderBO = (OrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDER);
    OrderDetailBO orderDetailBO = (OrderDetailBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDERDETAILS);

    public void initialize() throws SQLException, ClassNotFoundException {
        tblOrders.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("orderId"));
        tblOrders.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("customerId"));

        tblOrderDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tblOrderDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblOrderDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblOrderDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblOrderDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("discount"));
        tblOrderDetails.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("total"));
        TableColumn<OrderDetailTM, Button> lastCol = (TableColumn<OrderDetailTM, Button>) tblOrderDetails.getColumns().get(6);
        lastCol.setCellValueFactory(param -> {
            Button btnDelete = new Button("Delete");
            btnDelete.setOnAction(event -> {
                tblOrderDetails.getItems().remove(param.getValue());
                tblOrderDetails.getSelectionModel().clearSelection();

            });

            return new ReadOnlyObjectWrapper<>(btnDelete);
        });
//add Orders to The Table
        ArrayList<OrderDTO> allOrders = orderBO.getAllOrders();

        for (OrderDTO allOrder : allOrders) {
            tblOrders.getItems().add(new OrderTM(allOrder.getOid(), allOrder.getCustId()));
        }
        tblOrders.refresh();

        tblOrders.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue != null) {

                searchOrderDetails(newValue.getOrderId());
            }

        });


    }


    private void searchOrderDetails(String newValue) {
        //Search by ID
        String value = "%" + newValue + "%";

        ArrayList<OrderDetailDTO> oDetailDto = null;

        try {

            oDetailDto = orderDetailBO.searchOrderDetails(value);


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ObservableList<OrderDetailTM> orderDetailsTMS = FXCollections.observableArrayList();

        double allTotal = 0;

        for (OrderDetailDTO od : oDetailDto) {
            orderDetailsTMS.add(new OrderDetailTM(od.getOid(),
                    od.getItemCode(),
                    od.getOrderQty(),
                    od.getUnitPrice(),
                    od.getDiscount(),
                    BigDecimal.valueOf(od.getTotal())
            ));

            allTotal += od.getTotal();
        }

        tblOrderDetails.getItems().clear();
        tblOrderDetails.getItems().addAll(orderDetailsTMS);
        tblOrderDetails.refresh();
        lblTotal.setText(String.valueOf(allTotal));


    }


    public void removeOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        OrderTM selectedItem = tblOrders.getSelectionModel().getSelectedItem();
        boolean b = orderBO.deleteOrders(selectedItem.getOrderId());

        if (b) {
            new Alert(Alert.AlertType.INFORMATION, "Deleted SussesFull").show();
            tblOrderDetails.getItems().clear();
            tblOrders.getItems().clear();
            initialize();
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Something Went Wrong..").show();
        }
    }

    public void confirmEditsOnAction(ActionEvent actionEvent) {
    }

    public void updateOnAction(ActionEvent actionEvent) {
    }

    public void searchOrderDetailsOnKeyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            tblOrderDetails.getItems().clear();
            searchOrderDetails(txtSearchOrderDetails.getText());
        }
    }

    public void searchOrderIdOnKeyReleased(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            String value = "%" + txtSearchByOrderIdCustomerId.getText() + "%";

            ArrayList<OrderDTO> orderDto = orderBO.getAllSearchOrder(value);


            ObservableList<OrderTM> orderTMS = FXCollections.observableArrayList();

            //System.out.println(orderDto);
            for (OrderDTO od : orderDto) {
                orderTMS.add(new OrderTM(
                        od.getOid(),
                        od.getCustId()
                ));

            }

            tblOrders.getItems().clear();
            tblOrders.getItems().addAll(orderTMS);
            tblOrders.refresh();
        }
    }
}
