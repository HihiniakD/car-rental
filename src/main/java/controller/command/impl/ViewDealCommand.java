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
import static controller.Constants.*;
import static controller.Path.*;

public class ViewDealCommand implements Command {

    CarService carService = ServiceFactory.getCarService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int carId = Integer.parseInt(request.getParameter(ID_PARAMETER));
        Car car = null;
        try {
            car = carService.findCarById(carId);
        }catch (ServiceException exception){
            request.setAttribute(ERROR_PARAMETER, exception.getMessage());
            return REDIRECT;
        }
        HttpSession session = request.getSession(true);
        session.setAttribute(CAR_PARAMETER, car);
        return BOOK_CAR_VIEW;
    }
}
