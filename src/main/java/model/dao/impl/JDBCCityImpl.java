package model.dao.impl;

import model.dao.CityDao;
import model.entity.City;

import java.sql.Connection;

public class JDBCCityImpl implements CityDao {
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

    }
}
