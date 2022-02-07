package controller.command.impl.admin;

import controller.command.Command;
import model.entity.Car;
import model.service.CarService;
import model.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static controller.Constants.*;
import static controller.Path.*;

public class EditCarCommand implements Command {

    private final CarService carService = ServiceFactory.getCarService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int carId = Integer.parseInt(request.getParameter(ID_PARAMETER));
        Car car = carService.findCarById(carId);
        request.setAttribute(CAR_PARAMETER, car);
        return CAR_EDIT_VIEW;

    }
}
