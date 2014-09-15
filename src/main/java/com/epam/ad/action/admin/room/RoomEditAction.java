package com.epam.ad.action.admin.room;

import com.epam.ad.action.Action;
import com.epam.ad.action.ActionException;
import com.epam.ad.action.ActionResult;
import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.DaoManager;
import com.epam.ad.dao.h2.DaoFactory;
import com.epam.ad.dao.h2.RoomDao;
import com.epam.ad.entity.Room;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;


public class RoomEditAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {

        ActionResult roomdetail = new ActionResult("roomdetail", true);
        String roomDeleteString = request.getParameter("delete");
        DaoFactory daoFactory = new DaoFactory();
        RoomDao roomDao= daoFactory.createDaoManager().getRoomDao();

        if (roomDeleteString !=null){
            try {
                daoFactory.createDaoManager().transactionAndClose(new DaoManager.DaoCommand() {
                    @Override
                    public Object execute(DaoManager daoManager) throws DaoException, SQLException, ActionException {
                        roomDelete(roomDeleteString, roomDao);
                        return null;
                    }
                });
            } catch (DaoException e) {
                throw new ActionException("Исключение при удалении записи таблицы Room",e.getCause());
            }
            daoFactory.releaseContext();
            return roomdetail;
        }
        try {
            daoFactory.createDaoManager().transactionAndClose(new DaoManager.DaoCommand() {
                @Override
                public Object execute(DaoManager daoManager) throws DaoException, SQLException, ActionException {
                    getParametersAndUpdate(request, roomDao);
                    daoFactory.releaseContext();
                    return null;
                }
            });
            return roomdetail;
        } catch (DaoException e) {
            throw new ActionException("Исключение при обновлении таблицы Room",e.getCause());
        }

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
            throw new ActionException("Исключение при обновлении записи таблицы Room",e.getCause());
        }
    }

    private void roomDelete(String roomDelete, RoomDao roomDao) throws ActionException {
        try {

            int roomRecordDeleteId=Integer.parseInt(roomDelete);
            Room tableRecord = roomDao.getByPK(roomRecordDeleteId);
            tableRecord.setIsDeleted(true);
            roomDao.update(tableRecord);
        } catch (DaoException e) {
            throw new ActionException("Исключение при удалении записи таблицы Room",e.getCause());
        }
    }
}
