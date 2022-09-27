package dbutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    String login = "s334341";
    String password = "gOJCkVOUFJL3pjdC";
    String host = "jdbc:postgresql://pg:5432/studs";
//    String login = "postgres";
//    String password = "jma926";
//    String host = "jdbc:postgresql://localhost:5432/studs";

    public Connection connect() {
        Connection conn = null;

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        try {
            conn = DriverManager.getConnection(host, login, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

        return conn;
    }
}
