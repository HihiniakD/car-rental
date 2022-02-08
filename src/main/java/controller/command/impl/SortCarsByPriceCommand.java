package controller.command.impl;

import controller.command.Command;
import model.entity.Car;
import service.CarService;
import service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static controller.Constants.*;
import static controller.Path.SEARCH_CARS_VIEW;

public class SortCarsByPriceCommand implements Command {

    private final CarService carService = ServiceFactory.getCarService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(true);
        int cityId = (int) session.getAttribute(CITY_ID_PARAMETER);
        int brandId = (int) session.getAttribute(BRAND_ID_PARAMETER);
        int categoryId = (int) session.getAttribute(CATEGORY_ID_PARAMETER);

        List<Car> sortedCars = carService.findAllAvailableCarsSortedByPrice(cityId, categoryId, brandId);
        request.setAttribute(CARS_PARAMETER, sortedCars);
        return SEARCH_CARS_VIEW;
    }
}
