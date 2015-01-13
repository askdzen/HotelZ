package com.epam.ad.action;

import com.epam.ad.crud.UserJPADao;
import com.epam.ad.entity.UserEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


public class LoginActionJPA implements Action {


    @Override
    public ActionResult execute(HttpServletRequest req) throws ActionException {

        String USERNAME = "username";
        String PASSWORD = "password";
        ActionResult welcome = new ActionResult("welcome", true);
        ActionResult login = new ActionResult("login");
        ActionResult adminForm = new ActionResult("adminform", true);


        UserJPADao userJPADao = new UserJPADao();
        String username = req.getParameter(USERNAME);
        String password = req.getParameter(PASSWORD);
        if (username.isEmpty() || password.isEmpty()) {
            req.setAttribute("hidden", "");
            req.setAttribute("badoremptyusername", "emptyusername");
            req.setAttribute("badoremptycontext", "emptycontext");

            return login;
        }

        HttpSession session = req.getSession();
        boolean validation = false;
        List<UserEntity> users = userJPADao.getAll();
        for (UserEntity user : users) {
            if (user.getLogin().equals(username) && user.getPassword().equals(password)) {
                validation = true;
            }
        }
        if (!validation) {
            req.setAttribute("hidden", "");
            req.setAttribute("badoremptyusername", "badusername");
            req.setAttribute("badoremptycontext", "badcontext");

            return login;
        }
        UserEntity userEntity = userJPADao.findByCredentials(username, password);

        if (userEntity == null) {

            return login;
        }
        if (userEntity.getRole().equals("ADMIN")) {
            session.setAttribute("user", userEntity);
            return adminForm;
        }
        session.setAttribute("user", userEntity);

        return welcome;

    }
}
