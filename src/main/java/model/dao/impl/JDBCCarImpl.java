package model.dao.impl;

import model.dao.CarDao;
import model.dao.connectionPool.ConnectionPoolHolder;
import model.dao.mapper.CarMapper;
import model.dao.mapper.UserMapper;
import model.entity.Car;
import model.entity.User;
import model.entity.enums.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCCarImpl implements CarDao {

    public static final String SQL_GET_ALL_AVAILABLE_CARS_BY_BRAND_CITY_CATEGORY = "SELECT  c.id, c.brand_id, CONCAT(b.name, ' ', c.model) model, c.passengers, c.price, c.status_id, c.transmission, c.city_id, c.category_id, c.image_url FROM carrental.car c LEFT OUTER JOIN carrental.brand b ON (c.brand_id = b.id) WHERE c.city_id=? AND c.category_id =? AND c.brand_id =? AND c.status_id =?";
    public static final String SQL_GET_CAR_BY_ID = "SELECT  c.id, c.brand_id, CONCAT(b.name, ' ', c.model) model, c.passengers, c.price, c.status_id, c.transmission, c.city_id, c.category_id, c.image_url FROM carrental.car c LEFT OUTER JOIN carrental.brand b ON (c.brand_id = b.id) WHERE c.id=?";
    public static final String SQL_CHECK_IS_AVAILABLE_BY_ID = "SELECT * FROM carrental.car WHERE id=? AND status_id=?";
    public static final String SQL_CHANGE_CAR_STATUS_BY_ID = "UPDATE carrental.car SET status_id=? WHERE id=?";
    public static final String SQL_GET_ALL_AVAILABLE_CARS_BY_BRAND_CITY_CATEGORY_SORTED_BY_NAME = "SELECT  c.id, c.brand_id, CONCAT(b.name, ' ', c.model) model, c.passengers, c.price, c.status_id, c.transmission, c.city_id, c.category_id, c.image_url FROM carrental.car c LEFT OUTER JOIN carrental.brand b ON (c.brand_id = b.id) WHERE c.city_id=? AND c.category_id =? AND c.brand_id =? AND c.status_id =? ORDER BY model";
    public static final String SQL_GET_ALL_AVAILABLE_CARS_BY_BRAND_CITY_CATEGORY_SORTED_BY_PRICE = "SELECT  c.id, c.brand_id, CONCAT(b.name, ' ', c.model) model, c.passengers, c.price, c.status_id, c.transmission, c.city_id, c.category_id, c.image_url FROM carrental.car c LEFT OUTER JOIN carrental.brand b ON (c.brand_id = b.id) WHERE c.city_id=? AND c.category_id =? AND c.brand_id =? AND c.status_id =? ORDER BY c.price";

    private static final int FIELD_AVAILABLE = Status.AVAILABLE.getStatus();

    @Override
    public List<Car> findAllAvailableCars(int cityId, int categoryId, int brandId) {
        List<Car> cars = new ArrayList<>();
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getInstance().getConnection();
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
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
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        } finally {
            close(connection);
        }
        return cars;
    }

    @Override
    public List<Car> findAllCars() {
        return null;
    }


    @Override
    public Car findById(int id) {
        Car car = null;
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getInstance().getConnection();
        }catch (SQLException exception){
            exception.printStackTrace();
        }
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_CAR_BY_ID)){
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                if(rs.next())
                    car = CarMapper.map(rs);
            }
        } catch (SQLException throwable) {
            System.out.println(throwable.getMessage());
        } finally {
            close(connection);
        }
        System.out.println(car);
        return car;

    }

    @Override
    public boolean checkIsAvailable(int id) {
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getInstance().getConnection();
        }catch (SQLException exception){
            exception.printStackTrace();
        }
        try (PreparedStatement statement = connection.prepareStatement(SQL_CHECK_IS_AVAILABLE_BY_ID)){
            statement.setInt(1, id);
            statement.setInt(2, FIELD_AVAILABLE);
            try (ResultSet rs = statement.executeQuery()) {
                if(rs.next())
                    return true;
            }
        } catch (SQLException throwable) {
            System.out.println(throwable.getMessage());
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
            exception.printStackTrace();
        }
        try (PreparedStatement statement = connection.prepareStatement(SQL_CHANGE_CAR_STATUS_BY_ID)){
            statement.setInt(1, status.getStatus());
            statement.setInt(2, id);
                int count = statement.executeUpdate();
                if(count >0)
                    return true;
        } catch (SQLException throwable) {
            System.out.println(throwable.getMessage());
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
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
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
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
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
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        }
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL_AVAILABLE_CARS_BY_BRAND_CITY_CATEGORY_SORTED_BY_NAME)) {
            statement.setInt(1, cityId);
            statement.setInt(2, categoryId);
            statement.setInt(3, brandId);
            statement.setInt(4, FIELD_AVAILABLE);
//            statement.setString(5, FIELD_MODEL);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    cars.add(CarMapper.map(rs));
                }
            }
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
        } finally {
            close(connection);
        }
        return cars;
    }

    @Override
    public boolean create(Car entity) {
        return false;
    }

    @Override
    public boolean update(Car entity) {
        return false;
    }

    @Override
    public boolean delete(Car entity) {
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
}
