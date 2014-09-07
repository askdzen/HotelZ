package com.epam.ad.action;

import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.DaoManager;
import com.epam.ad.dao.h2.DaoFactory;
import com.epam.ad.dao.h2.UserDao;
import com.epam.ad.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;


public class UserEditAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        ActionResult userdetail = new ActionResult("userdetail", true);
        String userDeleteString = request.getParameter("delete");
        DaoFactory daoFactory = new DaoFactory();
        UserDao userDao= daoFactory.createDaoManager().getUserDao();

        if (userDeleteString !=null){
            try {
                daoFactory.createDaoManager().transactionAndClose(new DaoManager.DaoCommand() {
                    @Override
                    public Object execute(DaoManager daoManager) throws DaoException, SQLException, ActionException {
                        userDelete(userDeleteString, userDao);
                        return null;
                    }
                });
            } catch (DaoException e) {
                throw new ActionException("Исключение при удалении записи таблицы User",e.getCause());
            }
            daoFactory.releaseContext();
            return userdetail;
        }
        try {
            daoFactory.createDaoManager().transactionAndClose(new DaoManager.DaoCommand() {
                @Override
                public Object execute(DaoManager daoManager) throws DaoException, SQLException, ActionException {
                    getParametersAndUpdate(request, userDao);
                    daoFactory.releaseContext();
                    return null;
                }
            });
            return userdetail;
        } catch (DaoException e) {
            throw new ActionException("Исключение при обновлении таблицы User",e.getCause());
        }
    }

    private void getParametersAndUpdate(HttpServletRequest request, UserDao userDao) throws ActionException {
        try {
            String username = request.getParameter("username");
            String password =request.getParameter("password");
            String role = request.getParameter("role");
            String userId = request.getParameter("userid");
            userDao.updateRecord(username,password,role,userId);
        } catch (DaoException e) {
            throw new ActionException("Исключение при обновлении записи таблицы User",e.getCause());
        }
    }

    private void userDelete(String userDeleteString, UserDao userDao) throws ActionException {
        try {

            int userRecordDeleteId=Integer.parseInt(userDeleteString);
            User tableRecord = userDao.getByPK(userRecordDeleteId);
            tableRecord.setDeleted(true);
            userDao.update(tableRecord);
        } catch (DaoException e) {
            throw new ActionException("Исключение при удалении записи таблицы User",e.getCause());
        }
    }
}
