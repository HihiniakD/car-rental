package model.dao.factory;

import model.dao.*;
import model.dao.impl.factory.JDBCDaoFactory;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract UserDao createUserDao();
    public abstract CarDao createCarDao();
    public abstract OrderDao createOrderDao();
    public abstract CityDao createCityDao();
    public abstract BrandDao createBrandDao();
    public abstract CategoryDao createCategoryDao();

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
