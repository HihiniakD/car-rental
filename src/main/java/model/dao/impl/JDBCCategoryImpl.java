package model.dao.impl;

import model.dao.CategoryDao;
import model.dao.connectionPool.ConnectionPoolHolder;
import model.dao.mapper.CategoryMapper;
import model.entity.Category;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCCategoryImpl implements CategoryDao {

    public static final String SQL_GET_ALL_CATEGORIES = "SELECT * FROM CarRental.category";

    @Override
    public List<Category> findAllCategories() {
        List<Category> categories = new ArrayList();
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getInstance().getConnection();
        }catch (SQLException exception){
            //logger
        }
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL_CATEGORIES)){
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                categories.add(CategoryMapper.map(rs));
            }
        } catch (SQLException throwable) {
            // logger
        } finally {
            close(connection);
        }
        return categories;
    }

    @Override
    public boolean create(Category entity) {
        return false;
    } //TODO

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
