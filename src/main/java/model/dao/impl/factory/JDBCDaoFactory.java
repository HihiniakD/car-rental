package model.dao.impl.factory;

import model.dao.*;
import model.dao.factory.DaoFactory;
import model.dao.impl.*;

public class JDBCDaoFactory extends DaoFactory {
    @Override
    public UserDao createUserDao() {return new JDBCUserImpl();}

    @Override
    public CarDao createCarDao() { return new JDBCCarImpl();}

    @Override
    public OrderDao createOrderDao() {return new JDBCOrderImpl();}

    @Override
    public CityDao createCityDao() {return new JDBCCityImpl();}

    @Override
    public BrandDao createBrandDao() {return new JDBCBrandImpl();}

    @Override
    public CategoryDao createCategoryDao() {return new JDBCCategoryImpl();}
}
