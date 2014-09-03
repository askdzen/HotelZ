package com.epam.ad.dao.PersistenceAction;

import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.DaoManager;
import com.epam.ad.dao.PersistenceActionBase;
import com.epam.ad.dao.h2.CustomerDao;
import com.epam.ad.entity.Customer;

import java.util.HashMap;
import java.util.Map;


public class CustomerPersistenceAction extends PersistenceActionBase {
    public CustomerPersistenceAction(DaoManager daoManager) {
        super(daoManager);
    }

    public String getInputFirstName() {
        return inputFirstName;
    }

    public void setInputFirstName(String inputFirstName) {
        this.inputFirstName = inputFirstName;
    }

    public String getInputLastName() {
        return inputLastName;
    }

    public void setInputLastName(String inputLastName) {
        this.inputLastName = inputLastName;
    }

    public String getInputCity() {
        return inputCity;
    }

    public void setInputCity(String inputCity) {
        this.inputCity = inputCity;
    }

    public String getInputRegion() {
        return inputRegion;
    }

    public void setInputRegion(String inputRegion) {
        this.inputRegion = inputRegion;
    }

    public String getInputCountry() {
        return inputCountry;
    }

    public void setInputCountry(String inputCountry) {
        this.inputCountry = inputCountry;
    }

    public String getInputPassport() {
        return inputPassport;
    }

    public void setInputPassport(String inputPassport) {
        this.inputPassport = inputPassport;
    }

    public String getInputPhone() {
        return inputPhone;
    }

    public void setInputPhone(String inputPhone) {
        this.inputPhone = inputPhone;
    }

    public String getInputEmail() {
        return inputEmail;
    }

    public void setInputEmail(String inputEmail) {
        this.inputEmail = inputEmail;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Integer getPrepayment() {
        return prepayment;
    }

    public void setPrepayment(int prepayment) {
        this.prepayment = prepayment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   private String inputFirstName;
   private String inputLastName;
   private String inputCity;
   private String inputRegion;
   private String inputCountry;
   private String inputPassport;
   private String inputPhone;
   private String inputEmail;
   private int bookId;
   private int userId;
   private int prepayment;
   private int id;

    protected void doUpdatePersistenceAction(DaoManager daoManager) throws DaoException {
        Customer customer=daoManager.getCustomerDao().getByPK(id);
        if (getInputFirstName()!=null)customer.setFirstName(inputFirstName);
        if (getInputLastName()!=null)customer.setLastName(inputLastName);
        if (getInputCity()!=null)customer.setCity(inputCity);
        if (getInputRegion()!=null)customer.setRegion(inputRegion);
        if (getInputCountry()!=null)customer.setCountry(inputCountry);
        if (getInputPassport()!=null)customer.setPassport(inputPassport);
        if (getInputPhone()!=null)customer.setPhone(inputPhone);
        if (getInputEmail()!=null)customer.setEmail(inputEmail);
        if (getPrepayment()!=null)customer.setPrepayment(prepayment);
        if (getBookId()!=null)customer.setBookId(bookId);
        if (getUserId()!=null)customer.setUserId(userId);
        if (getId()!=null)customer.setId(id);
        daoManager.getCustomerDao().update(customer);
    }

    @Override
    protected void doCreatePersistenceAction(DaoManager daoManager) throws DaoException {

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
        customer.setUserId(userId);
        customer.setBookId(bookId);
        customer.setId(customer.getId());
        daoManager.getCustomerDao().update(customer);
    }


}
