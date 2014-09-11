package com.epam.ad.action;

import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.DaoManager;
import com.epam.ad.dao.h2.DaoFactory;
import com.epam.ad.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AccountEditAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        ActionResult account=new ActionResult("account",true);
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        int id=Integer.parseInt(request.getParameter("updateid"));
        DaoFactory daoFactory=new DaoFactory();

        try {
            daoFactory.createDaoManager().transactionAndClose(new DaoManager.DaoCommand() {
                @Override
                public Object execute(DaoManager daoManager) throws DaoException, SQLException, ActionException {
                   User user=daoFactory.createDaoManager().getUserDao().getByPK(id);
                    user.setUsername(username);
                    user.setPassword(password);
                    daoFactory.createDaoManager().getUserDao().update(user);
                    List<User>userList=new ArrayList<User>();
                    userList.add(user);
                    HttpSession session=request.getSession();
                    session.removeAttribute("user");
                    session.setAttribute("user",user);
                    return null;
                }
            });
        } catch (DaoException e) {
            throw new ActionException("Исключение при обновлении записи таблицы User",e.getCause());
        }
        daoFactory.releaseContext();

        return account;
    }
}
