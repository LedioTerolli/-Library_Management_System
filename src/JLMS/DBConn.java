package JLMS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {

    public static final String URL = "jdbc:mysql://localhost:3306/library_management_system";
    public static final String USER = "root";
    public static final String PASS = "Xn0o$Uk&1Fsn&LIvvTO";

    public static Connection getConnection() throws Exception {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASS);
            return conn;

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}