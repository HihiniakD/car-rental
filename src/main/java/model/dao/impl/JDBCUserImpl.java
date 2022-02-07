package model.dao.impl;

import model.dao.UserDao;
import model.dao.connectionPool.ConnectionPoolHolder;
import model.dao.mapper.UserMapper;
import model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCUserImpl implements UserDao {

    public static final String SQL_CREATE_USER = "INSERT INTO CarRental.user (name, password, email, phone, role_id, blocked) VALUES (?,?,?,?,?,?)";
    public static final String SQL_GET_USER_BY_EMAIL = "SELECT * FROM CarRental.user WHERE email =?";
    public static final String SQL_CHANGE_USER_NAME_BY_ID = "UPDATE CarRental.user SET name=? WHERE id=?";
    private static final String SQL_SELECT_USERS_COUNT = "SELECT COUNT(id) FROM CarRental.user";
    private static final String SQL_SELECT_USERS_BY_LIMIT = "SELECT * FROM CarRental.user WHERE role_id=1 LIMIT ?,?";
    private static final String SQL_CHANGE_BLOCKED_STATUS = "UPDATE CarRental.user SET blocked=? WHERE id =?";
    private static final String SQL_GET_ALL_MANAGERS = "SELECT * FROM CarRental.user where role_id=3";

    @Override
    public boolean create(User entity) {
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getInstance().getConnection();
        }catch (SQLException exception){
            // logger
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
            return false;
        } finally {
            close(connection);
        }
        return true;
    }

    @Override
    public void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException throwables) {
            //logger
        }
    }

    @Override
    public boolean changeBlockedStatus(int userId, boolean blocked) {
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getInstance().getConnection();
        }catch (SQLException exception){
            // logger
        }
        try (PreparedStatement statement = connection.prepareStatement(SQL_CHANGE_BLOCKED_STATUS)){
            statement.setBoolean(1, blocked);
            statement.setInt(2, userId);
            statement.executeUpdate();
        } catch (SQLException throwable) {
            // logger
            return false;
        } finally {
            close(connection);
        }
        return true;
    }

    @Override
    public int getNumberOfUsers() {
        Connection connection = null;
        int numberOfRows = 0;
        try {
            connection = ConnectionPoolHolder.getInstance().getConnection();
        } catch (SQLException throwables) {
            // logger
        }
        try(PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USERS_COUNT)){
            ResultSet rs = statement.executeQuery();

            if( rs.next()) {
                numberOfRows = rs.getInt(1);
            }

        } catch (SQLException ex){
            // logger
        } finally {
            close(connection);
        }
        return numberOfRows;
    }

    @Override
    public User findUserByEmail(String email) {
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getInstance().getConnection();
        }catch (SQLException exception){
            // logger
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
        return user;
    }

    @Override
    public boolean changeUserNameById(int id, String name) {
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getInstance().getConnection();
        }catch (SQLException exception){
            // logger
        }
        try (PreparedStatement statement = connection.prepareStatement(SQL_CHANGE_USER_NAME_BY_ID)){
            statement.setString(1, name);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException throwable) {
            // logger
            return false;
        } finally {
            close(connection);
        }
        return true;
    }

    @Override
    public List<User> findUsers(int start, int end) {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getInstance().getConnection();
        } catch (SQLException throwables) {
            // logger
        }
        try (PreparedStatement statement = connection.prepareStatement(SQL_SELECT_USERS_BY_LIMIT)) {
            statement.setInt(1, start);
            statement.setInt(2, end);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                users.add(UserMapper.map(rs));
            }

        } catch (SQLException ex) {
            // logger
        }finally {
            close(connection);
        }
        return users;
    }

    @Override
    public List<User> findAllManagers() {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getInstance().getConnection();
        } catch (SQLException throwables) {
            // logger
        }
        try (PreparedStatement statement = connection.prepareStatement(SQL_GET_ALL_MANAGERS)) {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                users.add(UserMapper.map(rs));
            }

        } catch (SQLException ex) {
            // logger
        }finally {
            close(connection);
        }
        return users;
    }
}
