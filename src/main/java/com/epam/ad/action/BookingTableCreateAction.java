package com.epam.ad.action;

import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.h2.BookingTableDao;
import com.epam.ad.dao.h2.DaoFactory;
import com.epam.ad.entity.BookingTable;

import javax.servlet.http.HttpServletRequest;

public class BookingTableCreateAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        ActionResult bookingtable=new ActionResult("bookingtable",true);
        DaoFactory daoFactory=DaoFactory.getInstance();
        BookingTableDao bookingTableDao= null;
        try {
            bookingTableDao = (BookingTableDao) daoFactory.getDao(BookingTable.class);
        } catch (DaoException e) {
            throw new ActionException("Исключение при поиске таблицы BookingTable",e.getCause());
        }
        String dateFrom= request.getParameter("datefromc");
        String dateTo = request.getParameter("datetoc");
        String roomNo = request.getParameter("roomnoc");
        String userId=request.getParameter("useridc");
        //String confirm=request.getParameter("confirm");
         int userIdInt = Integer.parseInt(userId);
        BookingTable.Confirm confirmEnum= BookingTable.Confirm.UNPROCESSED;
        try {
            bookingTableDao.createBookingTableRecord(dateFrom,dateTo,roomNo,userIdInt,false, confirmEnum);
        } catch (DaoException e) {
            throw new ActionException("Исключение при создании записи в таблице BookingTable",e.getCause());
        }

        return bookingtable;

    }
}
