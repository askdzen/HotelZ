package com.epam.ad.pool;

import com.epam.ad.dao.h2.DaoFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


/**
 * Created by Askar on 04.08.2014.
 */
public class ConnectionPool {
    public static final String PROPERTIES_FILE = "properties.database";
    public static final int DEFAULT_POOL_SIZE = 10;
    /**
     * single instance
     */
    private static ConnectionPool instance;
    /**
     * free connections queue
     */
    private BlockingQueue<Connection> connectionQueue;

    private ConnectionPool(String driver, String url, String user, String password, int poolSize) {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        connectionQueue = new ArrayBlockingQueue<Connection>(poolSize);
        for (int i = 0; i < poolSize; i++) {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            connectionQueue.offer(connection);
        }
    }

    public static void init() {
        if (instance == null) {
            ResourceBundle rb = ResourceBundle.getBundle(PROPERTIES_FILE);
            String driver = rb.getString("db.driver");
            String url = rb.getString("db.url");
            String user = rb.getString("db.user");
            String password = rb.getString("db.password");
            String poolSizeStr = rb.getString("db.poolsize");
            int poolSize = (poolSizeStr != null) ?
                    Integer.parseInt(poolSizeStr) : DEFAULT_POOL_SIZE;
            //"Trying to create pool of connections..."/
            instance = new ConnectionPool(driver, url, user, password, poolSize);
        }
    }

    public static void dispose() {
        if (instance != null) {
            instance.clearConnectionQueue();
            instance = null;
            //"Connection pool succesfully disposed"
        }
    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    public Connection takeConnection() {
        Connection connection = null;
        try {

            connection = connectionQueue.take();
        } catch (InterruptedException e) {
//"Free connection waiting interrupted.  Returned 'null' connection" , e
            throw new RuntimeException(e);

        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        try {
            if (!connection.isClosed()) {
                if (!connectionQueue.offer(connection)) {
                    //"Connection not added. Possible `leakage` of
                    // connections"
//                    connection.close();
//                    connectionQueue.put(connection);

                    System.out.println("нет больше конекшенов" + connectionQueue.size());
                }
                System.out.println("пришел не закрытый конекшн" + connectionQueue.size());
            } else if (connectionQueue.size()<DEFAULT_POOL_SIZE) {
                //"Trying to release closed connection. Possible
                // `leakage` of connections"
                ResourceBundle rb = ResourceBundle.getBundle(PROPERTIES_FILE);
              //  String driver = rb.getString("db.driver");
                String url = rb.getString("db.url");
                String user = rb.getString("db.user");
                String password = rb.getString("db.password");
                connection = DriverManager.getConnection(url, user, password);
               connectionQueue.put(connection);

                System.out.println("пришел закрытый конекшн" + connectionQueue.size());
            }
        } catch (SQLException e) {
            //"SQLException at conection isClosed () checking.
            // Connection not added", e
        }
         catch (InterruptedException e) {
           e.printStackTrace();
       }

    }

    private void clearConnectionQueue() {
        Connection connection;
        while ((connection = connectionQueue.poll()) != null) {
            /* see java.sql.Connection#close () javadoc */
            try {
                if (!connection.getAutoCommit()) {
                    connection.commit();
                }
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
