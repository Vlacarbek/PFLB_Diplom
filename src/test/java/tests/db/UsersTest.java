package tests.db;

import utils.PropertyReader;
import java.sql.*;

public class UsersTest {
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    private static int count ;
    public static String user = System.getProperty("DB_LOGIN", PropertyReader.getProperty("DB_LOGIN"));
    public static String password = System.getProperty("DB_PASSWORD", PropertyReader.getProperty("DB_PASSWORD"));
    public static String url = System.getProperty("DB_URL", PropertyReader.getProperty("DB_URL"));

    public static int checkUserCount(String query) throws SQLException {
        count = 0;
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()){
                count++;
            }
        }
        catch (SQLException sqlEx) {
        sqlEx.printStackTrace();
        }
        finally {
            con.close();
            stmt.close();
            rs.close();
        }
        return count;
    }
}
