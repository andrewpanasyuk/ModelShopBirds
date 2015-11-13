package connectDB;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by panasyuk on 31.10.2015.
 */
public class DBWorker {
    private final String USER = "root";
    private final String PASSWORD = "root";
    private Connection connection;

    public DBWorker(String url) {
        connection = null;
        try {
            connection = DriverManager.getConnection(url, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Connection getConnection() {
             return connection;
    }

}
