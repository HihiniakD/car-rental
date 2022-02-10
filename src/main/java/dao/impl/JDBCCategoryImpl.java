package dao.impl;

import dao.CategoryDao;
import dao.connectionPool.ConnectionPoolHolder;
import dao.mapper.CategoryMapper;
import model.entity.Category;
import org.apache.log4j.Logger;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static controller.Constants.*;

/**
 * Implementation of CategoryDAO
 *
 */
public class JDBCCategoryImpl implements CategoryDao {

    private static final Logger logger = Logger.getLogger(JDBCCategoryImpl.class);

    public static final String SQL_GET_ALL_CATEGORIES = "SELECT * FROM CarRental.category";

    @Override
    public List<Category> findAllCategories() {
        List<Category> categories = new ArrayList();
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getInstance().getConnection();
        }catch (SQLException exception){
            logger.error(SQL_GET_CONNECTION_ERROR);
        }
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL_CATEGORIES)){
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                categories.add(CategoryMapper.map(rs));
            }
        } catch (SQLException exception) {
            logger.error(SQL_QUERY_ERROR + exception.getMessage());
        } finally {
            close(connection);
        }
        return categories;
    }

    @Override
    public boolean create(Category entity) {
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
