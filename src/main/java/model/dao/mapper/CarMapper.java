package model.dao.mapper;

import model.entity.Car;
import model.entity.enums.Status;
import model.entity.enums.Transmission;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarMapper {
    public static Car map(ResultSet resultSet) throws SQLException {
        Car car = new Car();
        car.setId(resultSet.getInt("id"));
        car.setModel(resultSet.getString("model"));
        car.setPassengers(resultSet.getInt("passengers"));
        car.setPrice(resultSet.getInt("price"));
        car.setStatus((Status) resultSet.getObject("status"));
        car.setTransmission((Transmission) resultSet.getObject("transmission"));
        car.setCategory(resultSet.getString("category"));
        car.setCityId(resultSet.getInt("city_id"));
        car.setImageUrl(resultSet.getString("image_url"));

        return car;
    }
}
