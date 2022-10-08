package dao.custom;

import dao.CrudDAO;
import entity.CustomEntity;
import entity.Orders;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDAO extends CrudDAO<Orders,String> {
//    ArrayList<Orders> getOrderData() throws SQLException, ClassNotFoundException;
  //  ArrayList<Orders> getAllSearchOrder() throws SQLException, ClassNotFoundException;;
public ArrayList<Orders> searchOrder(String enteredText) throws SQLException, ClassNotFoundException;
}
