package model.service;

import model.entity.Car;
import model.entity.Order;
import model.entity.OrderExtended;
import model.entity.User;

import java.util.List;

public interface OrderService {
    List<Order> findAll();
    List<OrderExtended> findAllByUserId(int userId);
    long calculateTotalPrice(int price, long days);
    long calculateCarDriverPrice(long days);
    boolean processOrder(User user, Car car, String pickUpDate,
                         String dropOffDate, long totalPrice, boolean withDriver);
    public boolean validateOrderPayment(String creditCardName, String creditCardNumber,
                                        String creditCardExpiration, String creditCardCvv);
    // добавить методы для пагинации
    /// Code here


}
