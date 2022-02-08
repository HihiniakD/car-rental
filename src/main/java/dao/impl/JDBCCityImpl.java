package dao.impl;

import dao.CityDao;
import dao.connectionPool.ConnectionPoolHolder;
import dao.mapper.CityMapper;
import model.entity.City;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static controller.Constants.*;

/**
 * Implementation of CityDAO
 *
 */
public class JDBCCityImpl implements CityDao {

    private static final Logger logger = Logger.getLogger(JDBCCityImpl.class);

    public static final String SQL_GET_ALL_CITIES = "SELECT * FROM CarRental.city";
    public static final String SQL_GET_CITY_NAME_BY_ID = "SELECT name FROM CarRental.city where id=?";

    @Override
    public boolean create(City entity) {
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

    @Override
    public List<City> findAllCities() {
        List<City> cities = new ArrayList();
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getInstance().getConnection();
        }catch (SQLException exception){
            logger.error(SQL_GET_CONNECTION_ERROR);
        }
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL_CITIES)){
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                cities.add(CityMapper.map(rs));
            }
        } catch (SQLException exception) {
            logger.error(SQL_QUERY_ERROR + exception.getMessage());
        } finally {
            close(connection);
        }
        return cities;
    }

    @Override
    public String getCityNameById(int id) {
        String cityName = "";
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getInstance().getConnection();
        }catch (SQLException exception){
            logger.error(SQL_GET_CONNECTION_ERROR);
        }
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_CITY_NAME_BY_ID)){
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                cityName = rs.getString(1);
            }
        } catch (SQLException exception) {
            logger.error(SQL_QUERY_ERROR + exception.getMessage());
        } finally {
            close(connection);
        }
        return cityName;
    }
}
