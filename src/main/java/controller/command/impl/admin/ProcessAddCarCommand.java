package controller.command.impl.admin;

import controller.command.Command;
import model.exception.ServiceException;
import model.service.CarService;
import model.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static controller.Constants.*;
import static controller.Path.*;

public class ProcessAddCarCommand implements Command {

    private final CarService carService = ServiceFactory.getCarService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int cityId = Integer.parseInt(request.getParameter(CITY_PARAMETER));
        int categoryId = Integer.parseInt(request.getParameter(CATEGORY_PARAMETER));
        int brandId = Integer.parseInt(request.getParameter(BRAND_PARAMETER));
        int passengers = Integer.parseInt(request.getParameter(PASSENGERS_PARAMETER));
        String transmission = request.getParameter(TRANSMISSION_PARAMETER);
        String model = request.getParameter(MODEL_PARAMETER);
        String price = request.getParameter(PRICE_PARAMETER);
        String imageUrl = request.getParameter(URL_PARAMETER);

        try{
            if (carService.addCar(brandId, model, passengers, price, transmission, categoryId, cityId, imageUrl)){
                request.getSession().setAttribute(MESSAGE_PARAMETER, SUCCESS_MESSAGE);
                return REDIRECT + CARS_PAGE_COMMAND;
        }
        }catch (ServiceException e){
            request.getSession().setAttribute(MESSAGE_PARAMETER, e.getMessage());
        }
        return REDIRECT + CARS_PAGE_COMMAND;
    }
}
