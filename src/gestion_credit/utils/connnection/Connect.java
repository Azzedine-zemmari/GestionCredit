package gestion_credit.utils.connnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    private static Connect instance = null;
    private Connection connection = null;

    private Connect() {};

    private void init() throws SQLException {
        final String URL = "jdbc:postgresql://localhost:5432/Credit";
        final String USER = "postgres";
        final String PASSWORD = "Azzedine2004";

        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("Connection on ");

    }

    public Connection getConnection() {
        return this.connection;
    }

    public static Connection getInstance() throws SQLException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new Connect();
            instance.init();
        }
        return instance.getConnection();
    }

}
