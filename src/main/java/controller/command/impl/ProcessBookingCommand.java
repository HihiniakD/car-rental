package controller.command.impl;

import controller.command.Command;
import model.entity.Car;
import model.entity.User;
import model.entity.enums.Status;
import model.exception.ServiceException;
import model.service.CarService;
import model.service.OrderService;
import model.service.UserService;
import model.service.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import static controller.Constants.*;
import static controller.Path.*;

public class ProcessBookingCommand implements Command {

    private final OrderService orderService = ServiceFactory.getOrderService();
    private final UserService userService = ServiceFactory.getUserService();
    private final CarService carService = ServiceFactory.getCarService();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(true);
        Car car = (Car) session.getAttribute(CAR_PARAMETER);
        String name = request.getParameter(NAME_PARAMETER);
        String creditCardName = request.getParameter(CC_NAME_PARAMETER);
        String creditCardNumber = request.getParameter(CC_NUMBER_PARAMETER);
        String creditCardExpiration = request.getParameter(CC_EXPIRATION_PARAMETER);
        String creditCardCvv = request.getParameter(CC_CVV_PARAMETER);
        long totalPrice = (long) session.getAttribute(TOTAL_PRICE_PARAMETER);
        String pickUpDate = (String) session.getAttribute(PICKUP_DATE_PARAMETER);
        String dropOffDate = (String) session.getAttribute(DROPOFF_DATE_PARAMETER);
        boolean withDriver = !(session.getAttribute(DRIVER_PARAMETER) == null);
        User user = null;

        try {
            user = userService.checkUsernameChange(session, name);
        }catch (ServiceException exception){
            request.setAttribute(ERROR_PARAMETER, exception.getMessage());
            return BOOK_VIEW;
        }

        try{
            orderService.validateOrderPayment(creditCardName, creditCardNumber, creditCardExpiration, creditCardCvv);
        }catch (ServiceException exception){
            request.setAttribute(ERROR_PARAMETER, exception.getMessage());
            return BOOK_VIEW;
        }

        try{
            carService.carIsAvailable(car.getId());
        }catch (ServiceException exception){
            request.setAttribute(ERROR_PARAMETER, exception.getMessage());
            return INDEX_VIEW;
        }

        boolean isProcessed = false;
        isProcessed = orderService.processOrder(user, car, pickUpDate, dropOffDate, totalPrice, withDriver);
        if (isProcessed)
            carService.changeStatus(car.getId(), Status.BUSY);
        session.setAttribute(SUCCESS_MESSAGE_PARAMETER, SUCCESS_BOOKING_MESSAGE);
        return REDIRECT + MY_BOOKING_COMMAND;
    }

}
