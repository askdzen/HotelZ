package com.epam.ad.action;

import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.h2.DaoFactory;
import com.epam.ad.dao.h2.RoomDao;
import com.epam.ad.entity.Room;

import javax.servlet.http.HttpServletRequest;

public class RoomCreateAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {

        ActionResult roomdetail=new ActionResult("roomdetail",true);
        DaoFactory daoFactory=new DaoFactory();
        getParametersAndCreate(request, daoFactory);
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
            RoomDao  roomDao = (RoomDao) daoFactory.getDao(Room.class);
            roomDao.createRecord(roomNo,roomtype,bedtype,tarif);
        } catch (DaoException e) {
          throw new ActionException("Исключение при поиске таблицы Room",e.getCause());
        }
    }
}
