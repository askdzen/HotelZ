package com.epam.ad.action;

import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.h2.DaoFactory;
import com.epam.ad.dao.h2.UserDao;
import com.epam.ad.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


public class UserAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        ActionResult userdetail = new ActionResult("userdetail");
        ActionResult userupdate = new ActionResult("userupdate");
        ActionResult usercreate = new ActionResult("usercreate", true);
        DaoFactory daoFactory=new DaoFactory();
        UserDao userDao=daoFactory.createDaoManager().getUserDao();

        String userUpdateDataString = request.getParameter("update");
        String userCreate = request.getParameter("create");
        if (userCreate !=null){
            daoFactory.releaseContext();
            return usercreate;
        }
        if (userUpdateDataString!=null){
            setAttributesForUpdate(request, userDao, userUpdateDataString);
            daoFactory.releaseContext();
            return userupdate;
        }else {
            try {
                request.setAttribute("column",0);
                request.setAttribute("value",0);
                Pagination<User, UserDao> pagination = new Pagination<>();
                pagination.executePaginationAction(request, userDao, "userdetail");
                Map<String,String> selectedColumn=new HashMap<>();
                selectedColumn.put("ID","selected");
                selectedColumn.put("LOGIN","selected");
                selectedColumn.put("PASSWORD", "selected");
                selectedColumn.put("ROLE", "selected");

                for (String s : selectedColumn.keySet()) {
                    if (s.equals(request.getParameter("column"))){
                        request.setAttribute(s,selectedColumn.get(s));
                    }
                }
                daoFactory.releaseContext();
                return userdetail;

            } catch (DaoException e) {
                throw new ActionException("Исключение при выводе таблицы BookingTable с заданными параметрами вывода количества строк ", e.getCause());
            }
        }
    }

    private void setAttributesForUpdate(HttpServletRequest request, UserDao userDao, String userUpdateDataString) throws ActionException {
        int bookingtableUpdateDataId = Integer.parseInt(userUpdateDataString);
        User tableRecord = null;
        try {
            tableRecord = userDao.getByPK(bookingtableUpdateDataId);
        } catch (DaoException e) {
            throw new ActionException("Исключение при поиске записи в таблице User", e.getCause());
        }
        HttpSession session = request.getSession();
        session.setAttribute("username",tableRecord.getUsername());
        session.setAttribute("password",tableRecord.getPassword());
        session.setAttribute("role",tableRecord.getRole());
        session.setAttribute("userid",tableRecord.getId());
    }
}
