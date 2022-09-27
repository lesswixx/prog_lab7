package dbutility;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class DBInit {
    private final Connection dbConnection;

    public DBInit(Connection aConnection){
        dbConnection = aConnection;
    }

    public void init() throws SQLException {
        Statement st = dbConnection.createStatement();
        ResultSet rs = null;
//        st.executeUpdate("DROP TABLE IF EXISTS s334341Organizations");
//        st.executeUpdate("DROP TABLE IF EXISTS s334341users");
//        st.executeUpdate("DROP sequence IF EXISTS sequence");

        st.executeUpdate("CREATE TABLE IF NOT EXISTS s334341Organizations (" +
                "id int PRIMARY KEY," +
                "name varchar(255) NOT NULL CHECK(name<>'')," +
                "CoordinateX DOUBLE PRECISION  NOT NULL," +
                "CoordinateY float NOT NULL," +
                "creationDate date DEFAULT (current_date)," +
                "annualTurnover DOUBLE PRECISION NOT NULL CHECK(annualTurnover > 0)," +
                "fullName varchar(255) NOT NULL CHECK(fullName<>'')," +
                "employeesCount int NOT NULL CHECK(employeesCount>0), " +
                "type varchar(20) " +
                "CHECK(type='COMMERCIAL' OR " +
                "type='PUBLIC' OR " +
                "type='GOVERNMENT' OR " +
                "type='TRUST' OR " +
                "type='PRIVATE_LIMITED_COMPANY')," +
                "street varchar(20) NOT NULL ," +
                "zipCode varchar(20) NOT NULL ," +
                "TownX bigint NOT NULL ," +
                "TownY DOUBLE PRECISION  NOT NULL ," +
                "TownZ DOUBLE PRECISION  NOT NULL," +
                "username varchar(255) NOT NULL)");

        st.executeUpdate("CREATE TABLE IF NOT EXISTS s334341users ("+
                "username varchar(255) PRIMARY KEY,"+
                "hashPassword BYTEA DEFAULT (null)"+
                ")");

        st.executeUpdate("CREATE SEQUENCE IF NOT EXISTS sequence START 1");
    }

}
