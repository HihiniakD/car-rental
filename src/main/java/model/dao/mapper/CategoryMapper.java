package model.dao.mapper;

import model.entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper {

    private static final String FIELD_ID = "id";
    private static final String FIELD_NAME = "name";

    public static Category map(ResultSet resultSet) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getInt(FIELD_ID));
        category.setName(resultSet.getString(FIELD_NAME));

        return category;
    }
}
