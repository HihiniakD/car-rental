package controller.command.impl;

import controller.command.Command;
import model.entity.OrderExtended;
import model.entity.User;
import service.OrderService;
import service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static controller.Path.*;
import static controller.Constants.*;


public class MyBookingCommand implements Command {

    private final OrderService orderService = ServiceFactory.getOrderService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute(USER_PARAMETER);
        List<OrderExtended> orderExtended = orderService.findAllByUserId(user.getId());
        request.setAttribute(ORDERS_PARAMETER, orderExtended);
        return MY_BOOKING_VIEW;
    }
}
