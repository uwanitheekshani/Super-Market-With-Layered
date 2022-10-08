package controller;

import bo.BOFactory;
import bo.custom.IncomeBO;
import bo.custom.OrderDetailBO;
import dto.CustomDTO;
import dto.ItemDTO;
import dto.OrderDTO;
import dto.OrderDetailDTO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.tdm.DailyIncomeTM;
import view.tdm.MostMovableTM;

import javax.xml.transform.Result;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class IncomeChartFormController {
    public AnchorPane incomeContext;
    public TableView<DailyIncomeTM> tblDailyIncome;
    public LineChart monthlyIncome;
    public LineChart chartMonthlyIncome;

    IncomeBO incomeBO = (IncomeBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.INCOME);

    public void initialize() throws SQLException, ClassNotFoundException {
        tblDailyIncome.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("ordeDate"));
        tblDailyIncome.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        loadDailyIncome();
        loadMonthlyIncome();
    }

    private void loadMonthlyIncome() throws SQLException, ClassNotFoundException {
        XYChart.Series monthlyIncome = new XYChart.Series();
        ArrayList<OrderDetailDTO> orderDetailDTOS = incomeBO.getMonthlyIncome();
        ArrayList<OrderDTO> allOrder = incomeBO.getAllOrder();
        ArrayList<ItemDTO> allItems = incomeBO.getAllItems();

        String[] month = {"January","February","March","April","May","June","July","August","September","October","November","December"};

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy");
        LocalDateTime now = LocalDateTime.now();

        String date_time =dtf.format(now);

        for (int i = 0; i < month.length; i++) {

            if (i<10){

                double total=0;
                String date = date_time+"-0"+(i+1);

                for (OrderDTO orderDTO:allOrder
                ) {
                    LocalDate ordeDate = orderDTO.getOrdeDate();
                   String od = String.valueOf(ordeDate);
                    String year = od.substring(0,7);
                    if (date.equals(year)){
                        for (OrderDetailDTO orderDetailDTO:orderDetailDTOS
                        ) {

                       if (orderDTO.getOid().equals(orderDetailDTO.getOid())){


                           for (ItemDTO itemDTO: allItems
                                ) {
                               if (itemDTO.getCode().equals(orderDetailDTO.getItemCode())){
                                   String unitPrice = String.valueOf(itemDTO.getUnitPrice());
                                   double totals = Double.parseDouble(unitPrice);
                                   int qty=orderDetailDTO.getOrderQty();
                                   total=total+(totals*qty);
                               }
                           }
                       }
                        }
                    }

                }

                monthlyIncome.getData().add(new XYChart.Data<>(month[i],total));


            }else {


                double total=0;
                String date = date_time+"-"+(i+1);

                for (OrderDTO orderDTO:allOrder
                ) {
                    LocalDate ordeDate = orderDTO.getOrdeDate();
                    String od = String.valueOf(ordeDate);
                    String year = od.substring(0,7);
                    if (date.equals(year)){
                        for (OrderDetailDTO orderDetailDTO:orderDetailDTOS
                        ) {

                            if (orderDTO.getOid().equals(orderDetailDTO.getOid())){


                                for (ItemDTO itemDTO: allItems
                                ) {
                                    if (itemDTO.getCode().equals(orderDetailDTO.getItemCode())){
                                        String unitPrice = String.valueOf(itemDTO.getUnitPrice());
                                        double totals = Double.parseDouble(unitPrice);
                                        int qty=orderDetailDTO.getOrderQty();
                                        total=total+(totals*qty);
                                    }
                                }
                            }
                        }
                    }

                }

                monthlyIncome.getData().add(new XYChart.Data<>(month[i],total));




            }

        }

        monthlyIncome.setName("Monthly Report");

        chartMonthlyIncome.getData().add(monthlyIncome);

    }


    public void backOnMouseClicked(MouseEvent mouseEvent) throws IOException {
        setUi("AdminAndUserLoginForm");
    }


    private void loadDailyIncome() {
        tblDailyIncome.getItems().clear();
        try {
            /*Get all items*/
            ArrayList<CustomDTO> dailyIncome = incomeBO.getDailyIncome();
            for (CustomDTO dto : dailyIncome) {
                tblDailyIncome.getItems().add(new DailyIncomeTM(dto.getOrdeDate(), dto.getUnitPrice()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void setUi(String location) throws IOException {
        Stage stage = (Stage) incomeContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
    }
}
