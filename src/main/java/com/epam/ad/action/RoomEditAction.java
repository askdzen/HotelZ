package com.epam.ad.action;

import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.h2.DaoFactory;
import com.epam.ad.dao.h2.RoomDao;
import com.epam.ad.entity.Room;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Askar on 01.09.2014.
 */
public class RoomEditAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {

        ActionResult roomdetail = new ActionResult("roomdetail", true);
        String roomDeleteString = request.getParameter("delete");
        DaoFactory daoFactory = new DaoFactory();
        RoomDao roomDao= null;
        try {
            roomDao = (RoomDao) daoFactory.getDao(Room.class);
        } catch (DaoException e) {
            throw new ActionException("Исключение при получении таблицы BookingTable",e.getCause());
        }
        if (roomDeleteString !=null){
            roomDelete(roomDeleteString, roomDao);
            return roomdetail;
        }
        getParametersAndUpdate(request, roomDao);
        daoFactory.releaseContext();
        return roomdetail;
    }

    private void getParametersAndUpdate(HttpServletRequest request, RoomDao roomDao) throws ActionException {
        try {
            String roomNo = request.getParameter("roomno");
            String roomType =request.getParameter("roomtype");
            String bedType = request.getParameter("bedtype");
            String tarif = request.getParameter("tarif");
            String roomId = request.getParameter("roomid");
            roomDao.updateRecord(roomNo,roomType,bedType,tarif,roomId);
        } catch (DaoException e) {
            throw new ActionException("Исключение при обновлении записи таблицы Customer",e.getCause());
        }
    }

    private void roomDelete(String roomDelete, RoomDao roomDao) throws ActionException {
        try {

            int roomRecordDeleteId=Integer.parseInt(roomDelete);
            Room tableRecord = roomDao.getByPK(roomRecordDeleteId);
            roomDao.delete(tableRecord);
        } catch (DaoException e) {
            throw new ActionException("Исключение при удалении записи таблицы Customer",e.getCause());
        }
    }
}
