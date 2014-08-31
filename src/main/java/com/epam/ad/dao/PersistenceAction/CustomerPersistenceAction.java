package com.epam.ad.dao.PersistenceAction;

import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.DaoManager;
import com.epam.ad.dao.PersistenceActionBase;
import com.epam.ad.entity.Customer;

/**
 * Created by Askar on 30.08.2014.
 */
public class CustomerPersistenceAction extends PersistenceActionBase {
    public CustomerPersistenceAction(DaoManager daoManager) {
        super(daoManager);
    }

    @Override
    public void doPersistenceAction(DaoManager daoManager) throws DaoException {
        Customer customer=daoManager.getCustomerDao().getByPK(98);
        customer.setFirstName("Dmitriy");
        daoManager.getCustomerDao().update(customer);
    }
}
