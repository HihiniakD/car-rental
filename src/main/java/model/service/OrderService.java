package model.service;

import model.entity.Car;
import model.entity.Order;
import model.entity.OrderExtended;
import model.entity.User;
import model.entity.enums.Status;

import java.util.List;

public interface OrderService {
    boolean changeOrderStatusById(int orderId, Status status);
    boolean declineOrder(int id, String comment);
    boolean finishOrder(int id, String comment, String penalty);
    Order findOrderById(int orderId);
    List<OrderExtended> findAllFinishedOrders();
    List<OrderExtended> findAllNewOrders();
    List<OrderExtended> findAllInProgressOrders();
    List<OrderExtended> findAllDeclinedOrders();
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
