package model.dao.mapper;

import model.entity.Car;
import model.entity.enums.Status;
import model.entity.enums.Transmission;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarMapper {

    private static final String FIELD_ID = "id";
    private static final String FIELD_BRAND_ID = "brand_id";
    private static final String FIELD_MODEL = "model";
    private static final String FIELD_PASSENGERS = "passengers";
    private static final String FIELD_PRICE = "price";
    private static final String FIELD_STATUS_ID = "status_id";
    private static final String FIELD_TRANSMISSION = "transmission";
    private static final String FIELD_CITY_ID = "city_id";
    private static final String FIELD_CATEGORY_ID = "category_id";
    private static final String FIELD_IMAGE_URL = "image_url";

    public static Car map(ResultSet resultSet) throws SQLException {
        Car car = new Car();
        car.setId(resultSet.getInt(FIELD_ID));
        car.setBrandId(resultSet.getInt(FIELD_BRAND_ID));
        car.setModel(resultSet.getString(FIELD_MODEL));
        car.setPassengers(resultSet.getInt(FIELD_PASSENGERS));
        car.setPrice(resultSet.getInt(FIELD_PRICE));
        car.setStatusId(Status.geStatus(resultSet.getInt(FIELD_STATUS_ID)));
        car.setTransmission(Transmission.valueOf(resultSet.getString(FIELD_TRANSMISSION).toUpperCase()));
        car.setCityId(resultSet.getInt(FIELD_CITY_ID));
        car.setCategoryId(resultSet.getInt(FIELD_CATEGORY_ID));
        car.setImageUrl(resultSet.getString(FIELD_IMAGE_URL));

        return car;
    }
}
