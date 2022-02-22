package dao.connectionPool;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static controller.Constants.*;

/**
 * Singleton which helps to get DB connections using Double_Checked Locking
 * Use getInstance() method to get access
 */
public class ConnectionPoolHolder {
    private static ConnectionPoolHolder instance;
    private static volatile DataSource dataSource;
    private static final Logger logger = Logger.getLogger(ConnectionPoolHolder.class.getName());

    private ConnectionPoolHolder() { getDataSource(); }

    public static ConnectionPoolHolder getInstance(){
        if(instance == null){
            synchronized (ConnectionPoolHolder.class){
                if(instance == null){
                    instance = new ConnectionPoolHolder();
                }
            }
        }
        return instance;
    }

    private static DataSource getDataSource(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            logger.log(Level.INFO, DB_ACCESS_ERROR);
        } catch (ClassNotFoundException e) {
            logger.log(Level.INFO, DB_CLASS_NOT_FOUND_ERROR);
        }
        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    //configuring db connection pool with the following params
                    BasicDataSource basicDataSource = new BasicDataSource();
                    basicDataSource.setUrl("jdbc:mysql://localhost:3306/CarRental");
                    basicDataSource.setUsername("root");
                    basicDataSource.setPassword("carRental55$");
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
