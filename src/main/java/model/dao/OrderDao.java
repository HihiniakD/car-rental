package model.dao;

import model.entity.Order;
import model.entity.OrderExtended;
import model.entity.User;

import java.util.List;

public interface OrderDao extends GenericDao<Order> {
    List<Order> findAll();
    List<OrderExtended> findAllByUserId(int userId);
}
