package controller.command.impl.manager;

import controller.command.Command;
import model.entity.enums.Status;
import service.CarService;
import service.OrderService;
import service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static controller.Constants.*;
import static controller.Path.*;

public class ProccessDeclineBookingCommand implements Command {

    private final OrderService orderService = ServiceFactory.getOrderService();
    private final CarService carService = ServiceFactory.getCarService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int orderId = Integer.parseInt(request.getParameter(ID_PARAMETER));
        int carID = Integer.parseInt(request.getParameter(CAR_ID_PARAMETER));
        String comment = "";
        if (request.getParameter(COMMENT_PARAMETER) !=null)
            comment = request.getParameter(COMMENT_PARAMETER);

        if (orderService.declineOrder(orderId, comment)) {
            carService.changeStatus(carID, Status.AVAILABLE);
            request.getSession().setAttribute(MESSAGE_PARAMETER, SUCCESS_MESSAGE);
        } else {
            request.getSession().setAttribute(MESSAGE_PARAMETER, FAIL_MESSAGE);
        }

        return REDIRECT + MANAGER_PAGE_COMMAND;
    }
}
