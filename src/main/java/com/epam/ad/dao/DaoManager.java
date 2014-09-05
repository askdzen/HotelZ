package com.epam.ad.dao;

import com.epam.ad.dao.h2.BookingTableDao;
import com.epam.ad.dao.h2.CustomerDao;
import com.epam.ad.dao.h2.RoomDao;
import com.epam.ad.dao.h2.UserDao;
import com.epam.ad.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;


public class DaoManager {
    protected Connection connection = null;
    protected BookingTableDao bookingTableDao = null;
    protected CustomerDao customerDao = null;
    protected RoomDao roomDao=null;
    protected UserDao userDao=null;
    //protected ConnectionPool pool;

    public DaoManager(Connection connection) {
      //  ConnectionPool.init();
      //  this.pool = ConnectionPool.getInstance();
        this.connection = connection;
    }

    public CustomerDao getCustomerDao() {
        if (this.customerDao == null) {
            this.customerDao = new CustomerDao(this.connection);
        }
        return customerDao;
    }

    public BookingTableDao getBookingTableDao() {
        if (this.bookingTableDao == null) {
            this.bookingTableDao = new BookingTableDao(this.connection);
        }
        return this.bookingTableDao;
    }
    public RoomDao getRoomDao() {
        if (this.roomDao == null) {
            this.roomDao = new RoomDao(this.connection);
        }
        return roomDao;
    }

    public UserDao getUserDao(){
        if (this.userDao==null){
            this.userDao=new UserDao(this.connection);
        }
        return userDao;
    }

    public Object executeAndClose(DaoCommand command) throws DaoException {
        try {
            return command.execute(this);
        } catch (SQLException e) {
            throw  new DaoException("Исключение при выполнении команды DAO Manager",e.getCause());
        } finally {

            try {
                this.connection.close();
            } catch (SQLException e) {
                throw  new DaoException("Исключение при закрытии соединения",e.getCause());
            }

        }
    }

    public interface DaoCommand {
        public Object execute(DaoManager daoManager) throws DaoException, SQLException;
    }


    public Object transaction(DaoCommand command) throws SQLException, DaoException {
        try {
            this.connection.setAutoCommit(false);
            Object returnValue = command.execute(this);
            this.connection.commit();
            return returnValue;
        } catch (Exception e) {
            this.connection.rollback();
            throw e; //or wrap it before rethrowing it
        } finally {
            this.connection.setAutoCommit(true);
        }
    }

    public void transactionAndClose(DaoCommand command) throws DaoException {
        executeAndClose(new DaoCommand() {

            public Object execute(DaoManager daoManager) throws DaoException, SQLException {
                return daoManager.transaction(command);

            }
        });

    }

}
