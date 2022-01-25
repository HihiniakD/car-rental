package model.dao;

import model.entity.Order;
import model.entity.User;

import java.util.List;

public interface OrderDao extends GenericDao<Order> {
    List<Order> findByUser(User user);
    List<Order> findAll();
    List<Order> findAllById(int userId);
    List<Order> findAllSortedByPrice(User user);
    List<Order> findAllSortedByDate(User user);
}
