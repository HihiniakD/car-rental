package dao.impl;

import dao.CarDao;
import dao.mapper.CarMapper;
import dao.connectionPool.ConnectionPoolHolder;
import model.entity.Car;
import model.entity.enums.Status;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static controller.Constants.*;


/**
 * Implementation of CarDAO
 *
 */
public class JDBCCarImpl implements CarDao {

    private static final Logger logger = Logger.getLogger(JDBCCarImpl.class);

    public static final String SQL_GET_ALL_AVAILABLE_CARS_BY_BRAND_CITY_CATEGORY = "SELECT  c.id, c.brand_id, CONCAT(b.name, ' ', c.model) model, c.passengers, c.price, c.status_id, c.transmission, c.city_id, c.category_id, c.image_url FROM carrental.car c LEFT OUTER JOIN carrental.brand b ON (c.brand_id = b.id) WHERE c.city_id=? AND c.category_id =? AND c.brand_id =? AND c.status_id =?";
    public static final String SQL_GET_CAR_BY_ID = "SELECT  c.id, c.brand_id, CONCAT(b.name, ' ', c.model) model, c.passengers, c.price, c.status_id, c.transmission, c.city_id, c.category_id, c.image_url FROM carrental.car c LEFT OUTER JOIN carrental.brand b ON (c.brand_id = b.id) WHERE c.id=?";
    public static final String SQL_CHECK_IS_AVAILABLE_BY_ID = "SELECT * FROM carrental.car WHERE id=? AND status_id=?";
    public static final String SQL_CHANGE_CAR_STATUS_BY_ID = "UPDATE carrental.car SET status_id=? WHERE id=?";
    public static final String SQL_GET_ALL_AVAILABLE_CARS_BY_BRAND_CITY_CATEGORY_SORTED_BY_NAME = "SELECT  c.id, c.brand_id, CONCAT(b.name, ' ', c.model) model, c.passengers, c.price, c.status_id, c.transmission, c.city_id, c.category_id, c.image_url FROM carrental.car c LEFT OUTER JOIN carrental.brand b ON (c.brand_id = b.id) WHERE c.city_id=? AND c.category_id =? AND c.brand_id =? AND c.status_id =? ORDER BY model";
    public static final String SQL_GET_ALL_AVAILABLE_CARS_BY_BRAND_CITY_CATEGORY_SORTED_BY_PRICE = "SELECT  c.id, c.brand_id, CONCAT(b.name, ' ', c.model) model, c.passengers, c.price, c.status_id, c.transmission, c.city_id, c.category_id, c.image_url FROM carrental.car c LEFT OUTER JOIN carrental.brand b ON (c.brand_id = b.id) WHERE c.city_id=? AND c.category_id =? AND c.brand_id =? AND c.status_id =? ORDER BY c.price";
    public static final String SQL_GET_ALL_CARS = "SELECT c.id, c.brand_id, CONCAT((SELECT name FROM CarRental.brand where id = c.brand_id) ,' ', c.model) model, c.passengers, c.price, c.status_id, c.transmission, c.city_id, \n" +
            "c.category_id, c.image_url FROM  CarRental.car c";
    private static final int FIELD_AVAILABLE = Status.AVAILABLE.getStatus();
    private static final String SQL_DELETE_CAR_BY_ID = "DELETE FROM CarRental.car WHERE id=?";
    private static final String SQL_EDIT_CAR_BY_ID = "UPDATE CarRental.car SET price=?, image_url=? WHERE id=?";
    private static final String SQL_ADD_CAR = "INSERT INTO CarRental.car(brand_id, model, passengers, price, status_id, transmission, city_id, category_id, image_url) VALUES(?,?,?,?,?,?,?,?,?)";

    @Override
    public boolean deleteCar(int carId) {
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getInstance().getConnection();
        }catch (SQLException exception){
            logger.error(SQL_GET_CONNECTION_ERROR);
        }
        try (PreparedStatement statement = connection.prepareStatement(SQL_DELETE_CAR_BY_ID)){
            statement.setInt(1, carId);
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
    public boolean editCar(int carId, int price, String imageUrl) {
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getInstance().getConnection();
        }catch (SQLException exception){
            logger.error(SQL_GET_CONNECTION_ERROR);
        }
        try (PreparedStatement statement = connection.prepareStatement(SQL_EDIT_CAR_BY_ID)){
            statement.setInt(1, price);
            statement.setString(2, imageUrl);
            statement.setInt(3, carId);
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
    public List<Car> findAllAvailableCars(int cityId, int categoryId, int brandId) {
        List<Car> cars = new ArrayList<>();
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getInstance().getConnection();
        } catch (SQLException throwables) {
            logger.error(SQL_GET_CONNECTION_ERROR);
        }
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL_AVAILABLE_CARS_BY_BRAND_CITY_CATEGORY)) {
            statement.setInt(1, cityId);
            statement.setInt(2, categoryId);
            statement.setInt(3, brandId);
            statement.setInt(4, FIELD_AVAILABLE);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    cars.add(CarMapper.map(rs));
                }
            }
        } catch (SQLException exception) {
            logger.error(SQL_QUERY_ERROR + exception.getMessage());
        } finally {
            close(connection);
        }
        return cars;
    }

    @Override
    public List<Car> findAllCars() {

        List<Car> cars = new ArrayList<>();
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getInstance().getConnection();
        } catch (SQLException throwables) {
            logger.error(SQL_GET_CONNECTION_ERROR);
        }
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL_CARS)) {
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    cars.add(CarMapper.map(rs));
                }
            }
        } catch (SQLException exception) {
            logger.error(SQL_QUERY_ERROR + exception.getMessage());
        } finally {
            close(connection);
        }
        return cars;
    }


    @Override
    public Car findById(int id) {
        Car car = null;
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getInstance().getConnection();
        }catch (SQLException exception){
            logger.error(SQL_GET_CONNECTION_ERROR);
        }
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_CAR_BY_ID)){
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if(rs.next())
                    car = CarMapper.map(rs);
            }
        } catch (SQLException exception) {
            logger.error(SQL_QUERY_ERROR + exception.getMessage());
        } finally {
            close(connection);
        }
        return car;

    }

    @Override
    public boolean checkIsAvailable(int id) {
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getInstance().getConnection();
        }catch (SQLException exception){
            logger.error(SQL_GET_CONNECTION_ERROR);
        }
        try (PreparedStatement statement = connection.prepareStatement(SQL_CHECK_IS_AVAILABLE_BY_ID)){
            statement.setInt(1, id);
            statement.setInt(2, FIELD_AVAILABLE);
            try (ResultSet rs = statement.executeQuery()) {
                if(rs.next())
                    return true;
            }
        } catch (SQLException exception) {
            logger.error(SQL_QUERY_ERROR + exception.getMessage());
            return false;
        } finally {
            close(connection);
        }
        return false;
    }

    @Override
    public boolean changeCarStatus(int id, Status status) {
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getInstance().getConnection();
        }catch (SQLException exception){
            logger.error(SQL_GET_CONNECTION_ERROR);
        }
        try (PreparedStatement statement = connection.prepareStatement(SQL_CHANGE_CAR_STATUS_BY_ID)){
            statement.setInt(1, status.getStatus());
            statement.setInt(2, id);
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
    public List<Car> findAllAvailableCarsSortedByPrice(int cityId, int categoryId, int brandId) {
        List<Car> cars = new ArrayList<>();
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getInstance().getConnection();
        } catch (SQLException exception) {
            logger.error(SQL_GET_CONNECTION_ERROR);
        }
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL_AVAILABLE_CARS_BY_BRAND_CITY_CATEGORY_SORTED_BY_PRICE)) {
            statement.setInt(1, cityId);
            statement.setInt(2, categoryId);
            statement.setInt(3, brandId);
            statement.setInt(4, FIELD_AVAILABLE);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    cars.add(CarMapper.map(rs));
                }
            }
        } catch (SQLException exception) {
            logger.error(SQL_QUERY_ERROR + exception.getMessage());
        } finally {
            close(connection);
        }
        return cars;
    }

    @Override
    public List<Car> findAllAvailableCarsSortedByName(int cityId, int categoryId, int brandId) {
        List<Car> cars = new ArrayList<>();
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getInstance().getConnection();
        } catch (SQLException exception) {
            logger.error(SQL_GET_CONNECTION_ERROR);
        }
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL_AVAILABLE_CARS_BY_BRAND_CITY_CATEGORY_SORTED_BY_NAME)) {
            statement.setInt(1, cityId);
            statement.setInt(2, categoryId);
            statement.setInt(3, brandId);
            statement.setInt(4, FIELD_AVAILABLE);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    cars.add(CarMapper.map(rs));
                }
            }
        } catch (SQLException exception) {
            logger.error(SQL_QUERY_ERROR + exception.getMessage());
        } finally {
            close(connection);
        }
        return cars;
    }

    @Override
    public boolean create(Car entity) {
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getInstance().getConnection();
        }catch (SQLException exception){
            logger.error(SQL_GET_CONNECTION_ERROR);
        }
        try (PreparedStatement statement = connection.prepareStatement(SQL_ADD_CAR)){
            statement.setInt(1, entity.getBrandId());
            statement.setString(2, entity.getModel());
            statement.setInt(3, entity.getPassengers());
            statement.setInt(4, entity.getPrice());
            statement.setInt(5, entity.getStatusId().getStatus());
            statement.setString(6, entity.getTransmission().toString().toLowerCase());
            statement.setInt(7, entity.getCityId());
            statement.setInt(8, entity.getCategoryId());
            statement.setString(9, entity.getImageUrl());
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
    public void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException exception) {
            logger.error(SQL_CLOSE_CONNECTION_ERROR);
        }
    }
}
