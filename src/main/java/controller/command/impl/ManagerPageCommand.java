package controller.command.impl;

import controller.command.Command;
import model.entity.OrderExtended;
import model.service.OrderService;
import model.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static controller.Path.*;
import static controller.Constants.*;

public class ManagerPageCommand implements Command {

    OrderService orderService = ServiceFactory.getOrderService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<OrderExtended> newOrders = orderService.findAllNewOrders();
        List<OrderExtended> ordersInProgress = orderService.findAllInProgressOrders();
        List<OrderExtended> finishedOrders = orderService.findAllFinishedOrders();
        List<OrderExtended> declinedOrders = orderService.findAllDeclinedOrders();

        request.setAttribute(NEW_ORDERS_PARAMETER, newOrders);
        request.setAttribute(IN_PROGRESS_ORDERS_PARAMETER, ordersInProgress);
        request.setAttribute(FINISHED_ORDERS_PARAMETER, finishedOrders);
        request.setAttribute(DECLINED_ORDERS_PARAMETER, declinedOrders);

        return MANAGER_PAGE_VIEW;
    }
}
