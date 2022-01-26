package model.service;

import model.entity.Order;
import model.entity.User;

import java.util.List;

public interface OrderService {
    List<Order> findAll();
    List<Order> findAllById(int userId);
    List<Order> findAllSortedByPrice(User user);
    List<Order> findAllSortedByDate(User user);
    // добавить методы для пагинации
    /// Code here


}
