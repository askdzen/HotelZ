package com.epam.ad.dao.PersistenceAction;

import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.DaoManager;
import com.epam.ad.dao.PersistenceActionBase;
import com.epam.ad.entity.BookingTable;

import java.sql.Date;

public class BookingPersistenceAction extends PersistenceActionBase {
    public BookingPersistenceAction(DaoManager daoManager) {
        super(daoManager);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public void setDayCount(String dayCount) {
        this.dayCount = dayCount;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    private int id;
    private String roomNo;
    private String dateFrom;
    private String  dateTo;
    private String dayCount;
    private String userId;
    private String confirm;

    @Override
    protected void doCreatePersistenceAction(DaoManager daoManager) throws DaoException {
        BookingTable bookingTable=daoManager.getBookingTableDao().create();

        Date dateFromSql = Date.valueOf(dateFrom);
        Date dateToSql = Date.valueOf(dateTo);
        bookingTable.setDateFrom(dateFromSql);
        bookingTable.setDateTo(dateToSql);
        bookingTable.setDayCount((int) (dateToSql.getTime() - dateFromSql.getTime()) / (24 * 60 * 60 * 1000));
        bookingTable.setRoomNo(Integer.valueOf(roomNo));
        bookingTable.setUserId(Integer.valueOf(userId));
        bookingTable.setConfirm(BookingTable.Confirm.CONFIRM.valueOf(confirm));
        //  bookingTable.setDelete(false);
        bookingTable.setId(bookingTable.getId());
        daoManager.getBookingTableDao().update(bookingTable);

    }

    @Override
    protected void doUpdatePersistenceAction(DaoManager daoManager) throws DaoException {

    }
}
