package dao.impl;

import dao.OrderDao;
import dao.mapper.OrderMapper;
import dao.connectionPool.ConnectionPoolHolder;
import dao.mapper.OrderExtendedMapper;
import model.entity.Order;
import model.entity.OrderExtended;
import model.entity.enums.Status;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static controller.Constants.*;


/**
 * Implementation of OrderDAO
 *
 */
public class JDBCOrderImpl implements OrderDao {

    private static final Logger logger = Logger.getLogger(JDBCOrderImpl.class);

    public static final String SQL_CREATE_ORDER = "INSERT INTO CarRental.order(user_id, car_id, city_id, pickup_date, dropoff_date, total_price, status_id, driver) \n" +
            "values(?,?,?,?,?,?,?,?);";
    public static final String SQL_GET_ALL_ORDERS_BY_USER_ID_EXT = "SELECT o.id, o.user_id, (SELECT name FROM CarRental.user WHERE id = o.user_id) user_name, car_id, \n" +
            "(SELECT CONCAT(ci.name, ' ', ca.model) car_name FROM CarRental.car ca LEFT OUTER JOIN  CarRental.brand ci ON(ca.brand_id = ci.id) WHERE ca.id = o.car_id) car_name,\n" +
            " o.city_id, (SELECT name FROM CarRental.city WHERE id = o.city_id) city_name, o.pickup_date, o.dropoff_date, o.total_price,\n" +
            " o.status_id, o.driver, o.comment FROM CarRental.order o WHERE o.user_id=?";
    public static final String SQL_GET_ALL_ORDERS_BY_STATUS = "SELECT o.id, o.user_id, (SELECT name FROM CarRental.user WHERE id = o.user_id) user_name, car_id,\n" +
            "            (SELECT CONCAT(ci.name, ' ', ca.model) car_name FROM CarRental.car ca LEFT OUTER JOIN  CarRental.brand ci ON(ca.brand_id = ci.id) WHERE ca.id = o.car_id) car_name,\n" +
            "             o.city_id, (SELECT name FROM CarRental.city WHERE id = o.city_id) city_name, o.pickup_date, o.dropoff_date, o.total_price,\n" +
            "             o.status_id, o.driver, o.comment FROM CarRental.order o WHERE o.status_id =?";
    public static final String SQL_CHANGE_ORDER_STATUS_BY_ID = "UPDATE CarRental.order SET status_id = ? WHERE id=?";
    public static final String SQL_GET_ORDER_BY_ID = "SELECT * FROM CarRental.order where id=?";
    public static final String SQL_SET_STATUS_AND_COMMENT_BY_ID = "UPDATE CarRental.order SET status_id = ?, comment = ? WHERE id=?";
    public static final String SQL_SET_STATUS_AND_COMMENT_AND_PENALTY_BY_ID = "UPDATE CarRental.order SET status_id=?, total_price=?, comment=? where id=?";

    @Override
    public boolean create(Order entity) {
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getInstance().getConnection();
        }catch (SQLException exception){
            logger.error(SQL_GET_CONNECTION_ERROR);
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
        } catch (SQLException exception) {
            logger.error(SQL_QUERY_ERROR + exception.getMessage());
            return false;
        } finally {
            close(connection);
        }
        return true;
    }

    @Override
    public void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException exception) {
            logger.error(SQL_CLOSE_CONNECTION_ERROR);
        }
    }

    @Override
    public boolean finishOrderAndSetStatusAndPrice(int id, String comment, int totalPrice, Status status) {
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getInstance().getConnection();
        }catch (SQLException exception){
            logger.error(SQL_GET_CONNECTION_ERROR);
        }
        try (PreparedStatement statement = connection.prepareStatement(SQL_SET_STATUS_AND_COMMENT_AND_PENALTY_BY_ID)){
            statement.setInt(1, status.getStatus());
            statement.setInt(2, totalPrice);
            statement.setString(3, comment);
            statement.setInt(4, id);
            int count = statement.executeUpdate();
            if(count >0)
                return true;
        } catch (SQLException exception) {
            logger.error(SQL_QUERY_ERROR + exception.getMessage());
            return false;
        } finally {
            close(connection);

        }
        return false;
    }

    @Override
    public boolean declineOrderAndSetComment(int id, String comment, Status status) {
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getInstance().getConnection();
        }catch (SQLException exception){
            logger.error(SQL_GET_CONNECTION_ERROR);
        }
        try (PreparedStatement statement = connection.prepareStatement(SQL_SET_STATUS_AND_COMMENT_BY_ID)){
            statement.setInt(1, status.getStatus());
            statement.setString(2, comment);
            statement.setInt(3, id);
            int count = statement.executeUpdate();
            if(count >0)
                return true;
        } catch (SQLException exception) {
            logger.error(SQL_QUERY_ERROR + exception.getMessage());
            return false;
        } finally {
            close(connection);

        }
        return false;
    }

    @Override
    public Order findById(int orderId) {
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getInstance().getConnection();
        }catch (SQLException exception){
            logger.error(SQL_GET_CONNECTION_ERROR);
        }
        Order order = null;
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_ORDER_BY_ID)){
            statement.setInt(1, orderId);
            try (ResultSet rs = statement.executeQuery()) {
                if(rs.next())
                    order = OrderMapper.map(rs);
            }
        } catch (SQLException exception) {
            logger.error(SQL_QUERY_ERROR + exception.getMessage());
        } finally {
            close(connection);
        }
        return order;
    }

    @Override
    public boolean changeOrderStatusById(int orderId, Status status) {
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getInstance().getConnection();
        }catch (SQLException exception){
            logger.error(SQL_GET_CONNECTION_ERROR);
        }
        try (PreparedStatement statement = connection.prepareStatement(SQL_CHANGE_ORDER_STATUS_BY_ID)){
            statement.setInt(1, status.getStatus());
            statement.setInt(2, orderId);
            int count = statement.executeUpdate();
            if(count >0)
                return true;
        } catch (SQLException exception) {
            logger.error(SQL_QUERY_ERROR + exception.getMessage());
            return false;
        } finally {
            close(connection);

        }
        return false;
    }

    @Override
    public List<OrderExtended> findAllByUserId(int userId) {
        List<OrderExtended> orders = new ArrayList<>();
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getInstance().getConnection();
        }catch (SQLException exception){
            logger.error(SQL_GET_CONNECTION_ERROR);
        }
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL_ORDERS_BY_USER_ID_EXT)){
            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                orders.add(OrderExtendedMapper.map(rs));
            }
        } catch (SQLException exception) {
            logger.error(SQL_QUERY_ERROR + exception.getMessage());
        } finally {
            close(connection);
        }
        return orders;
    }

    @Override
    public List<OrderExtended> findAllByStatus(Status status) {
        List<OrderExtended> orders = new ArrayList<>();
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getInstance().getConnection();
        }catch (SQLException exception){
            logger.error(SQL_GET_CONNECTION_ERROR);
        }
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL_ORDERS_BY_STATUS)){
            statement.setInt(1, status.getStatus());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                orders.add(OrderExtendedMapper.map(rs));
            }
        } catch (SQLException exception) {
            logger.error(SQL_QUERY_ERROR + exception.getMessage());
        } finally {
            close(connection);
        }
        return orders;
    }
}
