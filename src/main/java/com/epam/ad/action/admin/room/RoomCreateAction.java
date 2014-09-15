package com.epam.ad.action.admin.room;

import com.epam.ad.action.Action;
import com.epam.ad.action.ActionException;
import com.epam.ad.action.ActionResult;
import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.DaoManager;
import com.epam.ad.dao.h2.DaoFactory;
import com.epam.ad.dao.h2.RoomDao;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class RoomCreateAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        ActionResult roomdetail=new ActionResult("roomdetail",true);
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
            throw new ActionException("Исключение при создании таблицы Room",e.getCause());
        }
        daoFactory.releaseContext();
        return roomdetail;
    }

    private void getParametersAndCreate(HttpServletRequest request, DaoFactory daoFactory) throws ActionException {


        try {
            String roomNo = request.getParameter("roomnum");
            String roomtype = request.getParameter("type");
            String bedtype=request.getParameter("bed");
            String tarif=request.getParameter("rate");
            System.out.println(roomNo + " "+roomtype+" "+bedtype+" "+tarif);
            RoomDao  roomDao = daoFactory.createDaoManager().getRoomDao();
            roomDao.createRecord(roomNo,roomtype,bedtype,tarif);
        } catch (DaoException e) {
          throw new ActionException("Исключение при создании записи в таблице Room",e.getCause());
        }
    }
}
