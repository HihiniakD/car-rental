package model.mapper;

import model.entity.City;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CityMapper {
    public static City map(ResultSet resultSet) throws SQLException {
        City city = new City();
        city.setId(resultSet.getInt("id"));
        city.setName(resultSet.getString("name"));

        return city;
    }
}
