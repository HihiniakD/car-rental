package model.dao.impl;

import model.dao.CityDao;
import model.dao.connectionPool.ConnectionPoolHolder;
import model.dao.mapper.CityMapper;
import model.entity.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCCityImpl implements CityDao {

    public static final String SQL_GET_ALL_CITIES = "SELECT * FROM CarRental.city";

    @Override
    public boolean create(City entity) {
        return false;
    }

    @Override
    public boolean update(City entity) {
        return false;
    }

    @Override
    public boolean delete(City entity) {
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
    public List<City> findAllCities() {
        List<City> cities = new ArrayList();
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getInstance().getConnection();
        }catch (SQLException exception){
            exception.printStackTrace();
        }
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL_CITIES)){
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                cities.add(CityMapper.map(rs));
            }
        } catch (SQLException throwable) {
            // logger
            System.out.println(throwable.getMessage());
        } finally {
            close(connection);
        }
        return cities;
    }
}
