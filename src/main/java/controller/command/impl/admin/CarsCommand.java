package controller.command.impl.admin;

import controller.command.Command;
import model.entity.Car;
import service.CarService;
import service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import static controller.Constants.*;
import static controller.Path.*;

public class CarsCommand implements Command {

    private final CarService carService = ServiceFactory.getCarService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Car> cars = carService.findAllCars();
        request.setAttribute(CARS_PARAMETER, cars);
        return CARS_VIEW;
    }
}
