package solvd.laba;

import jakarta.inject.Singleton;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class ConnectionPool {
    private static ConnectionPool connectionPool;
    private int poolSize;
    private int usedConnections;
    private List<Connection> availableConnectionList;
    private String url;
    private String user;
    private String password;


    public ConnectionPool(int poolSize, String url, String user, String password) {
        this.poolSize = poolSize;
        this.url = url;
        this.user = user;
        this.password = password;
        availableConnectionList = new ArrayList<>();
    }

    public static ConnectionPool getInstance(int poolSize, String url, String user, String password) {
        if(connectionPool == null) {
            connectionPool = new ConnectionPool(poolSize, url, user, password);
        }
        return connectionPool;
    }

    public Connection getConnection() throws InterruptedException, SQLException {
        while (true) {
            synchronized (this) {
                if (usedConnections < poolSize) {
                    usedConnections++;
                    if (availableConnectionList.isEmpty()) {
                        return new ConnectionWrapper(DriverManager.getConnection(url, user, password), this);
                    } else {
                        return new ConnectionWrapper(availableConnectionList.remove(0), this);
                    }
                } else {
                    this.wait();
                }
            }
        }
    }

    public void releaseConnection(Connection connection) {
        synchronized (this) {
            availableConnectionList.add(connection);
            usedConnections--;
            this.notify();
        }
    }

    public void close() {
        availableConnectionList
                .forEach(connection -> {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

}
