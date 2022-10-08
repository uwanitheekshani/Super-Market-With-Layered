package dao.custom.impl;

import dao.SQLUtil;
import dao.custom.OrderDAO;
import entity.CustomEntity;
import entity.Orders;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
//import java.util.Date;


public class OrderDAOImpl implements OrderDAO {

    @Override
    public ArrayList<Orders> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Orders");
        ArrayList<Orders> searchOrders = new ArrayList();
        while (rst.next()) {
            searchOrders.add(new Orders(rst.getString(1), rst.getDate(2).toLocalDate(),rst.getString(3)));
        }
        return searchOrders;
    }

    @Override
    public boolean save(Orders entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO `Orders` (OrderID, OrderDate, custID) VALUES (?,?,?)", entity.getOid(), entity.getOrdeDate(), entity.getCustId());
    }

    @Override
    public boolean update(Orders entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Orders search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean exist(String oid) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeQuery("SELECT OrderID FROM `Orders` WHERE OrderID=?", oid).next();
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return  SQLUtil.executeUpdate("DELETE FROM `Orders` WHERE OrderID=?",s);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT OrderID FROM `Orders` ORDER BY OrderID DESC LIMIT 1;");
        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("OrderID").replace("OID-", "")) + 1)) : "OID-001";
    }


    @Override
    public ArrayList<Orders> searchOrder(String enteredText) throws SQLException, ClassNotFoundException {
        ResultSet result = SQLUtil.executeQuery("SELECT OrderID,custID from `Orders` where OrderID LIKE ? OR custID LIKE ? ORDER BY OrderID DESC", enteredText, enteredText);
        ArrayList<Orders> orList = new ArrayList<>();


        while (result.next()) {
            orList.add(new Orders(
                    result.getString(1),
                    result.getString(2)

            ));
        }
        return orList;
    }
}
