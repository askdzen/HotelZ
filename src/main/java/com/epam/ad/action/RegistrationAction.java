package com.epam.ad.action;

import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.DaoManager;
import com.epam.ad.dao.Identified;
import com.epam.ad.dao.h2.DaoFactory;
import com.epam.ad.dao.h2.UserDao;
import com.epam.ad.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class RegistrationAction implements Action {

    private ActionResult result;

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {

        ActionResult registration = new ActionResult("registration");
//        ActionResult welcome = new ActionResult("welcome", true);
        String username = request.getParameter("inputUsername");
        String password = request.getParameter("inputPassword");
        String confirmpass = request.getParameter("inputConfirmPassword");

        if (username.equals("") || password.equals("")){
            request.setAttribute("hidden","hidden=\"hidden\"");
            return registration;
        }


        else if (!password.equals(confirmpass)) {
            request.setAttribute("registrationmessage", "badparol");
            request.setAttribute("hidden","");

            return registration;
        }
            DaoFactory daoFactory=new DaoFactory();
        try {
            daoFactory.createDaoManager().transactionAndClose(new DaoManager.DaoCommand() {
            @Override
            public Object execute(DaoManager daoManager) throws DaoException, SQLException, ActionException {
                UserDao userDao = daoFactory.createDaoManager().getUserDao();
                List<User> users = new ArrayList<>(userDao.getAll());
                for (User user : users) {
                    if (user.getUsername().equals(username)) {
                        request.setAttribute("registrationmessage", "badusername");
                        request.setAttribute("hidden","");
                        result=registration;

                        return result;
                    }
                }
                User newUser = userDao.createUser(username, password, userDao);
                HttpSession session = request.getSession();
                session.setAttribute("user", newUser);
                request.setAttribute("registrationmessage", "registration.congratulations");
                request.setAttribute("hidden","");
                result = registration;
                return result;
            }
        });

        } catch (DaoException e) {
            throw new ActionException("Исключение при получении данных из таблицы User",e.getCause());
        }

        daoFactory.releaseContext();

         return result;
      }


}
