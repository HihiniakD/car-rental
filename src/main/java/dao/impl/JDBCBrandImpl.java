package dao.impl;

import dao.BrandDao;
import dao.connectionPool.ConnectionPoolHolder;
import dao.mapper.BrandMapper;
import model.entity.Brand;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static controller.Constants.*;

/**
 * Implementation of BrandDAO
 *
 */
public class JDBCBrandImpl implements BrandDao {

    private static final Logger logger = Logger.getLogger(JDBCBrandImpl.class);

    public static final String SQL_GET_ALL_BRANDS = "SELECT * FROM CarRental.brand";

    @Override
    public List<Brand> findAllABrands() {
        List<Brand> brands = new ArrayList();
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getInstance().getConnection();
        }catch (SQLException exception){
            logger.error(SQL_GET_CONNECTION_ERROR);
        }
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL_BRANDS)){
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                brands.add(BrandMapper.map(rs));
            }
        } catch (SQLException exception) {
            logger.error(SQL_QUERY_ERROR + exception.getMessage());
        } finally {
            close(connection);
        }
        return brands;
    }

    @Override
    public boolean create(Brand entity) {
        return false;
    } // TODO

    @Override
    public void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException exception) {
            logger.error(SQL_CLOSE_CONNECTION_ERROR);
        }
    }
}
