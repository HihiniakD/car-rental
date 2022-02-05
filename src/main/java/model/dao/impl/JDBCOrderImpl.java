package model.dao.impl;

import model.dao.OrderDao;
import model.dao.connectionPool.ConnectionPoolHolder;
import model.dao.mapper.CityMapper;
import model.dao.mapper.OrderExtendedMapper;
import model.entity.City;
import model.entity.Order;
import model.entity.OrderExtended;
import model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCOrderImpl implements OrderDao {

    public static final String SQL_CREATE_ORDER = "INSERT INTO CarRental.order(user_id, car_id, city_id, pickup_date, dropoff_date, total_price, status_id, driver) \n" +
            "values(?,?,?,?,?,?,?,?);";
    public static final String SQL_GET_ALL_ORDERS_BY_USER_ID_EXT = "SELECT o.id, o.user_id, (SELECT name FROM CarRental.user WHERE id = o.user_id) user_name, car_id, \n" +
            "(SELECT CONCAT(ci.name, ' ', ca.model) car_name FROM CarRental.car ca LEFT OUTER JOIN  CarRental.brand ci ON(ca.brand_id = ci.id) WHERE ca.id = o.car_id) car_name,\n" +
            " o.city_id, (SELECT name FROM CarRental.city WHERE id = o.city_id) city_name, o.pickup_date, o.dropoff_date, o.total_price,\n" +
            " o.status_id, o.driver, o.comment FROM CarRental.order o WHERE o.user_id=?";

    @Override
    public boolean create(Order entity) {
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getInstance().getConnection();
        }catch (SQLException exception){
            exception.printStackTrace();
        }
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_ORDER)){
            statement.setInt(1, entity.getUserId());
            statement.setInt(2, entity.getCarId());
            statement.setInt(3, entity.getCityId());
            statement.setObject(4, entity.getPickupDate());
            statement.setObject(5, entity.getDropoffDate());
            statement.setInt(6, entity.getTotalPrice());
            statement.setInt(7, entity.getStatusId().getStatus());
            statement.setBoolean(8, entity.isWithDriver());
            statement.executeUpdate();
        } catch (SQLException throwable) {
            // logger
            System.out.println(throwable.getMessage());
            return false;
        } finally {
            close(connection);
        }
        return true;
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
        try {
            connection.close();
        } catch (SQLException throwables) {
            //logger
            throw new RuntimeException(throwables);
        }
    }


    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public List<OrderExtended> findAllByUserId(int userId) {
        List<OrderExtended> orders = new ArrayList();
        System.out.println(userId);
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getInstance().getConnection();
        }catch (SQLException exception){
            exception.printStackTrace();
        }
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL_ORDERS_BY_USER_ID_EXT)){
            statement.setInt(1, userId);
            System.out.println(statement + " statement");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                orders.add(OrderExtendedMapper.map(rs));
            }
        } catch (SQLException throwable) {
            // logger
            System.out.println(throwable.getMessage());
        } finally {
            close(connection);
        }
        return orders;
    }


}
