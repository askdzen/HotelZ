package com.epam.ad.action.admin.user;

import com.epam.ad.action.Action;
import com.epam.ad.action.ActionException;
import com.epam.ad.action.ActionResult;
import com.epam.ad.crud.JPADao;
import com.epam.ad.entity.UserEntity;

import javax.servlet.http.HttpServletRequest;

public class UserCreateAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        ActionResult userdetail=new ActionResult("userdetail",true);
//        DaoFactory daoFactory=new DaoFactory();
//        try {
//            daoFactory.createDaoManager().transactionAndClose(new DaoManager.DaoCommand() {
//                @Override
//                public Object execute(DaoManager daoManager) throws DaoException, SQLException, ActionException {
//                    getParametersAndCreate(request, daoFactory);
//
//                    return null;
//                }
//            });
//        } catch (DaoException e) {
//            throw new ActionException("Исключение при создании таблицы User",e.getCause());
//        }
//        daoFactory.releaseContext();

        getParametersAndCreate(request);
        return userdetail;
    }

    private void getParametersAndCreate(HttpServletRequest request) throws ActionException {
//        try {
            String username = request.getParameter("login");
            String password =request.getParameter("pass");
            String role = request.getParameter("roles");
        JPADao service=new JPADao(UserEntity.class);
        UserEntity user=new UserEntity();
        user.setLogin(username);
        user.setPassword(password);
        user.setRole(role);
        user.setIsdeleted(false);
        service.add(user);


//            UserDao userDao = daoFactory.createDaoManager().getUserDao();
//            userDao.createRecord(username,password,role);
//        } catch (DaoException e) {
//            throw new ActionException("Исключение при создании записи в таблице User",e.getCause());
//        }
    }
}
