package dao;

import java.sql.Connection;

public interface GenericDao<T> {
    boolean create(T entity);
    void close(Connection connection);
}
