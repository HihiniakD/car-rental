package model.dao.impl;

import model.dao.BrandDao;
import model.dao.connectionPool.ConnectionPoolHolder;
import model.dao.mapper.BrandMapper;
import model.dao.mapper.CityMapper;
import model.entity.Brand;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCBrandImpl implements BrandDao {

    public static final String SQL_GET_ALL_BRANDS = "SELECT * FROM CarRental.brand";

    @Override
    public List<Brand> findAllABrands() {
        List<Brand> brands = new ArrayList();
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getInstance().getConnection();
        }catch (SQLException exception){
            exception.printStackTrace();
        }
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL_BRANDS)){
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                brands.add(BrandMapper.map(rs));
            }
        } catch (SQLException throwable) {
            // logger
            System.out.println(throwable.getMessage());
        } finally {
            close(connection);
        }
        return brands;
    }

    @Override
    public boolean create(Brand entity) {
        return false;
    }

    @Override
    public boolean update(Brand entity) {
        return false;
    }

    @Override
    public boolean delete(Brand entity) {
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