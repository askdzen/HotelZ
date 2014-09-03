package com.epam.ad.dao;


import java.sql.SQLException;

public abstract class PersistenceActionBase {
    protected DaoManager daoManager=null;

    public PersistenceActionBase(DaoManager daoManager) {
        this.daoManager = daoManager;
    }
    public void doUpdateAction() throws  DaoException {
        try {
            this.daoManager.transactionAndClose(new DaoManager.DaoCommand() {
                @Override
                public Object execute(DaoManager daoManager) throws DaoException, SQLException {
                    doUpdatePersistenceAction(daoManager);
                    return null;
                }
            });
        } catch (SQLException e) {
            throw new DaoException(e.getCause());
        }
    }

    public void doCreateAction() throws DaoException {
        try {
            this.daoManager.transactionAndClose(new DaoManager.DaoCommand() {
                @Override
                public Object execute(DaoManager daoManager) throws DaoException, SQLException {
                    doCreatePersistenceAction(daoManager);
                    return null;
                }
            });
        } catch (SQLException e) {
            throw new DaoException(e.getCause());
        }
    }

//    public void doFindByIdAction() throws DaoException {
//        try {
//            this.daoManager.transactionAndClose(new DaoManager.DaoCommand() {
//                @Override
//                public Object execute(DaoManager daoManager) throws DaoException{
//                    doFindByIdPersistenceAction(daoManager);
//                    return null;
//                }
//            });
//        } catch (SQLException e) {
//            throw new DaoException(e.getCause());
//        }
//    }
//    public void doGetAllAction() throws DaoException {
//        try {
//            this.daoManager.transactionAndClose(new DaoManager.DaoCommand() {
//                @Override
//                public Object execute(DaoManager daoManager) throws DaoException{
//                    doGetAllPersistenceAction(daoManager);
//                    return null;
//                }
//            });
//        } catch (SQLException e) {
//            throw new DaoException(e.getCause());
//        }
//    }

    public void doDeleteAction() throws DaoException {
        try {
            this.daoManager.transactionAndClose(new DaoManager.DaoCommand() {
                @Override
                public Object execute(DaoManager daoManager) throws DaoException {
                    doDeletePersistenceAction(daoManager);
                    return null;
                }
            });
        } catch (SQLException e) {
            throw new DaoException(e.getCause());
        }
    }

    protected void   doDeletePersistenceAction(DaoManager daoManager) throws DaoException {
    }
    protected void   doCreatePersistenceAction(DaoManager daoManager) throws DaoException {
    }
    protected void doUpdatePersistenceAction(DaoManager daoManager) throws DaoException {
    }
//    private void doGetAllPersistenceAction(DaoManager daoManager) {
//    }
//    protected int doFindByIdPersistenceAction(DaoManager daoManager) throws DaoException {
//        return 0;
//    }
}
