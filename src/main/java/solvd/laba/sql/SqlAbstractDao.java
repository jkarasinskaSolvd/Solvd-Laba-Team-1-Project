package solvd.laba.sql;


import solvd.laba.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class SqlAbstractDao {
    private static String url = "jdbc:mysql://localhost:3306/Optimising logistics for order fulfilment";
    private static String user = "root";
    private static String password = "12345";
    public static final ConnectionPool connectionPool = ConnectionPool.getInstance(5, url, user, password);
    Connection getConnection() throws SQLException, InterruptedException {
        return connectionPool.getConnection();
    }
}
