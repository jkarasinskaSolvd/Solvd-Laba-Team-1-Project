package solvd.laba.sql;


import solvd.laba.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class SqlAbstractDao {
    //TODO URL!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    private static String url = "";
    private static String user = "";
    private static String password = "";
    public static final ConnectionPool connectionPool = ConnectionPool.getInstance(5, url, user, password);
    Connection getConnection() throws SQLException, InterruptedException {
        return connectionPool.getConnection();
    }
}
