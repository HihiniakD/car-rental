package model.dao.impl;

import model.dao.UserDao;
import model.entity.User;

import java.sql.Connection;
import java.util.List;

public class JDBCUserImpl implements UserDao {
    @Override
    public boolean create(User entity) {
        return false;
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

    }

    @Override
    public List<User> findAllUsers() {
        return null;
    }
}
