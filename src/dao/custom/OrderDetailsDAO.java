package dao.custom;

import dao.CrudDAO;
import entity.OrderDetails;
import entity.Orders;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailsDAO extends CrudDAO<OrderDetails,String> {

//    public ArrayList<OrderDetails> searchOrderDetail(String enteredText) throws SQLException, ClassNotFoundException;
}
