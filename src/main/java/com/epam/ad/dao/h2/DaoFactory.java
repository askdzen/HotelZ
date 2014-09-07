package com.epam.ad.dao.h2;

import com.epam.ad.action.ActionException;
import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.DaoManager;
import com.epam.ad.dao.GenericDao;
import com.epam.ad.entity.BookingTable;
import com.epam.ad.entity.Customer;
import com.epam.ad.entity.Room;
import com.epam.ad.entity.User;
import com.epam.ad.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class DaoFactory implements com.epam.ad.dao.DaoFactory<Connection> {
//    private Map<Class, DaoCreator> creators;
    //ConnectionPool pool = getPool();

     private Connection connection=getContext();

    @Override
    public Connection getContext() {
         ConnectionPool pool = getPool();

        if (connection==null){
            try {
                throw new ActionException();
            } catch (ActionException e) {
                e.getMessage();
            }
        }

        Connection connection = pool.takeConnection();
        this.connection=connection;
        return connection;

    }

    private ConnectionPool getPool() {
        return ConnectionPool.getInstance();
    }

//    @Override
//    public GenericDao getDao(Class dtoClass) throws DaoException {
//        DaoCreator creator = creators.get(dtoClass);
//        if (creator == null) {
//
//                throw new DaoException("Dao object for " + dtoClass + " not found.");
//
//
//        }
//        return creator.create(connection);
//    }

    @Override
    public void releaseContext()  {
        ConnectionPool pool=getPool();
        pool.releaseConnection(connection);

       // ConnectionPool.dispose();
    }
    public DaoManager createDaoManager(){
       // connection = getContext();
        return new DaoManager(connection);
    }

    public DaoFactory() {
       // this.getContext();

//        creators = new HashMap<Class, DaoCreator>();
//        creators.put(Room.class, new DaoCreator<Connection>() {
//            @Override
//            public RoomDao create(Connection connection) {
//                return new RoomDao(connection);
//            }
//        });
//        creators.put(Customer.class, new DaoCreator<Connection>() {
//            @Override
//            public CustomerDao create(Connection connection) {
//                return new CustomerDao(connection);
//            }
//        });
//        creators.put(BookingTable.class, new DaoCreator<Connection>() {
//            @Override
//            public BookingTableDao create(Connection connection) {
//                return new BookingTableDao(connection);
//            }
//        });
//        creators.put(User.class, new DaoCreator<Connection>() {
//            @Override
//            public UserDao create(Connection connection) {
//                return new UserDao(connection);
//            }
//        });


    }
}
