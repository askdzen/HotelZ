package com.epam.ad.dao;

import com.epam.ad.dao.h2.BookingTableDao;
import com.epam.ad.dao.h2.CustomerDao;

import java.sql.Connection;
import java.sql.SQLException;


public class DaoManager {
    protected Connection connection = null;
    protected BookingTableDao bookingTableDao = null;
    protected CustomerDao customerDao = null;

    public DaoManager(Connection connection) {
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

    public Object executeAndClose(DaoCommand command) throws DaoException, SQLException {
        try {
            return command.execute(this);
        } finally {

            this.connection.close();

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

    public void transactionAndClose(DaoCommand command) throws SQLException, DaoException {
        executeAndClose(new DaoCommand() {

            public Object execute(DaoManager daoManager) throws DaoException, SQLException {
                return daoManager.transaction(command);

            }
        });


    }

}
