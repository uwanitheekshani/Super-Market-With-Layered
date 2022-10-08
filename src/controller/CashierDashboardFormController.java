package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;

public class CashierDashboardFormController {
    public AnchorPane cashierContext;
    public AnchorPane mainCashierContext;
    public Label lblDate;
    public Label lblTime;


    public void initialize() throws IOException {
        mainCashierContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/ManageCustomerForm.fxml"));
        mainCashierContext.getChildren().add(parent);
        loadDateAndTime();

    }

    private void loadDateAndTime() {
        Calendar clndr = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat(" aa");


        Timeline clock = new Timeline(new KeyFrame(Duration.INDEFINITE.ZERO, e -> {
            LocalTime currenttime = LocalTime.now();
            LocalDate currentdate = LocalDate.now();
            lblTime.setText(currenttime.getHour() + ":" + currenttime.getMinute() + ":" + currenttime.getSecond() + "  " + format1.format(clndr.getTime()));
            lblDate.setText(currentdate.getDayOfWeek()+","+ currentdate.getMonth() +" " + currentdate.getDayOfMonth() + "," + currentdate.getYear());

        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }


    public void btnPlaceOrderOnAction(ActionEvent actionEvent) throws IOException {
        mainCashierContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/PlaceOrderForm.fxml"));
        mainCashierContext.getChildren().add(parent);
    }

    public void btnManageCustomerOnAction(ActionEvent actionEvent) throws IOException {
        mainCashierContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/ManageCustomerForm.fxml"));
        mainCashierContext.getChildren().add(parent);
    }

    public void btnManageCustomerOrderOnAction(ActionEvent actionEvent) throws IOException {
        mainCashierContext.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/ManageCustomerOrderForm.fxml"));
        mainCashierContext.getChildren().add(parent);
    }
}
