package service.impl;

import controller.security.Security;
import dao.OrderDao;
import dao.factory.DaoFactory;
import model.entity.Car;
import model.entity.Order;
import model.entity.OrderExtended;
import model.entity.User;
import model.entity.enums.Status;
import service.exception.ServiceException;
import service.OrderService;
import service.util.PriceService;

import java.time.LocalDate;
import java.util.List;

import static controller.Constants.*;

/**
 * Implementation of OrderService
 */
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = DaoFactory.getInstance().createOrderDao();

    @Override
    public boolean changeOrderStatusById(int orderId, Status status) {
        return orderDao.changeOrderStatusById(orderId, status);
    }

    @Override
    public boolean declineOrder(int id, String comment) {
        return orderDao.declineOrderAndSetComment(id, comment, Status.CANCELED);
    }

    @Override
    public boolean finishOrder(int id, String comment, String penalty) {
        validatePenaltyFee(penalty);
        int penaltyFee = Integer.parseInt(penalty);
        Order order = orderDao.findById(id);
        int newTotalPrice = order.getTotalPrice() + penaltyFee;
        return orderDao.finishOrderAndSetStatusAndPrice(id, comment, newTotalPrice, Status.DONE);
    }

    @Override
    public Order findOrderById(int orderId) {
        return orderDao.findById(orderId);
    }

    @Override
    public List<OrderExtended> findAllFinishedOrders() {
        return orderDao.findAllByStatus(Status.DONE);
    }

    @Override
    public List<OrderExtended> findAllNewOrders() {
        return orderDao.findAllByStatus(Status.PROCESSING);
    }

    @Override
    public List<OrderExtended> findAllInProgressOrders() {
        return orderDao.findAllByStatus(Status.APPROVED);
    }

    @Override
    public List<OrderExtended> findAllDeclinedOrders() {
        return orderDao.findAllByStatus(Status.CANCELED);
    }

    @Override
    public List<OrderExtended> findAllByUserId(int userId) {
        return orderDao.findAllByUserId(userId);
    }

    @Override
    public long calculateTotalPrice(int price, long days) {
        return PriceService.calculateTotalPrice(price, days);
    }

    @Override
    public long calculateCarDriverPrice(long days) {
        return PriceService.calculateCarDrivePrice(days);
    }

    @Override
    public boolean processOrder(User user, Car car, String pickUpDate, String dropOffDate, long totalPrice, boolean withDriver) {
        Order order = new Order();
        order.setUserId(user.getId());
        order.setCarId(car.getId());
        order.setCityId(car.getCityId());
        order.setPickupDate(LocalDate.parse(pickUpDate));
        order.setDropoffDate(LocalDate.parse(dropOffDate));
        order.setTotalPrice((int) totalPrice);
        order.setStatusId(Status.PROCESSING);
        order.setWithDriver(withDriver);
        return orderDao.create(order);
    }

    /**
     * Payment form validation
     */
    public boolean validateOrderPayment(String creditCardName, String creditCardNumber,
                                        String creditCardExpiration, String creditCardCvv) {
        if (!Security.isCreditCardNameValid(creditCardName))
            throw new ServiceException(CC_NAME_NOT_VALID_ERROR);

        if (!Security.isCreditCardNumberValid(creditCardNumber))
            throw new ServiceException(CC_NUMBER_NOT_VALID_ERROR);

        if (!Security.isCreditCardExpirationValid(creditCardExpiration))
            throw new ServiceException(CC_EXPIRATION_NOT_VALID_ERROR);

        if (!Security.isCreditCardCvvValid(creditCardCvv))
            throw new ServiceException(CC_CVV_NOT_VALID_ERROR);

        return true;
    }

    /**
     * FinishBooking form validation
     */
    public boolean validatePenaltyFee(String penaltyFee) {
        if (penaltyFee == null || penaltyFee.isBlank())
            throw new ServiceException(FAIL_MESSAGE);

        try {
            Integer.parseInt(penaltyFee);
        }catch (NumberFormatException exception){
            throw new ServiceException(FAIL_MESSAGE);
        }
        return true;
    }
}
