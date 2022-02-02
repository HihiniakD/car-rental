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

    public static final String SQL_CREATE_USER = "INSERT INTO CarRental.user (name, password, email, phone, role_id, blocked) VALUES (?,?,?,?,?,?)";
    public static final String SQL_GET_USER_BY_EMAIL = "SELECT * FROM CarRental.user WHERE email =?";

    @Override
    public boolean create(User entity) {
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getInstance().getConnection();
        }catch (SQLException exception){
            exception.printStackTrace();
        }
        try (PreparedStatement statement = connection.prepareStatement(SQL_CREATE_USER)){
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getPassword());
            statement.setString(3, entity.getEmail());
            statement.setString(4, entity.getPhone());
            statement.setInt(5, entity.getRoleId());
            statement.setBoolean(6, entity.isBlocked());
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
        System.out.println("EMAIL IN JDBC METHOD " +email);
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
            System.out.println(throwable.getMessage());
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
