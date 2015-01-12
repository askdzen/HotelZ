package com.epam.ad.action.admin.user;

import com.epam.ad.action.Action;
import com.epam.ad.action.ActionException;
import com.epam.ad.action.ActionResult;
import com.epam.ad.crud.UserJPAService;
import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.DaoManager;
import com.epam.ad.dao.h2.DaoFactory;
import com.epam.ad.dao.h2.UserDao;
import com.epam.ad.entity.User;
import com.epam.ad.entity.UserEntity;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;


public class UserEditAction implements Action {
    UserJPAService userJPAService=new UserJPAService();
    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        ActionResult userdetail = new ActionResult("userdetail", true);
        String userDeleteString = request.getParameter("delete");
//        DaoFactory daoFactory = new DaoFactory();
//        UserDao userDao= daoFactory.createDaoManager().getUserDao();

        if (userDeleteString !=null){
//            try {
//                daoFactory.createDaoManager().transactionAndClose(new DaoManager.DaoCommand() {
//                    @Override
//                    public Object execute(DaoManager daoManager) throws DaoException, SQLException, ActionException {
//                        userDelete(userDeleteString, userDao);
//                        return null;
//                    }
//                });
//            } catch (DaoException e) {
//                throw new ActionException("Исключение при удалении записи таблицы User",e.getCause());
//            }
//            daoFactory.releaseContext();
            userDelete(userDeleteString);
            return userdetail;
        }
//        try {
//            daoFactory.createDaoManager().transactionAndClose(new DaoManager.DaoCommand() {
//                @Override
//                public Object execute(DaoManager daoManager) throws DaoException, SQLException, ActionException {
//                    getParametersAndUpdate(request, userDao);
//                    daoFactory.releaseContext();
//                    return null;
//                }
//            });
//            return userdetail;
//        } catch (DaoException e) {
//            throw new ActionException("Исключение при обновлении таблицы User",e.getCause());
//        }
        getParametersAndUpdate(request);
        return userdetail;
    }

    private void getParametersAndUpdate(HttpServletRequest request) throws ActionException {
//        try {
//            String username = request.getParameter("username");
//            String password =request.getParameter("password");
//            String role = request.getParameter("role");
//            String userId = request.getParameter("userid");
//            userDao.updateRecord(username,password,role,userId);
//        } catch (DaoException e) {
//            throw new ActionException("Исключение при обновлении записи таблицы User",e.getCause());
//        }
            String username = request.getParameter("username");
            String password =request.getParameter("password");
            String role = request.getParameter("role");
            String userId = request.getParameter("userid");
        UserEntity userfromDB=userJPAService.get(Integer.parseInt(userId));
        UserEntity userEntity=new UserEntity();
        userEntity.setId(userfromDB.getId());
        userEntity.setLogin(username);
        userEntity.setPassword(password);
        userEntity.setRole(role);
        userEntity.setIsdeleted(false);
        userJPAService.update(userEntity);
    }

    private void userDelete(String userDeleteString) throws ActionException {
//        try {
//
//            int userRecordDeleteId=Integer.parseInt(userDeleteString);
//            User tableRecord = userDao.getByPK(userRecordDeleteId);
//            tableRecord.setDeleted(true);
//            userDao.update(tableRecord);
//        } catch (DaoException e) {
//            throw new ActionException("Исключение при удалении записи таблицы User",e.getCause());
//        }
        UserEntity userfromDB=userJPAService.get(Integer.parseInt(userDeleteString));
        UserEntity userEntity=new UserEntity();
        userEntity.setId(userfromDB.getId());
        userEntity.setIsdeleted(true);
        userJPAService.update(userEntity);
    }
}
