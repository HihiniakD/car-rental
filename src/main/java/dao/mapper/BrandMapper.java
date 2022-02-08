package dao.mapper;

import model.entity.Brand;

import java.sql.ResultSet;
import java.sql.SQLException;


public class BrandMapper {

    private static final String FIELD_ID = "id";
    private static final String FIELD_NAME = "name";

    public static Brand map(ResultSet resultSet) throws SQLException {
        Brand brand = new Brand();
        brand.setId(resultSet.getInt(FIELD_ID));
        brand.setName(resultSet.getString(FIELD_NAME));

        return brand;
    }
}
