package JLMS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn{
   public static Connection getConnection() throws Exception{
       try {
           String url = "jdbc:mysql://localhost:3306/library_management_system";
           String username = "root";
           String password = "Xn0o$Uk&1Fsn&LIvvTO";
           Connection conn = DriverManager.getConnection(url,username,password);
           System.out.println("Connected");
           return conn;

       } catch (Exception e) {
           System.out.println(e);
       }
       return null;
   }
}