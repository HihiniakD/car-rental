package controller.command.impl;

import controller.command.Command;
import model.entity.enums.Status;
import model.exception.ServiceException;
import model.service.CarService;
import model.service.OrderService;
import model.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static controller.Constants.*;
import static controller.Path.MANAGER_PAGE_COMMAND;
import static controller.Path.REDIRECT;

public class ProcessFinishBookingCommand implements Command {

    OrderService orderService = ServiceFactory.getOrderService();
    CarService carService = ServiceFactory.getCarService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int orderId = Integer.parseInt(request.getParameter(ID_PARAMETER));
        int carID = Integer.parseInt(request.getParameter(CAR_ID_PARAMETER));
        String penalty = request.getParameter(PENALTY_PARAMETER);
        String comment = "";
        if (request.getParameter(COMMENT_PARAMETER) !=null)
            comment = request.getParameter(COMMENT_PARAMETER);

        try {
            orderService.finishOrder(orderId, comment, penalty);
            carService.changeStatus(carID, Status.AVAILABLE);
            request.getSession().setAttribute(MESSAGE_PARAMETER, SUCCESS_MESSAGE);
        }catch (ServiceException exception){
            request.getSession().setAttribute(MESSAGE_PARAMETER, FAIL_MESSAGE);
        }

        return REDIRECT + MANAGER_PAGE_COMMAND;
    }
}
