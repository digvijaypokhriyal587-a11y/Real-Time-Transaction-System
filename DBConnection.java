package Transaction.system;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {
        try {
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bankdb",
                "root",
                "0011"  
            );
            return con;
        } catch (Exception e) {
            System.out.println("Database Connection Failed");
            return null;
        }
    }
}