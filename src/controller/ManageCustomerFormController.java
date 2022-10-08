package controller;

import bo.BOFactory;
import bo.custom.CustomerBO;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.CustomerDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.tdm.CustomerTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManageCustomerFormController {
    public AnchorPane customerContext;
    public JFXTextField txtICustomerId;
    public JFXTextField txtCity;
    public JFXTextField txtProvince;
    public JFXTextField txtCustomerAddress;
    public Button btnsaveCustomer;
    public Button btnDeleteCustomer;
    public JFXTextField txtPostalCode;
    public JFXTextField txtCustomerName;
    public TableView<CustomerTM> tblCustomer;
    public JFXComboBox<String> cmbCustomerTitle;
    public TextField txtSearchCustomer;

    private final CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);


    public void initialize() {
        tblCustomer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("custId"));
        tblCustomer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("custTitle"));
        tblCustomer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("custName"));
        tblCustomer.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("custAddress"));
        tblCustomer.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("city"));
        tblCustomer.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("province"));
        tblCustomer.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        initUI();

        ObservableList<String> obl = FXCollections.observableArrayList();

        obl.add("Mr");
        obl.add("Mrs");
        obl.add("Miss");

        cmbCustomerTitle.setItems(obl);

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDeleteCustomer.setDisable(newValue == null);
            btnsaveCustomer.setText(newValue != null ? "Update" : "Save");
            btnsaveCustomer.setDisable(newValue == null);

            if (newValue != null) {
                txtICustomerId.setText(newValue.getCustId());
                cmbCustomerTitle.setValue(newValue.getCustTitle());
                txtCustomerName.setText(newValue.getCustName());
                txtCustomerAddress.setText(newValue.getCustAddress());
                txtCity.setText(newValue.getCity());
                txtProvince.setText(newValue.getProvince());
                txtPostalCode.setText(newValue.getPostalCode());

                txtICustomerId.setDisable(false);
                cmbCustomerTitle.setDisable(false);
                txtCustomerName.setDisable(false);
                txtCustomerAddress.setDisable(false);
                txtCity.setDisable(false);
                txtProvince.setDisable(false);
                txtPostalCode.setDisable(false);

            }
        });

        //txtCustomerAddress.setOnAction(event -> btnsaveCustomer.fire());
        loadAllCustomers();
    }

    private void loadAllCustomers() {
        tblCustomer.getItems().clear();
        /*Get all customers*/
        try {
            ArrayList<CustomerDTO> allCustomers = customerBO.getAllCustomers();
            for (CustomerDTO customer : allCustomers) {
                tblCustomer.getItems().add(new CustomerTM(customer.getCustId(), customer.getCustTitle(), customer.getCustName(), customer.getCustAddress(), customer.getCity(), customer.getProvince(), customer.getPostalCode()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void initUI() {
        txtICustomerId.clear();
        txtCustomerName.clear();
        txtCustomerAddress.clear();
        txtCity.clear();
        txtProvince.clear();
        txtPostalCode.clear();
        txtICustomerId.setDisable(true);
        txtCustomerName.setDisable(true);
        txtCustomerAddress.setDisable(true);
        txtCity.setDisable(true);
        txtProvince.setDisable(true);
        txtPostalCode.setDisable(true);
        txtICustomerId.setEditable(false);
        btnsaveCustomer.setDisable(true);
        btnDeleteCustomer.setDisable(true);
        cmbCustomerTitle.setDisable(true);
    }


    public void txtCustomerSearchOnAction(ActionEvent actionEvent) {
    }

    public void textField_key_Released(KeyEvent keyEvent) {
    }

    public void btnNewCustomerOnAction(ActionEvent actionEvent) {
        txtICustomerId.setDisable(false);
        txtCustomerName.setDisable(false);
        txtCustomerAddress.setDisable(false);
        txtCity.setDisable(false);
        txtProvince.setDisable(false);
        txtPostalCode.setDisable(false);

        txtICustomerId.clear();
        txtICustomerId.setText(generateNewId());
        txtCustomerName.clear();
        txtCustomerAddress.clear();
        txtCity.clear();
        txtProvince.clear();
        txtPostalCode.clear();

        cmbCustomerTitle.requestFocus();
        //txtCustomerName.requestFocus();
        cmbCustomerTitle.setDisable(false);
        btnsaveCustomer.setDisable(false);
        btnsaveCustomer.setText("Save");
        tblCustomer.getSelectionModel().clearSelection();
    }

    private String generateNewId() {
        try {
            return customerBO.generateNewCustomerID();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (tblCustomer.getItems().isEmpty()) {
            return "C00-001";
        } else {
            String id = getLastCustomerId();
            int newCustomerId = Integer.parseInt(id.replace("C", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        }
    }

    private String getLastCustomerId() {
        List<CustomerTM> tempCustomersList = new ArrayList<>(tblCustomer.getItems());
        Collections.sort(tempCustomersList);
        return tempCustomersList.get(tempCustomersList.size() - 1).getCustId();
    }

    public void btnSearchOnAction (ActionEvent actionEvent){
        }

        public void saveCustomerOnAction (ActionEvent actionEvent){
            String id = txtICustomerId.getText();
            String cusTitle = (String) cmbCustomerTitle.getValue();
            String name = txtCustomerName.getText();
            String address = txtCustomerAddress.getText();
            String city = txtCity.getText();
            String province = txtProvince.getText();
            String postalCode = txtPostalCode.getText();

            if (!name.matches("^[A-Z ][a-z]{1,}$")) {
                new Alert(Alert.AlertType.ERROR, "Invalid name").show();
                txtCustomerName.requestFocus();
                return;
            } else if (!address.matches(".{3,}")) {
                new Alert(Alert.AlertType.ERROR, "Address should be at least 3 characters long").show();
                txtCustomerAddress.requestFocus();
                return;
            }else if (!city.matches("^[A-Z ][a-z]{1,}$")) {
                new Alert(Alert.AlertType.ERROR, "Invalid city").show();
                txtCity.requestFocus();
                return;
            }else if (!province.matches("^[A-Z ][a-z]{1,}$")) {
                new Alert(Alert.AlertType.ERROR, "Invalid province").show();
                txtProvince.requestFocus();
                return;
            }else if (!postalCode.matches("^[A-z0-9 ,/]{4,20}$")) {
                new Alert(Alert.AlertType.ERROR, "Invalid postal code").show();
                txtPostalCode.requestFocus();
                return;
            }

            if (btnsaveCustomer.getText().equalsIgnoreCase("save")) {
                /*Save Customer*/
                try {
                    if (existCustomer(id)) {
                        new Alert(Alert.AlertType.ERROR, id + " already exists").show();
                    }

                    customerBO.saveCustomer(new CustomerDTO(id,cusTitle, name, address,city,province,postalCode));
                    tblCustomer.getItems().add(new CustomerTM(id,cusTitle, name, address,city,province,postalCode));
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, "Failed to save the customer " + e.getMessage()).show();
                    e.printStackTrace();

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                /*Update customer*/
                try {
                    if (!existCustomer(id)) {
                        new Alert(Alert.AlertType.ERROR, "There is no such customer associated with the id " + id).show();
                    }
                    //Customer update
                    customerBO.updateCustomer(new CustomerDTO(id,cusTitle, name, address,city,province,postalCode));

                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, "Failed to update the customer " + id + e.getMessage()).show();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                CustomerTM selectedCustomer = tblCustomer.getSelectionModel().getSelectedItem();
                selectedCustomer.setCustTitle(cusTitle);
                selectedCustomer.setCustName(name);
                selectedCustomer.setCustAddress(address);
                selectedCustomer.setCity(city);
                selectedCustomer.setProvince(province);
                selectedCustomer.setPostalCode(postalCode);
                tblCustomer.refresh();
            }
            initUI();
           // btnNewCustomerOnAction().fire();
        }

    private boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerBO.customerExist(id);
    }

    public void deleteCustomerOnAction (ActionEvent actionEvent){
        String id = tblCustomer.getSelectionModel().getSelectedItem().getCustId();
        try {
            if (!existCustomer(id)) {
                new Alert(Alert.AlertType.ERROR, "There is no such customer associated with the id " + id).show();
            }
            customerBO.deleteCustomer(id);
            tblCustomer.getItems().remove(tblCustomer.getSelectionModel().getSelectedItem());
            tblCustomer.getSelectionModel().clearSelection();
            initUI();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the customer " + id).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        }

        public void backOnMouseClicked (MouseEvent mouseEvent) throws IOException {
            setUi("AdminAndUserLoginForm");
        }

        private void setUi (String location) throws IOException {
            Stage stage = (Stage) customerContext.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/" + location + ".fxml"))));
            stage.centerOnScreen();
        }


    public void searchCustomerOnKeyReleased(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        String search = "%" + txtSearchCustomer.getText() + "%";

        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            ArrayList<CustomerDTO> customerDTOS = customerBO.searchCustomers(search);
            ObservableList<CustomerTM> oBCustomerTMS = FXCollections.observableArrayList();

            for (CustomerDTO cusDto : customerDTOS) {
                oBCustomerTMS.add(new CustomerTM(cusDto.getCustId(),
                        cusDto.getCustTitle(),
                        cusDto.getCustName(),
                        cusDto.getCustAddress(),
                        cusDto.getCity(),
                        cusDto.getProvince(),
                        cusDto.getPostalCode()));
            }
            tblCustomer.getItems().clear();
            tblCustomer.getItems().addAll(oBCustomerTMS);
            tblCustomer.refresh();
        }
    }

//    public void searchCustomerOnAction(ActionEvent actionEvent) {
//        try {
//            search();
//        } catch (ClassNotFoundException |SQLException e) {
//            e.printStackTrace();
//        }
//    }
}

