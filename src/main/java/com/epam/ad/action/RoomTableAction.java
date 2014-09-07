package com.epam.ad.action;

import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.h2.DaoFactory;
import com.epam.ad.dao.h2.RoomDao;
import com.epam.ad.entity.Room;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


public class RoomTableAction implements Action {


    @Override

    public ActionResult execute(HttpServletRequest request) throws ActionException {
        ActionResult roomdetail = new ActionResult("roomdetail");
        ActionResult roomupdate = new ActionResult("roomupdate");
        ActionResult roomcreate = new ActionResult("roomcreate", true);
        DaoFactory daoFactory=new DaoFactory();
        RoomDao roomDao=daoFactory.createDaoManager().getRoomDao();

        String roomUpdateDataString = request.getParameter("update");
        String roomCreate = request.getParameter("create");
        if (roomCreate !=null){
            daoFactory.releaseContext();
            return roomcreate;
        }
        if (roomUpdateDataString!=null){
            setAttributesForUpdate(request, roomDao, roomUpdateDataString);
            daoFactory.releaseContext();
            return roomupdate;
        }else {
            try {
                Pagination<Room, RoomDao> pagination = new Pagination<>();
                pagination.executePaginationAction(request, roomDao, "roomdetail");
                daoFactory.releaseContext();
                return roomdetail;

            } catch (DaoException e) {
                throw new ActionException("Исключение при выводе таблицы BookingTable с заданными параметрами вывода количества строк ", e.getCause());
            }
        }

    }

    private void setAttributesForUpdate(HttpServletRequest request, RoomDao roomDao, String roomUpdateDataString) throws ActionException {
        int bookingtableUpdateDataId = Integer.parseInt(roomUpdateDataString);
        Room tableRecord = null;
        try {
            tableRecord = roomDao.getByPK(bookingtableUpdateDataId);
        } catch (DaoException e) {
            throw new ActionException("Исключение при поиске записи в таблице Room", e.getCause());
        }

        HttpSession session = request.getSession();
        session.setAttribute("roomno",tableRecord.getRoomNo());
        session.setAttribute("roomtype",tableRecord.getRoomType());
        session.setAttribute("bedtype",tableRecord.getRoomBed());
        session.setAttribute("tarif",tableRecord.getRoomRate());
        session.setAttribute("roomid",tableRecord.getId());

    }


}
