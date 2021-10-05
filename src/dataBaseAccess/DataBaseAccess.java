package dataBaseAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseAccess {


    private Connection connection;
    public DataBaseAccess() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1/taxi_data_base", "mona_razbani", "Pendi_Pengi142131");
    }

    public Connection getConnection() {
        return connection;
    }
}
