package controller.command.impl;

import controller.command.Command;
import model.entity.Car;
import model.exception.ServiceException;
import model.service.CarService;
import model.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import static controller.Path.*;
import static controller.Constants.*;

public class SearchCarsCommand implements Command {
    private final CarService carService = ServiceFactory.getCarService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int cityID = Integer.parseInt(request.getParameter(CITY_PARAMETER));
        int categoryID = Integer.parseInt(request.getParameter(CATEGORY_PARAMETER));
        int brandID = Integer.parseInt(request.getParameter(BRAND_PARAMETER));
        String pickUpDate = request.getParameter(PICKUP_DATE_PARAMETER);
        String dropOffDate = request.getParameter(DROPOFF_DATE_PARAMETER);
        List<Car> cars = null;
        try {
            cars = carService.findAllAvailableCars(cityID, categoryID, brandID, pickUpDate, dropOffDate, request);
        }catch (ServiceException exception){
            request.setAttribute(ERROR_PARAMETER, exception.getMessage());
            return BACK_TO_INDEX;
        }
        HttpSession session = request.getSession(true);
        session.setAttribute(BRAND_ID_PARAMETER, brandID);
        session.setAttribute(CITY_ID_PARAMETER, cityID);
        session.setAttribute(CATEGORY_ID_PARAMETER, categoryID);
        request.setAttribute(CARS_PARAMETER, cars);
        return SEARCH_CARS_VIEW;
    }
}
