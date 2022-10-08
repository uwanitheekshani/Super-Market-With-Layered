package bo.custom.impl;

import bo.custom.IncomeBO;
import dao.DAOFactory;
import dao.custom.ItemDAO;
import dao.custom.OrderDAO;
import dao.custom.OrderDetailsDAO;
import dao.custom.QueryDAO;
import dao.custom.impl.QueryDAOImpl;
import dto.CustomDTO;
import dto.ItemDTO;
import dto.OrderDTO;
import dto.OrderDetailDTO;
import entity.CustomEntity;
import entity.Item;
import entity.OrderDetails;
import entity.Orders;

import java.sql.SQLException;
import java.util.ArrayList;

public class IncomeBOImpl implements IncomeBO {
   // private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.INCOME);
    QueryDAO queryDAO = new QueryDAOImpl();
    private final OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);
    private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public ArrayList<CustomDTO> getDailyIncome() throws SQLException, ClassNotFoundException {
        ArrayList<CustomEntity> incomeDetails = queryDAO.dailyIncome();


        ArrayList<CustomDTO> orDetailDto=new ArrayList<>();

        for (CustomEntity income : incomeDetails) {

            orDetailDto.add(new CustomDTO(income.getOrdeDate(),
                    income.getUnitPrice()
            ));
        }
        return orDetailDto;
    }

    @Override
    public ArrayList<OrderDetailDTO> getMonthlyIncome() throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetails> all = orderDetailsDAO.getAll();
        ArrayList<OrderDetailDTO> allOrders = new ArrayList<>();

        for (OrderDetails order: all
        ) {
allOrders.add(new OrderDetailDTO(order.getOid(),order.getItemCode(),order.getOrderQty(),order.getDiscount()));
        }

        return allOrders;
    }

    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item> all = itemDAO.getAll();
        ArrayList<ItemDTO> allItems= new ArrayList<>();
        for (Item item : all) {
            allItems.add(new ItemDTO(item.getCode(),item.getDescription(),item.getPackSize(),item.getUnitPrice(),item.getQtyOnHand(),item.getDiscount()));
        }
        return allItems;
    }

    public ArrayList<OrderDTO> getAllOrder() throws SQLException, ClassNotFoundException {
        ArrayList<Orders> all = orderDAO.getAll();
        ArrayList<OrderDTO> allOrders = new ArrayList<>();

        for (Orders order: all
        ) {
            allOrders.add(new OrderDTO(order.getOid(),order.getOrdeDate(),order.getCustId()));

        }

        return allOrders;
    }

}
