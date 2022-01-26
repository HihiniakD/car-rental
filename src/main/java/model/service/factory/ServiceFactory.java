package model.service.factory;

import model.service.CarService;
import model.service.OrderService;
import model.service.UserService;
import model.service.impl.CarServiceImpl;
import model.service.impl.OrderServiceImpl;
import model.service.impl.UserServiceImpl;

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
}
