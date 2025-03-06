package solvd.laba.sql;


import solvd.laba.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class SqlAbstractDao {
    private static String url = "jdbc:mysql://localhost:3306/db";
    private static String user = "root";
    private static String password = "password";
    public static final ConnectionPool connectionPool = ConnectionPool.getInstance(5, url, user, password);
    Connection getConnection() throws SQLException, InterruptedException {
        return connectionPool.getConnection();
    }
}
