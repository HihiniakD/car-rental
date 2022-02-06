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
    private static final String SELECT_USERS_COUNT = "SELECT COUNT(id) FROM CarRental.user";
    private static final String SELECT_USERS_BY_LIMIT = "SELECT * FROM CarRental.user WHERE role_id=1 LIMIT ?,?";

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
    public int getNumberOfUsers() {
        Connection connection = null;
        int numberOfRows = 0;
        try {
            connection = ConnectionPoolHolder.getInstance().getConnection();
        } catch (SQLException throwables) {
//            logger.error("no connection", throwables);
            System.out.println(throwables.getMessage());
        }
        try(PreparedStatement statement = connection.prepareStatement(SELECT_USERS_COUNT)){
            ResultSet rs = statement.executeQuery();

            if( rs.next()) {
                numberOfRows = rs.getInt(1);
            }

        } catch (SQLException ex){
//            logger.info("Exception" + ex.getMessage());
            System.out.println(ex.getMessage());
        } finally {
            close(connection);
        }
        return numberOfRows;
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

    @Override
    public boolean changeUserNameById(int id, String name) {
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getInstance().getConnection();
        }catch (SQLException exception){
            exception.printStackTrace();
        }
        try (PreparedStatement statement = connection.prepareStatement(SQL_CHANGE_USER_NAME_BY_ID)){
            statement.setString(1, name);
            statement.setInt(2, id);
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
    public List<User> findUsers(int start, int end) {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        try {
            connection = ConnectionPoolHolder.getInstance().getConnection();
        } catch (SQLException throwables) {
//            logger.error("no connection", throwables);
        }
        try (PreparedStatement statement = connection.prepareStatement(SELECT_USERS_BY_LIMIT)) {
            statement.setInt(1, start);
            statement.setInt(2, end);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                users.add(UserMapper.map(rs));
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }finally {
            close(connection);
        }
        return users;
    }
}
