package connectDB;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by panasyuk on 14.11.2015.
 */
public class DataSource {
    private ComboPooledDataSource cpds;


    public DataSource(String driver, String url) {
        cpds = new ComboPooledDataSource();
        try {
            cpds.setDriverClass(driver);
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        cpds.setJdbcUrl(url);
        cpds.setUser("root");
        cpds.setPassword("root");

        cpds.setMinPoolSize(1);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(10);
        cpds.setMaxStatements(180);
    }

    public Connection getConnections(){
        try {
            return this.cpds.getConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void takeConnect(Connection conn){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
