import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static ConnectionDB instance;
    private Connection connection;
    private String url = "jdbc:oracle:thin:@192.168.56.105:1521:orcl";
    private String username = "jj";
    private String password = "ora123";

    private ConnectionDB() throws SQLException {
        this.connection = DriverManager.getConnection(url, username, password);
    }


    public Connection getConnection() {
        return connection;
    }

    public static ConnectionDB getInstance() throws SQLException {
        if (instance == null) {
            instance = new ConnectionDB();
        } else if (instance.getConnection().isClosed()) {
            instance = new ConnectionDB();
        }

        return instance;
    }
}
