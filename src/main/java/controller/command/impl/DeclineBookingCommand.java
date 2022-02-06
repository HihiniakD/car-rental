package controller.command.impl;

import controller.command.Command;
import model.entity.Order;
import model.service.OrderService;
import model.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static controller.Constants.*;
import static controller.Path.*;

public class DeclineBookingCommand implements Command {

    OrderService orderService = ServiceFactory.getOrderService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int orderId = Integer.parseInt(request.getParameter(ID_PARAMETER));
        Order order = orderService.findOrderById(orderId);
        request.setAttribute(ORDER_PARAMETER, order);
        return DECLINE_ORDER_VIEW;
    }
}