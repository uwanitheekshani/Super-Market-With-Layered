package bo.custom;

import bo.SuperBO;
import dto.CustomDTO;
import dto.ItemDTO;
import dto.OrderDTO;
import dto.OrderDetailDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IncomeBO extends SuperBO {
    ArrayList<CustomDTO> getDailyIncome() throws SQLException, ClassNotFoundException;
    ArrayList<OrderDetailDTO> getMonthlyIncome() throws SQLException, ClassNotFoundException;
    ArrayList<OrderDTO> getAllOrder() throws SQLException, ClassNotFoundException ;
    ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException;
}
