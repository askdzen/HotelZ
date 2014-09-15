package com.epam.ad.dao.PersistenceAction;

import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.DaoManager;
import com.epam.ad.entity.BookingTable;

import java.sql.Date;

public class BookingPersistenceAction extends PersistenceActionBase {
    private int id;
    private String roomNo;
    private String dateFrom;
    private String  dateTo;
    private String dayCount;
    private String userId;
    private String confirm;
    private boolean isDeleted;


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

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public int getId() {
        return id;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public String getDayCount() {
        return dayCount;
    }

    public String getUserId() {
        return userId;
    }

    public String getConfirm() {
        return confirm;
    }

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
        bookingTable.setConfirm(BookingTable.Confirm.UNPROCESSED);
        isDeleted = false;
        bookingTable.setDelete(isDeleted);
        bookingTable.setId(bookingTable.getId());
        daoManager.getBookingTableDao().update(bookingTable);

    }

    @Override
    protected void doUpdatePersistenceAction(DaoManager daoManager) throws DaoException {
        BookingTable booking =daoManager.getBookingTableDao().getByPK(id);
        Date datefromDate=Date.valueOf(dateFrom);
        Date dateToDate = Date.valueOf(dateTo);
        int dayCountInt=Integer.parseInt(dayCount);
        int roomNoInt=Integer.parseInt(roomNo);
        int userIdInt = Integer.parseInt(userId);
        BookingTable.Confirm confirmEnum= BookingTable.Confirm.valueOf(confirm);

        if (getDateFrom()!=null)booking.setDateFrom(datefromDate);
        if (getDateTo()!=null)booking.setDateTo(dateToDate);
        if (getDayCount()!=null)booking.setDayCount(dayCountInt);
        if (getRoomNo()!=null)booking.setRoomNo(roomNoInt);
        if (getUserId()!=null)booking.setUserId(userIdInt);
        if (getConfirm()!=null)booking.setConfirm(confirmEnum);
         booking.setDelete(isDeleted);
        if (getId()!=0)booking.setId(id);
        daoManager.getBookingTableDao().update(booking);
    }
}
