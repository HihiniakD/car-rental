package model.service.impl;

import model.entity.Order;
import model.entity.User;
import model.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public List<Order> findAllById(int userId) {
        return null;
    }

    @Override
    public List<Order> findAllSortedByPrice(User user) {
        return null;
    }

    @Override
    public List<Order> findAllSortedByDate(User user) {
        return null;
    }
}
