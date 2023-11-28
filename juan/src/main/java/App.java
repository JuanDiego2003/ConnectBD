import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

public class App {
    public static File file = new File("games.csv");

    public static void main(String[] args) throws SQLException {
        ConnectionDB.getInstance();


    }
}
