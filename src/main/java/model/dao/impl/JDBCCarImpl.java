package model.dao.impl;

import model.dao.CarDao;
import model.entity.Car;

import java.sql.Connection;
import java.util.List;

public class JDBCCarImpl implements CarDao {
    @Override
    public List<Car> findAllAvailableCars(String city) {
        return null;
    }

    @Override
    public List<Car> findAllCars() {
        return null;
    }

    @Override
    public Car findById(int id) {
        return null;
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

    }
}
