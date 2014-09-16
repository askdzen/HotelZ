package com.epam.ad.dao.PersistenceAction;

import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.DaoManager;
import com.epam.ad.entity.Customer;


public class CustomerPersistenceAction extends PersistenceActionBase {
    public CustomerPersistenceAction(DaoManager daoManager) {
        super(daoManager);
    }
    public CustomerPersistenceAction(DaoManager daoManager, Customer customer) {
        super(daoManager);
        this.customer=customer;
    }


   private Customer customer;
    protected void doUpdatePersistenceAction(DaoManager daoManager) throws DaoException {
        Customer customerUpdate=daoManager.getCustomerDao().getByPK(this.customer.getId());
        if (!customerUpdate.getFirstName().equals(this.customer.getFirstName()))customerUpdate.setFirstName(this.customer.getFirstName());
        if (!customerUpdate.getLastName().equals(this.customer.getLastName()))customerUpdate.setLastName(this.customer.getLastName());
        if (!customerUpdate.getCity().equals(this.customer.getCity()))customerUpdate.setCity(this.customer.getCity());
        if (!customerUpdate.getRegion().equals(this.customer.getRegion()))customerUpdate.setRegion(this.customer.getRegion());
        if (!customerUpdate.getCountry().equals(this.customer.getCountry()))customerUpdate.setCountry(this.customer.getCountry());
        if (!customerUpdate.getPassport().equals(this.customer.getPassport()))customerUpdate.setPassport(this.customer.getPassport());
        if (!customerUpdate.getPhone().equals(this.customer.getPhone()))customerUpdate.setPhone(this.customer.getPhone());
        if (!customerUpdate.getEmail().equals(this.customer.getEmail()))customerUpdate.setEmail(this.customer.getEmail());
        if (customerUpdate.getPrepayment()!=(this.customer.getPrepayment()))customerUpdate.setPrepayment(this.customer.getPrepayment());
        if (!customerUpdate.getBookId().equals(this.customer.getBookId()))customerUpdate.setBookId(this.customer.getBookId());
        if (!customerUpdate.getUserId().equals(this.customer.getUserId()))customerUpdate.setUserId(this.customer.getUserId());
        if (customerUpdate.isDeleted()!=(this.customer.isDeleted()))customerUpdate.setIsDeleted(this.customer.isDeleted());
        if (!customerUpdate.getId().equals(this.customer.getId()))customerUpdate.setId(this.customer.getId());
        daoManager.getCustomerDao().update(customerUpdate);
    }

    @Override
    protected void doCreatePersistenceAction(DaoManager daoManager) throws DaoException {
        Customer customer=daoManager.getCustomerDao().create();
        this.customer.setId(customer.getId());
        daoManager.getCustomerDao().update(this.customer);


    }


}
