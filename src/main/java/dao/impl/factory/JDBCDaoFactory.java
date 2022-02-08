package dao.impl.factory;

import dao.*;
import dao.impl.*;
import dao.factory.DaoFactory;

/**
 * Implementation of Abstract DAOFactory
 *
 */
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
