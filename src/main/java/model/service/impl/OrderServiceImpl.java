package model.service.impl;

import controller.security.Security;
import model.dao.OrderDao;
import model.dao.factory.DaoFactory;
import model.entity.Car;
import model.entity.Order;
import model.entity.OrderExtended;
import model.entity.User;
import model.entity.enums.Status;
import model.exception.ServiceException;
import model.service.OrderService;
import model.util.PriceSevice;

import java.time.LocalDate;
import java.util.List;

import static controller.Constants.*;

public class OrderServiceImpl implements OrderService {

    OrderDao orderDao = DaoFactory.getInstance().createOrderDao();

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public List<OrderExtended> findAllByUserId(int userId) {
        return orderDao.findAllByUserId(userId);
    }

    @Override
    public long calculateTotalPrice(int price, long days) {
        return PriceSevice.calculateTotalPrice(price, days);
    }

    @Override
    public long calculateCarDriverPrice(long days) {
        return PriceSevice.calculateCarDrivePrice(days);
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
}
