package tests.db;

import java.sql.*;

public class UsersTest {
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    private static final String url = "jdbc:postgresql://82.142.167.37:4832/pflb_trainingcenter";
    private static final String user = "pflb-at-read";
    private static final String password = "PflbQaTraining2354";
    private static int count ;


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
