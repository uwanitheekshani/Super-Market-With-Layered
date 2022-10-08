package dao.custom.impl;

import dao.SQLUtil;
import dao.custom.QueryDAO;
import entity.CustomEntity;
import entity.OrderDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;


public class QueryDAOImpl implements QueryDAO {
//    @Override
//    public ArrayList<CustomEntity> searchOrderByOrderID(String id) throws SQLException, ClassNotFoundException {
//        ResultSet rst = SQLUtil.executeQuery("select Orders.oid,Orders.date,Orders.customerID,OrderDetails.itemCode,OrderDetails.qty,OrderDetails.unitPrice from Orders inner join OrderDetails on Orders.oid=OrderDetails.oid where Orders.oid=?;", id);
//        ArrayList<CustomEntity> orderRecords = new ArrayList();
//        while (rst.next()) {
//            orderRecords.add(new CustomEntity(rst.getString(1), LocalDate.parse(rst.getString(2)), rst.getString(3), rst.getString(4), rst.getInt(5), rst.getBigDecimal(6)));
//        }
//        return orderRecords;
//    }

    @Override
    public ArrayList<CustomEntity> mostMovableItem() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT Item.ItemCode,Description,UnitPrice,QtyOnHand,SUM(OrderQTY) from Item inner join OrderDetail on Item.ItemCode = OrderDetail.ItemCode GROUP BY ItemCode ORDER BY SUM(OrderQTY) DESC");
        ArrayList<CustomEntity> mostMovable = new ArrayList();
        while (rst.next()) {
            mostMovable.add(new CustomEntity(rst.getString(1), rst.getString(2), rst.getBigDecimal(3), rst.getInt(4), rst.getInt(5)));
        }
        return mostMovable;
    }

    @Override
    public ArrayList<CustomEntity> leastMovableItem() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT Item.ItemCode,Description,UnitPrice,QtyOnHand,SUM(OrderQTY) from Item inner join OrderDetail on Item.ItemCode = OrderDetail.ItemCode GROUP BY ItemCode ORDER BY SUM(OrderQTY)");
        ArrayList<CustomEntity> mostMovable = new ArrayList();
        while (rst.next()) {
            mostMovable.add(new CustomEntity(rst.getString(1), rst.getString(2), rst.getBigDecimal(3), rst.getInt(4), rst.getInt(5)));
        }
        return mostMovable;
    }

    @Override
    public ArrayList<OrderDetails> searchOrderDetail(String enteredText) throws SQLException, ClassNotFoundException {
        ResultSet result = SQLUtil.executeQuery("SELECT OrderDetail.OrderID,OrderDetail.ItemCode,OrderDetail.OrderQty,Item.UnitPrice,OrderDetail.Discount from OrderDetail INNER JOIN  Item on OrderDetail.ItemCode = Item.ItemCode where OrderDetail.OrderID LIKE ? OR OrderDetail.ItemCode LIKE ? OR OrderDetail.OrderQty LIKE ? OR OrderDetail.Discount LIKE ?;", enteredText, enteredText, enteredText, enteredText);
        ArrayList<OrderDetails> orList = new ArrayList<>();


        while (result.next()) {
            double total=(result.getInt(3)*result.getDouble(4)-result.getDouble(5));
            orList.add(new OrderDetails(result.getString(1),
                    result.getString(2),
                    result.getInt(3),
                    result.getBigDecimal(4),
                    result.getBigDecimal(5),
                    total

            ));
        }
        return orList;
    }

    @Override
    public ArrayList<CustomEntity> dailyIncome() throws SQLException, ClassNotFoundException {
        ResultSet result = SQLUtil.executeQuery("SELECT OrderDate,SUM(OrderQTY*UnitPrice-o.Discount)FROM Orders INNER JOIN OrderDetail o on Orders.OrderID = o.OrderID INNER JOIN Item i on o.ItemCode = i.ItemCode GROUP BY OrderDate ORDER BY OrderDate;");
        ArrayList<CustomEntity> orList = new ArrayList<>();


        while (result.next()) {
           // double total=(result.getInt(2)*result.getDouble(1)-result.getDouble(3));
            orList.add(new CustomEntity(result.getDate(1).toLocalDate(),
                    result.getBigDecimal(2))

            );
        }

        return orList;
    }
}
