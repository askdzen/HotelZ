package com.epam.ad.dao;


import java.sql.SQLException;

public class PersistenceActionBase {
    protected DaoManager daoManager=null;

    public PersistenceActionBase(DaoManager daoManager) {
        this.daoManager = daoManager;
    }
    public void doAction() throws SQLException, DaoException {
        this.daoManager.transactionAndClose(new DaoManager.DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) throws DaoException, SQLException {
                doPersistenceAction(daoManager);
                return null;
            }
        });
    }

    protected void   doPersistenceAction(DaoManager daoManager) throws DaoException {
    }

}
