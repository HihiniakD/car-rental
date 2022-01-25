package model.dao.mapper;

import model.entity.Order;
import model.entity.enums.Status;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class OrderMapper {
    public static Order map(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getInt("id"));
        order.setUserId(resultSet.getInt("user_id"));
        order.setCarId(resultSet.getInt("car_id"));
        order.setCityId(resultSet.getInt("city_id"));
        order.setPickupDate((LocalDate) resultSet.getObject("pickup_date"));
        order.setDropoffDate((LocalDate) resultSet.getObject("dropoff_date"));
        order.setTotalPrice(resultSet.getInt("total_price"));
        order.setStatus((Status) resultSet.getObject("status"));

        return order;

    }
}
