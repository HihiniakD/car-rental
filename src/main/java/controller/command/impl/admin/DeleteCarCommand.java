package controller.command.impl.admin;

import controller.command.Command;
import model.service.CarService;
import model.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static controller.Constants.*;
import static controller.Path.*;

public class DeleteCarCommand implements Command {

    private final CarService carService = ServiceFactory.getCarService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int carId = Integer.parseInt(request.getParameter(ID_PARAMETER));

        if (carService.deleteCar(carId))
            request.getSession().setAttribute(MESSAGE_PARAMETER, SUCCESS_MESSAGE);
        else
            request.getSession().setAttribute(MESSAGE_PARAMETER, FAIL_MESSAGE);

        return REDIRECT + CARS_PAGE_COMMAND;

    }
}
