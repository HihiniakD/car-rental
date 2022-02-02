package model.dao.mapper;

import model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {

    private static final String FIELD_ID = "id";
    private static final String FIELD_ROLE_ID = "role_id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_EMAIL = "email";
    private static final String FIELD_PHONE = "phone";
    private static final String FIELD_PASSWORD = "password";
    private static final String FIELD_BLOCKED = "blocked";

    public static User map(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt(FIELD_ID));
        user.setName(resultSet.getString(FIELD_NAME));
        user.setEmail(resultSet.getString(FIELD_EMAIL));
        user.setPhone(resultSet.getString(FIELD_PHONE));
        user.setPassword(resultSet.getString(FIELD_PASSWORD));
        user.setRoleId(resultSet.getInt(FIELD_ROLE_ID));
        user.setBlocked(resultSet.getBoolean(FIELD_BLOCKED));

        return user;
    }
}
