package com.epam.ad.action;

import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.h2.DaoFactory;
import com.epam.ad.dao.h2.UserDao;
import com.epam.ad.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;


public class LoginAction implements Action {
    private final static String USERNAME = "username";
    private final static String PASSWORD = "password";
   // private static final String ROLES = "roles";
    private ActionResult welcome = new ActionResult("welcome", true);
    private ActionResult login = new ActionResult("login");
    private ActionResult adminForm = new ActionResult("adminform", true);


    @Override
    public ActionResult execute(HttpServletRequest req) throws ActionException {
       DaoFactory daoFactory=DaoFactory.getInstance();
        String username = req.getParameter(USERNAME);
        String password = req.getParameter(PASSWORD);
        UserDao userDao = null;
        try {
            userDao = (UserDao) daoFactory.getDao(User.class);
        } catch (DaoException e) {
            throw new ActionException("Исключение при поиске таблицы User",e.getCause());
        }
        User user = userDao.findByCredentials(username, password);
        if (user == null) {
            return login;
        }
        if (user.getRole().equals("ADMIN")) {
            return adminForm;
        }
        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        return welcome;
    }
}
