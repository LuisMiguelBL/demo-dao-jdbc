package model.dao;

import model.SellerDao;
import model.dao.impl.SellerDaoJDBC;

public class DaoFactory {
    //Cria nossos Dao
    public static SellerDao createSellerDao(){
        return new SellerDaoJDBC();
    }

}
