package bo.custom.impl;

import bo.custom.OrderBO;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import dao.DAOFactory;
import dao.custom.ItemDAO;
import dao.custom.OrderDAO;
import dao.custom.impl.OrderDAOImpl;
import dto.OrderDTO;
import entity.Orders;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBOImpl implements OrderBO {
    private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
   //OrderDAO orderDAO = new OrderDAOImpl();
    @Override
    public ArrayList<OrderDTO> getAllOrders() throws SQLException, ClassNotFoundException {
        ArrayList<Orders> all = orderDAO.getAll();
        ArrayList<OrderDTO> allOrder = new ArrayList<>();

        for (Orders order : all) {
            allOrder.add(new OrderDTO(order.getOid(), order.getCustId()));
        }
        return allOrder;
    }

    @Override
    public boolean deleteOrders(String id) throws SQLException, ClassNotFoundException {
        return orderDAO.delete(id);
    }

    @Override
    public ArrayList<OrderDTO> getAllSearchOrder(String enteredText) throws SQLException, ClassNotFoundException {
        ArrayList<Orders> order = orderDAO.searchOrder(enteredText);

        ArrayList<OrderDTO> orDto = new ArrayList<>();

        for (Orders orderList : order) {

            orDto.add(new OrderDTO(
                    orderList.getOid(),
                    orderList.getCustId()
            ));
        }
        return orDto;
    }
}
