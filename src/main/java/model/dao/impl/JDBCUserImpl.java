package model.dao.impl;

import model.dao.UserDao;
import model.dao.connectionPool.ConnectionPoolHolder;
import model.dao.mapper.UserMapper;
import model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JDBCUserImpl implements UserDao {

    public static final String SQL_CREATE_USER = "INSERT INTO CarRental.user (name, password, email, phone, role_id) VALUES (?,?,?,?,?)";
    public static final String SQL_GET_USER_BY_EMAIL = "SELECT * FROM CarRental.user WHERE email =?";


    private static final String FIELD_ID = "id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_EMAIl = "email";
    private static final String FIELD_PASSWORD = "password";
    private static final String FIELD_PHONE_NUMBER = "phone";
    private static final String FIELD_ROLE_ID = "role_id";

    @Override
    public boolean create(User entity) {
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getInstance().getConnection();
        }catch (SQLException exception){
            exception.printStackTrace();
        }
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_USER)){
            System.out.println("ONO VIPOLNYAETSA");
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getPassword());
            statement.setString(3, entity.getEmail());
            statement.setString(4, entity.getPhone());
            statement.setInt(5, entity.getRoleId());
            statement.executeUpdate();
        } catch (SQLException throwable) {
            // logger
            System.out.println(throwable.getMessage());
            return false;
        } finally {
            close(connection);
        }
        return true;

    }

    @Override
    public boolean update(User entity) {
        return false;
    }

    @Override
    public boolean delete(User entity) {
        return false;
    }

    @Override
    public void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException throwables) {
            //logger
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public User findUserByEmail(String email) {
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getInstance().getConnection();
        }catch (SQLException exception){
            exception.printStackTrace();
        }
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_USER_BY_EMAIL)){
            statement.setString(1, email);
            try (ResultSet rs = statement.executeQuery()) {
                if(rs.next())
                    user = UserMapper.map(rs);
            }
        } catch (SQLException throwable) {
            // logger
        } finally {
            close(connection);
        }
        System.out.println(user);
        return user;
    }

    @Override
    public List<User> findAllUsers() {
        return null;
    }
}
