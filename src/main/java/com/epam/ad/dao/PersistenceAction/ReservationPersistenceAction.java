package com.epam.ad.dao.PersistenceAction;

import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.DaoManager;
import com.epam.ad.entity.BookingTable;
import com.epam.ad.entity.Customer;

import java.sql.Date;


public class ReservationPersistenceAction extends PersistenceActionBase {

    private Customer customer;
    private BookingTable bookingTable;
    public ReservationPersistenceAction(DaoManager daoManager) {
        super(daoManager);

    }
    public ReservationPersistenceAction(DaoManager daoManager,BookingTable bookingTable, Customer customer) {
        super(daoManager);
        this.bookingTable=bookingTable;
        this.customer=customer;
    }


    protected void doPersistenceAction(DaoManager daoManager) throws DaoException {
        doCreateBookingPersistenceAction(daoManager);
        doCreateCustomerPersistenceAction(daoManager);
    }
    protected void doCreateBookingPersistenceAction(DaoManager daoManager) throws DaoException {
        BookingTable booking=daoManager.getBookingTableDao().create();
        this.bookingTable.setId(booking.getId());
        daoManager.getBookingTableDao().update(this.bookingTable);


    }
    protected void doCreateCustomerPersistenceAction(DaoManager daoManager) throws DaoException {
        Customer customer=daoManager.getCustomerDao().create();
        this.customer.setId(customer.getId());
        this.customer.setBookId(this.bookingTable.getId());
        daoManager.getCustomerDao().update(this.customer);

    }

}
