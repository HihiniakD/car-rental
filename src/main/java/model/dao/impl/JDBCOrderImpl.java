package model.dao.impl;

import model.dao.OrderDao;
import model.entity.Order;
import model.entity.User;

import java.sql.Connection;
import java.util.List;

public class JDBCOrderImpl implements OrderDao {
    @Override
    public boolean create(Order entity) {
        return false;
    }

    @Override
    public boolean update(Order entity) {
        return false;
    }

    @Override
    public boolean delete(Order entity) {
        return false;
    }

    @Override
    public void close(Connection connection) {

    }

    @Override
    public List<Order> findByUser(User user) {
        return null;
    }

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
