package com.epam.ad.action;

import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.DaoManager;
import com.epam.ad.dao.PersistenceAction.BookingPersistenceAction;
import com.epam.ad.dao.h2.BookingTableDao;
import com.epam.ad.dao.h2.DaoFactory;
import com.epam.ad.entity.BookingTable;

import javax.servlet.http.HttpServletRequest;

public class BookingTableCreateAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {

//        DaoFactory daoFactory=DaoFactory.getInstance();
//        BookingTableDao bookingTableDao= null;
//        try {
//            bookingTableDao = (BookingTableDao) daoFactory.getDao(BookingTable.class);
//        } catch (DaoException e) {
//            throw new ActionException("Исключение при поиске таблицы BookingTable",e.getCause());
//        }
        ActionResult result= getParametersAndCreate(request);
        return result;

    }

    private ActionResult getParametersAndCreate(HttpServletRequest request) throws ActionException {
        ActionResult bookingtable=new ActionResult("bookingtable",true);
        ActionResult createBooking=new ActionResult("bookingtablecreate");
        String dateFrom= request.getParameter("datefromc");
        String dateTo = request.getParameter("datetoc");
        String roomNo = request.getParameter("roomnoc");
        String userId=request.getParameter("useridc");
       // BookingTable.Confirm confirmEnum= BookingTable.Confirm.UNPROCESSED;
        if (userId.isEmpty()){
            request.setAttribute("useridisempty","Введите ID Пользователя, оформляющего заказ!");
            return createBooking;
        }
        if (roomNo.isEmpty()){
            request.setAttribute("roomidisempty","Введите ID бронируемой комнаты!");
            return createBooking;
        }
        DaoManager daoManager=new DaoManager();
        try {
            daoManager.getBookingTableDao().createBookingWithDaoManager(daoManager, dateFrom, dateTo, roomNo, userId);
        } catch (DaoException e) {
            throw new ActionException("Исключение при создании записи BookingTable",e.getCause());
        }
        return bookingtable;
    }


}
