package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminAndUserLoginFormController {

    public AnchorPane LogInContext;
    public TextField txtAdminName;
    public PasswordField pwdAdminPassword;
    public TextField txtCashierName;
    public PasswordField pwdUserPassword;
    int attempts=0;


    public void logInAdminOnAction(ActionEvent actionEvent) throws IOException {
//        setUi("AdminDashboardForm");
        attempts++;
        if (attempts<=3) {
            String tempAdminUserName = txtAdminName.getText();
            String tempAdminPassword = pwdAdminPassword.getText();
            if (tempAdminUserName.equals("admin") && tempAdminPassword.equals("1234")) {
//                Stage stage = (Stage) LogInContext.getScene().getWindow();
//                stage.close();
                setUi("AdminDashboardForm");
            } else {
                new Alert(Alert.AlertType.WARNING, "Try again").show();
                txtAdminName.clear();
                pwdAdminPassword.clear();
            }
        }else {
            txtAdminName.setEditable(false);
            pwdAdminPassword.setEditable(false);
        }
    }

    public void logInCashierOnAction(ActionEvent actionEvent) throws IOException {
       // setUi("CashierDashboardForm");

        attempts++;
        if (attempts<=3) {
            String tempCashierUserName = txtCashierName.getText();
            String tempCashierPassword = pwdUserPassword.getText();
            if (tempCashierUserName.equals("user") && tempCashierPassword.equals("5678")) {
//                Stage stage = (Stage) LogInContext.getScene().getWindow();
//                stage.close();
                setUi("CashierDashboardForm");
            } else {
                new Alert(Alert.AlertType.WARNING, "Try again").show();
                txtCashierName.clear();
                pwdUserPassword.clear();
            }
        }else {
            txtCashierName.setEditable(false);
            pwdUserPassword.setEditable(false);
        }
    }

    private void setUi(String location) throws IOException {
        Stage stage = (Stage) LogInContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
    }


}
