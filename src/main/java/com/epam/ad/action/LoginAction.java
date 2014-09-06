package com.epam.ad.action;

import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.DaoManager;
import com.epam.ad.dao.h2.DaoFactory;
import com.epam.ad.dao.h2.UserDao;
import com.epam.ad.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;


public class LoginAction implements Action {
    private final static String USERNAME = "username";
    private final static String PASSWORD = "password";
   // private static final String ROLES = "roles";
    private ActionResult welcome = new ActionResult("welcome", true);
    private ActionResult login = new ActionResult("login");
    private ActionResult adminForm = new ActionResult("adminform", true);
    private ActionResult result;


    @Override
    public ActionResult execute(HttpServletRequest req) throws ActionException {
        DaoFactory daoFactory=new DaoFactory();
        DaoManager daoManager=daoFactory.createDaoManager();

        try {
            daoManager.transactionAndClose(new DaoManager.DaoCommand() {
                @Override
                public Object execute(DaoManager daoManager) throws DaoException, SQLException, ActionException {
                    String username = req.getParameter(USERNAME);
                    String password = req.getParameter(PASSWORD);

                    UserDao userDao = null;
                    userDao = daoManager.getUserDao();

                    User user = userDao.findByCredentials(username, password);
                if (user == null) {
                    result= login;
                    return result;
                }
                if (user.getRole().equals("ADMIN")) {
                    result= adminForm;
                    return result;
                }
                    HttpSession session = req.getSession();
                    session.setAttribute("user", user);
                    result= welcome;
                    return result;
                }
            });
        } catch (DaoException e) {
            throw  new ActionException("Исключении при выполнении транзакции при авторизации",e.getCause());
    }
        daoFactory.releaseContext();
        return result;

    }
}
