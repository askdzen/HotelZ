package com.epam.ad.dao;


import java.sql.SQLException;

public abstract class PersistenceActionBase {
    protected DaoManager daoManager=null;

    public PersistenceActionBase(DaoManager daoManager) {
        this.daoManager = daoManager;
    }
    public void doUpdateAction() throws  DaoException {
        this.daoManager.transactionAndClose(new DaoManager.DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) throws DaoException, SQLException {
                doUpdatePersistenceAction(daoManager);
                return null;
            }
        });
    }

    public void doCreateAction() throws DaoException {
        this.daoManager.transactionAndClose(new DaoManager.DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) throws DaoException, SQLException {
                doCreatePersistenceAction(daoManager);
                return null;
            }
        });
    }



    public void doDeleteAction() throws DaoException {
        this.daoManager.transactionAndClose(new DaoManager.DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) throws DaoException {
                doDeletePersistenceAction(daoManager);
                return null;
            }
        });
    }
    public void doAction() throws DaoException {
        this.daoManager.transactionAndClose(new DaoManager.DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) throws DaoException {
                doPersistenceAction(daoManager);
                return null;
            }
        });
    }

    protected void   doPersistenceAction(DaoManager daoManager) throws DaoException {
    }
    protected void   doDeletePersistenceAction(DaoManager daoManager) throws DaoException {
    }
    protected void   doCreatePersistenceAction(DaoManager daoManager) throws DaoException {
    }
    protected void doUpdatePersistenceAction(DaoManager daoManager) throws DaoException {
    }


}
