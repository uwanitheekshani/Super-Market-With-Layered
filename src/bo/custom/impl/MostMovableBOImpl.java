package bo.custom.impl;

//import bo.custom.CustomBO;
import bo.custom.MostMovableBO;
import dao.DAOFactory;
import dao.custom.QueryDAO;
import dto.CustomDTO;
import entity.CustomEntity;

import java.sql.SQLException;
import java.util.ArrayList;


public class MostMovableBOImpl implements MostMovableBO {

    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERYDAO);


    @Override
    public ArrayList<CustomDTO> getAllMostMovableItem() throws SQLException, ClassNotFoundException {
        ArrayList<CustomEntity> all = queryDAO.mostMovableItem();
        ArrayList<CustomDTO> allMovable = new ArrayList<>();
        for (CustomEntity ent : all) {
            allMovable.add(new CustomDTO(ent.getCode(), ent.getDescription(), ent.getUnitPrice(), ent.getQtyOnHand(), ent.getOrderQty()));
        }
        return allMovable;
    }

}
