package dao.custom.impl;

import dao.SQLUtil;
import dao.custom.OrderDetailsDAO;
import entity.Item;
import entity.OrderDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    @Override
    public ArrayList<OrderDetails> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM OrderDetail");
        ArrayList<OrderDetails> allItems = new ArrayList<>();
        while (rst.next()) {
            allItems.add(new OrderDetails(rst.getString(1), rst.getString(2), rst.getInt(3), rst.getBigDecimal(4)));

        }
        return allItems;
    }

    @Override
    public boolean save(OrderDetails entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO OrderDetail (OrderID, ItemCode, OrderQty, Discount) VALUES (?,?,?,?)", entity.getOid(), entity.getItemCode(), entity.getOrderQty(), entity.getDiscount());
    }

    @Override
    public boolean update(OrderDetails entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderDetails search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

}
