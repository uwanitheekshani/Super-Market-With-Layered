package dao.custom;

import dao.SuperDAO;
import entity.CustomEntity;
import entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
    ArrayList<CustomEntity> mostMovableItem() throws SQLException, ClassNotFoundException;
    ArrayList<CustomEntity> leastMovableItem() throws SQLException, ClassNotFoundException;
    public ArrayList<OrderDetails> searchOrderDetail(String enteredText) throws SQLException, ClassNotFoundException;
    public ArrayList<CustomEntity> dailyIncome() throws SQLException, ClassNotFoundException;

}
