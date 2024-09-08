package searchengine.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/my_base";
    private static final String USER = "root";
    private static final String PASSWORD = "freefire007";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        try (Connection connection = getConnection()) {
            System.out.println("Connected to MySQL successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
