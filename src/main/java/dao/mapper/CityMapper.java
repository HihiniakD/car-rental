package dao.mapper;

import model.entity.City;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CityMapper {

    private static final String FIELD_ID = "id";
    private static final String FIELD_NAME = "name";

    public static City map(ResultSet resultSet) throws SQLException {
        City city = new City();
        city.setId(resultSet.getInt(FIELD_ID));
        city.setName(resultSet.getString(FIELD_NAME));

        return city;
    }
}
