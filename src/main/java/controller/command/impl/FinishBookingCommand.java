package controller.command.impl;

import controller.command.Command;
import model.entity.Order;
import model.service.OrderService;
import model.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static controller.Constants.ID_PARAMETER;
import static controller.Constants.ORDER_PARAMETER;
import static controller.Path.DECLINE_ORDER_VIEW;
import static controller.Path.FINISH_ORDER_VIEW;

public class FinishBookingCommand implements Command {

    OrderService orderService = ServiceFactory.getOrderService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int orderId = Integer.parseInt(request.getParameter(ID_PARAMETER));
        Order order = orderService.findOrderById(orderId);
        request.setAttribute(ORDER_PARAMETER, order);
        return FINISH_ORDER_VIEW;
    }
}
