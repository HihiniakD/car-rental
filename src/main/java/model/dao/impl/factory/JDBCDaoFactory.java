package model.dao.impl.factory;

import model.dao.CarDao;
import model.dao.CityDao;
import model.dao.OrderDao;
import model.dao.UserDao;
import model.dao.factory.DaoFactory;
import model.dao.impl.JDBCCarImpl;
import model.dao.impl.JDBCCityImpl;
import model.dao.impl.JDBCOrderImpl;
import model.dao.impl.JDBCUserImpl;

public class JDBCDaoFactory extends DaoFactory {
    @Override
    public UserDao createUserDao() {return new JDBCUserImpl();}

    @Override
    public CarDao createCarDao() { return new JDBCCarImpl();}

    @Override
    public OrderDao createOrderDao() {return new JDBCOrderImpl();}

    @Override
    public CityDao createCityDao() {return new JDBCCityImpl();}
}
