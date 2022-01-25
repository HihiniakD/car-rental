package model.dao.factory;

import model.dao.CarDao;
import model.dao.CityDao;
import model.dao.OrderDao;
import model.dao.UserDao;
import model.dao.impl.factory.JDBCDaoFactory;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao();
    public abstract CarDao createCarDao();
    public abstract OrderDao createOrderDao();
    public abstract CityDao createCityDao();

    public static DaoFactory getInstance(){
        if (daoFactory == null){
            synchronized (DaoFactory.class){
                if (daoFactory == null){
                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }

}
