package com.epam.ad.dao.PersistenceAction;

import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.DaoManager;
import com.epam.ad.entity.BookingTable;

import java.sql.Date;

public class BookingPersistenceAction extends PersistenceActionBase {

    private BookingTable bookingTable;


    public BookingPersistenceAction(DaoManager daoManager) {
        super(daoManager);
    }
    public BookingPersistenceAction(DaoManager daoManager,BookingTable bookingTable) {
        super(daoManager);
        this.bookingTable=bookingTable;
      }


    @Override
    protected void doCreatePersistenceAction(DaoManager daoManager) throws DaoException {
        BookingTable booking=daoManager.getBookingTableDao().create();
        this.bookingTable.setId(booking.getId());
        daoManager.getBookingTableDao().update(this.bookingTable);
    }

    @Override
    protected void doUpdatePersistenceAction(DaoManager daoManager) throws DaoException {
        BookingTable booking =daoManager.getBookingTableDao().getByPK(this.bookingTable.getId());
       if (!booking.getDateFrom().equals(bookingTable.getDateFrom()))booking.setDateFrom(bookingTable.getDateFrom());
       if (!booking.getDateTo().equals(bookingTable.getDateTo()))booking.setDateTo(bookingTable.getDateTo());
        if (booking.getDayCount()!=bookingTable.getDayCount())booking.setDayCount(bookingTable.getDayCount());
        if (!booking.getRoomNo().equals(bookingTable.getRoomNo()))booking.setRoomNo(bookingTable.getRoomNo());
        if (!booking.getConfirm().equals(bookingTable.getConfirm()))booking.setConfirm(bookingTable.getConfirm());
        if (!booking.getUserId().equals(bookingTable.getUserId()))booking.setUserId(bookingTable.getUserId());

        daoManager.getBookingTableDao().update(booking);
    }
}
