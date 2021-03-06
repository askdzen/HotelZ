package com.epam.ad.pool;

import com.epam.ad.action.ActionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class ConnectionPool {
    public static final String PROPERTIES_FILE = "properties.database";
    public static final int DEFAULT_POOL_SIZE = 10;
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionPool.class);
    /**
     * single instance
     */
    private static ConnectionPool instance;
    /**
     * free connections queue
     */
    private BlockingQueue<Connection> connectionQueue;

    private ConnectionPool(String driver, String url, String user, String password, int poolSize) throws ActionException {
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

//               throw new RuntimeException();
                throw new ActionException("Нет подключения к Базе данных", e.getCause());
            }
            connectionQueue.offer(connection);
        }
    }

    public static void init() throws ActionException {
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
        ResourceBundle rb = ResourceBundle.getBundle(PROPERTIES_FILE);
        try {
            if (!connection.isClosed()) {
                if (!connectionQueue.offer(connection)) {
                    //"Connection not added. Possible `leakage` of
                    // connections"


                    //                   System.out.println("нет больше конекшенов" + connectionQueue.size());
                }
                if (connectionQueue.size() < DEFAULT_POOL_SIZE) {
                    //  connection.close();
                    String url = rb.getString("db.url");
                    String user = rb.getString("db.user");
                    String password = rb.getString("db.password");
                    connection = DriverManager.getConnection(url, user, password);
                    connectionQueue.put(connection);
                    LOGGER.info("пришел не закрытый конекшн {}", connectionQueue.size());
                }
            } else if (connectionQueue.size() < DEFAULT_POOL_SIZE) {
                //"Trying to release closed connection. Possible
                // `leakage` of connections"
                //  String driver = rb.getString("db.driver");
                String url = rb.getString("db.url");
                String user = rb.getString("db.user");
                String password = rb.getString("db.password");
                connection = DriverManager.getConnection(url, user, password);
                connectionQueue.put(connection);

                LOGGER.info("пришел закрытый конекшн {}", connectionQueue.size());
            }
        } catch (SQLException e) {
            //"SQLException at conection isClosed () checking.
            // Connection not added", e
        } catch (InterruptedException e) {
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
