package model.dao;

import model.entity.Order;
import model.entity.OrderExtended;
import model.entity.User;
import model.entity.enums.Status;

import java.util.List;

public interface OrderDao extends GenericDao<Order> {
    boolean finishOrderAndSetStatusAndPrice(int id, String comment, int totalPrice, Status status);
    boolean declineOrderAndSetComment(int id, String comment, Status status);
    Order findById(int orderId);
    public boolean changeOrderStatusById(int orderId, Status status);
    List<OrderExtended> findAllByUserId(int userId);
    List<OrderExtended> findAllByStatus(Status status);
}
