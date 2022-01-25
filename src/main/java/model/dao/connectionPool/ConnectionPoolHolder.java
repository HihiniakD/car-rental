package model.dao.connectionPool;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionPoolHolder {
    private static ConnectionPoolHolder instance;
    private static volatile DataSource dataSource;
    static Logger log = Logger.getLogger(ConnectionPoolHolder.class.getName());

    private ConnectionPoolHolder() { getDataSource(); }

    public static synchronized ConnectionPoolHolder getInstance(){
        if(instance == null){
            synchronized (ConnectionPoolHolder.class){
                if(instance == null){
                    instance = new ConnectionPoolHolder();
                }
            }
        }
        return instance;
    }

    public static DataSource getDataSource(){
        try {
            Class.forName("org.postgresql.Driver").newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            log.log(Level.INFO, "can't access");
        } catch (ClassNotFoundException e) {
            log.log(Level.INFO, "class not found");
        }
        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    BasicDataSource basicDataSource = new BasicDataSource();
                    basicDataSource.setUrl("jdbc:postgresql://localhost:5432/car_rental");
                    basicDataSource.setUsername("postgres");
                    basicDataSource.setPassword("postgres");
                    basicDataSource.setMinIdle(15);
                    basicDataSource.setMaxIdle(50);
                    basicDataSource.setMaxOpenPreparedStatements(100);
                    dataSource = basicDataSource;
                }
            }
        }
        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
