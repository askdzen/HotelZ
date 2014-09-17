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
import java.util.*;


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
//       HttpSession session=req.getSession();
//        if ( session.getAttribute("language")==null){
////        System.out.println(session.getAttribute("language"));
//            session.setAttribute("language",req.getParameter("language"));
//            session.setAttribute("bundlelang","i18n.message"+req.getParameter("language"));
//            session.setAttribute("language",req.getParameter("language"));
//            session.setAttribute("bundlelang","i18n.message"+req.getParameter("language"));
//            Map<String,String> selected=new HashMap<>();
//            selected.put("en","selected");
//            selected.put("ru","selected");
//
//
//            for (String s : selected.keySet()) {
//                if (s.equals(req.getParameter("language"))){
//                    req.setAttribute(s,selected.get(s));
//                }
//            }}
//        System.out.println(session.getAttribute("language")+"by login action");
        try {
            daoManager.transactionAndClose(new DaoManager.DaoCommand() {
                @Override
                public Object execute(DaoManager daoManager) throws DaoException, SQLException, ActionException {
                    String username = req.getParameter(USERNAME);
                    String password = req.getParameter(PASSWORD);
                    if (username.isEmpty()||password.isEmpty()){
                        req.setAttribute("hidden","");
                        req.setAttribute("badoremptyusername","emptyusername");
                        req.setAttribute("badoremptycontext","emptycontext");
                        result=login;
                        return result;
                    }
                    UserDao userDao = null;
                    userDao = daoManager.getUserDao();
                    HttpSession session = req.getSession();
                    boolean validation=false;
                    List<User>users=userDao.getAll();
                    for (User user : users) {
                        if (user.getUsername().equals(username)&&user.getPassword().equals(password)){
                            validation=true;
                        }
                    }
                    if (!validation){
                        req.setAttribute("hidden","");
                        req.setAttribute("badoremptyusername","badusername");
                        req.setAttribute("badoremptycontext","badcontext");
                        result=login;
                        return result;
                    }
                    User user = userDao.findByCredentials(username, password);

                if (user == null) {
                    result= login;
                    return result;
                }
                if (user.getRole().equals("ADMIN")) {
                    session.setAttribute("user", user);
                    result= adminForm;
                    return result;
                }
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
