import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/school_db";
    private static final String USER = "root"; // ✅ Replace with your MySQL username if different
    private static final String PASSWORD = "1234"; // ✅ Replace with your MySQL password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
