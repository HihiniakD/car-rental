package model.dao;

import java.sql.Connection;

public interface GenericDao<T> {
    boolean create(T entity);
    boolean update(T entity);
    boolean delete(T entity);
    void close(Connection connection);
}
