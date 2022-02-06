package controller.command.impl;

import controller.command.Command;
import model.entity.enums.Status;
import model.service.OrderService;
import model.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import static controller.Constants.*;
import static controller.Path.*;

public class ApproveBookingCommand implements Command {

    OrderService orderService = ServiceFactory.getOrderService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int orderId = Integer.parseInt(request.getParameter(ID_PARAMETER));

        if(orderService.changeOrderStatusById(orderId, Status.APPROVED))
            request.getSession().setAttribute(MESSAGE_PARAMETER, SUCCESS_MESSAGE);
        else
            request.getSession().setAttribute(MESSAGE_PARAMETER, FAIL_MESSAGE);

        return REDIRECT + MANAGER_PAGE_COMMAND;
    }
}
