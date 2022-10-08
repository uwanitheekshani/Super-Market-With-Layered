package bo.custom.impl;

import bo.custom.OrderBO;
import bo.custom.OrderDetailBO;
import dao.DAOFactory;
import dao.custom.OrderDAO;
import dao.custom.OrderDetailsDAO;
import dao.custom.QueryDAO;
import dao.custom.impl.QueryDAOImpl;
import dto.OrderDTO;
import dto.OrderDetailDTO;
import entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailBOImpl implements OrderDetailBO {

    //private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);
     QueryDAO queryDAO = new QueryDAOImpl();

    @Override
    public ArrayList<OrderDetailDTO> getAllOrderDetails() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<OrderDetailDTO> searchOrderDetails(String enteredText) throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetails> orderDetails = queryDAO.searchOrderDetail(enteredText);


        ArrayList<OrderDetailDTO> orDetailDto=new ArrayList<>();

        for (OrderDetails ordersList : orderDetails) {

            orDetailDto.add(new OrderDetailDTO(ordersList.getOid(),
                    ordersList.getItemCode(),
                    ordersList.getOrderQty(),
                    ordersList.getUnitPrice(),
                    ordersList.getDiscount(),
                    ordersList.getTotal()
            ));
        }
        return orDetailDto;
    }
}
