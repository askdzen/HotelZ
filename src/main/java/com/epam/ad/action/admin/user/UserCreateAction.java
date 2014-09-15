package com.epam.ad.action.admin.user;

import com.epam.ad.action.Action;
import com.epam.ad.action.ActionException;
import com.epam.ad.action.ActionResult;
import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.DaoManager;
import com.epam.ad.dao.h2.DaoFactory;
import com.epam.ad.dao.h2.UserDao;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class UserCreateAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        ActionResult userdetail=new ActionResult("userdetail",true);
        DaoFactory daoFactory=new DaoFactory();
        try {
            daoFactory.createDaoManager().transactionAndClose(new DaoManager.DaoCommand() {
                @Override
                public Object execute(DaoManager daoManager) throws DaoException, SQLException, ActionException {
                    getParametersAndCreate(request, daoFactory);

                    return null;
                }
            });
        } catch (DaoException e) {
            throw new ActionException("Исключение при создании таблицы User",e.getCause());
        }
        daoFactory.releaseContext();
        return userdetail;
    }

    private void getParametersAndCreate(HttpServletRequest request, DaoFactory daoFactory) throws ActionException {
        try {
            String username = request.getParameter("login");
            String password =request.getParameter("pass");
            String role = request.getParameter("roles");
            UserDao userDao = daoFactory.createDaoManager().getUserDao();
            userDao.createRecord(username,password,role);
        } catch (DaoException e) {
            throw new ActionException("Исключение при создании записи в таблице User",e.getCause());
        }
    }
}
