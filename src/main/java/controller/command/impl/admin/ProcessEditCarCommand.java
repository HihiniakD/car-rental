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

public class ProcessEditCarCommand implements Command {

    private final CarService carService = ServiceFactory.getCarService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int carId = Integer.parseInt(request.getParameter(ID_PARAMETER));
        String price = request.getParameter(PRICE_PARAMETER);
        String imageURL = request.getParameter(URL_PARAMETER);

        try {
            carService.editCar(carId, price, imageURL);
            request.getSession().setAttribute(MESSAGE_PARAMETER, SUCCESS_MESSAGE);
        }catch (ServiceException exception){
            request.getSession().setAttribute(MESSAGE_PARAMETER, exception.getMessage());
        }

        return REDIRECT + CARS_PAGE_COMMAND;
    }
}
