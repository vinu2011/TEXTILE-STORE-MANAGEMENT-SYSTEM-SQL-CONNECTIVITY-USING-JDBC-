import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String url = "jdbc:mysql://localhost:3306/textiles";
    private static final String username = "root";
    private static final String password = "Vinu@2004";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
}
