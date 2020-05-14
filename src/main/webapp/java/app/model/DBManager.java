package app.model;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
    private static final String DATABASE_URL = "jdbc:mysql://localhost/bhusers?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "root";

    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            connection = DriverManager.getConnection(DATABASE_URL, USER, PASS);
        } catch (SQLException | ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() {
        return connection;
    }
}
