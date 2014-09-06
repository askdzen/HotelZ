package com.epam.ad.dao.PersistenceAction;

import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.DaoManager;
import com.epam.ad.dao.PersistenceActionBase;
import com.epam.ad.entity.BookingTable;
import com.epam.ad.entity.Customer;

import java.sql.Date;


public class ConfirmTransactionPersistenceAction extends PersistenceActionBase {
    public void setBookId(int bookId) {
        this.bookId = bookId;
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

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public void setInputFirstName(String inputFirstName) {
        this.inputFirstName = inputFirstName;
    }

    public void setInputLastName(String inputLastName) {
        this.inputLastName = inputLastName;
    }

    public void setInputCity(String inputCity) {
        this.inputCity = inputCity;
    }

    public void setInputRegion(String inputRegion) {
        this.inputRegion = inputRegion;
    }

    public void setInputCountry(String inputCountry) {
        this.inputCountry = inputCountry;
    }

    public void setInputPassport(String inputPassport) {
        this.inputPassport = inputPassport;
    }

    public void setInputPhone(String inputPhone) {
        this.inputPhone = inputPhone;
    }

    public void setInputEmail(String inputEmail) {
        this.inputEmail = inputEmail;
    }

    public void setPrepayment(int prepayment) {
        this.prepayment = prepayment;
    }

    private int bookId;
    private String roomNo;
    private String dateFrom;
    private String  dateTo;
    private String userId;
    private String confirm;
    private String inputFirstName;
    private String inputLastName;
    private String inputCity;
    private String inputRegion;
    private String inputCountry;
    private String inputPassport;
    private String inputPhone;
    private String inputEmail;
    private int prepayment;


    public ConfirmTransactionPersistenceAction(DaoManager daoManager) {
        super(daoManager);
    }


    protected void doPersistenceAction(DaoManager daoManager) throws DaoException {
        doCreateBookingPersistenceAction(daoManager);
        doCreateCustomerPersistenceAction(daoManager);
    }
    protected void doCreateBookingPersistenceAction(DaoManager daoManager) throws DaoException {
        BookingTable bookingTable=daoManager.getBookingTableDao().create();

        Date dateFromSql = Date.valueOf(dateFrom);
        Date dateToSql = Date.valueOf(dateTo);
        bookingTable.setDateFrom(dateFromSql);
        bookingTable.setDateTo(dateToSql);
        bookingTable.setDayCount((int) (dateToSql.getTime() - dateFromSql.getTime()) / (24 * 60 * 60 * 1000));
        bookingTable.setRoomNo(Integer.valueOf(roomNo));
        bookingTable.setUserId(Integer.valueOf(userId));
        bookingTable.setConfirm(BookingTable.Confirm.CONFIRM.valueOf(confirm));
        bookingTable.setId(bookingTable.getId());
        this.bookId=bookingTable.getId();
        daoManager.getBookingTableDao().update(bookingTable);

    }
    protected void doCreateCustomerPersistenceAction(DaoManager daoManager) throws DaoException {

        Customer customer = daoManager.getCustomerDao().create();
        customer.setFirstName(inputFirstName);
        customer.setLastName(inputLastName);
        customer.setCity(inputCity);
        customer.setRegion(inputRegion);
        customer.setCountry(inputCountry);
        customer.setPassport(inputPassport);
        customer.setPhone(inputPhone);
        customer.setEmail(inputEmail);
        customer.setPrepayment(prepayment);
        customer.setUserId(Integer.parseInt(userId));
        customer.setBookId(bookId);
        customer.setId(customer.getId());
        daoManager.getCustomerDao().update(customer);
    }

}
