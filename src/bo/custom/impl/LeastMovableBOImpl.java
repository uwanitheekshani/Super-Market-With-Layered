package bo.custom.impl;

import bo.custom.LeastMovableBO;
import dao.DAOFactory;
import dao.custom.QueryDAO;
import dto.CustomDTO;
import entity.CustomEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class LeastMovableBOImpl implements LeastMovableBO {

    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERYDAO);


    @Override
    public ArrayList<CustomDTO> getAllLeastMovableItem() throws SQLException, ClassNotFoundException {
            ArrayList<CustomEntity> all = queryDAO.leastMovableItem();
            ArrayList<CustomDTO> allMovable = new ArrayList<>();
            for (CustomEntity ent : all) {
                allMovable.add(new CustomDTO(ent.getCode(), ent.getDescription(), ent.getUnitPrice(), ent.getQtyOnHand(), ent.getOrderQty()));
            }
            return allMovable;
        }

}
