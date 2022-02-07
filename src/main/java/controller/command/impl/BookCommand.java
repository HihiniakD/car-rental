package controller.command.impl;

import controller.command.Command;
import model.entity.Car;
import model.service.CityService;
import model.service.OrderService;
import model.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static controller.Constants.*;
import static controller.Path.*;

public class BookCommand implements Command {

    private final CityService cityService = ServiceFactory.getCityService();
    private final OrderService orderService = ServiceFactory.getOrderService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(true);
        Car car = (Car) session.getAttribute(CAR_PARAMETER);
        long days = (long) session.getAttribute(DAYS_PARAMETER);
        long driverPrice = 0;
        boolean withDriver = request.getParameter(DRIVER_PARAMETER) != null;
        if (withDriver){
            session.setAttribute(DRIVER_PARAMETER, true);
            driverPrice = orderService.calculateCarDriverPrice(days);
            session.setAttribute(DRIVER_PRICE_PARAMETER, driverPrice);
        }
        else{
            request.setAttribute(DRIVER_PARAMETER, false);
        }
        long carRentPrice = orderService.calculateTotalPrice(car.getPrice(), days);
        session.setAttribute(CAR_RENTAL_PRICE_PARAMETER, carRentPrice);
        long totalPrice = carRentPrice + driverPrice;
        session.setAttribute(TOTAL_PRICE_PARAMETER, totalPrice);
        int cityId = car.getCityId();
        String cityName = cityService.getCityNameById(cityId);
        session.setAttribute(CITY_PARAMETER, cityName);

        return BOOK_VIEW;
    }
}
