package model.service.factory;

import model.service.*;
import model.service.impl.*;

public class ServiceFactory {
    private ServiceFactory(){}

    public static UserService getUserService() {
        return new UserServiceImpl();
    }

    public static OrderService getOrderService() {
        return new OrderServiceImpl();
    }

    public static CarService getCarService() {
        return new CarServiceImpl();
    }

    public static CityService getCityService() { return new CityServiceImpl(); }

    public static BrandService getBrandService() { return new BrandServiceImpl(); }

    public static CategoryService getCategoryService() { return new CategoryServiceImpl(); }
}
