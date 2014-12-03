package com.epam.ad.dao.h2;

import com.epam.ad.action.ActionException;
import com.epam.ad.dao.DaoManager;
import com.epam.ad.pool.ConnectionPool;

import java.sql.Connection;


public class DaoFactory implements com.epam.ad.dao.DaoFactory<Connection> {


    private Connection connection = getContext();

    public DaoFactory() {

    }

    @Override
    public Connection getContext() {
        ConnectionPool pool = getPool();

        if (connection == null) {
            try {
                throw new ActionException();
            } catch (ActionException e) {
                e.getMessage();
            }
        }

        Connection connection = pool.takeConnection();
        this.connection = connection;
        return connection;

    }

    private ConnectionPool getPool() {
        return ConnectionPool.getInstance();
    }

    @Override
    public void releaseContext() {
        ConnectionPool pool = getPool();
        pool.releaseConnection(connection);

    }

    public DaoManager createDaoManager() {

        return new DaoManager(connection);
    }
}
