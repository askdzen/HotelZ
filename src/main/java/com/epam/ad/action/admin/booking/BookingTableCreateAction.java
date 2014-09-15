package com.epam.ad.action.admin.booking;

import com.epam.ad.action.Action;
import com.epam.ad.action.ActionException;
import com.epam.ad.action.ActionResult;
import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.DaoManager;
import com.epam.ad.dao.h2.DaoFactory;

import javax.servlet.http.HttpServletRequest;

public class BookingTableCreateAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {

        return getParametersAndCreate(request);

    }

    private ActionResult getParametersAndCreate(HttpServletRequest request) throws ActionException {
        ActionResult bookingtable=new ActionResult("bookingtable",true);
        ActionResult createBooking=new ActionResult("bookingtablecreate");
        String dateFrom= request.getParameter("datefromc");
        String dateTo = request.getParameter("datetoc");
        String roomNo = request.getParameter("roomnoc");
        String userId=request.getParameter("useridc");
        if (userId.isEmpty()){
            request.setAttribute("useridisempty","Введите ID Пользователя, оформляющего заказ!");
            return createBooking;
        }
        if (roomNo.isEmpty()){
            request.setAttribute("roomidisempty","Введите ID бронируемой комнаты!");
            return createBooking;
        }
        DaoFactory daoFactory=new DaoFactory();
        DaoManager daoManager=daoFactory.createDaoManager();

        try {
            daoManager.getBookingTableDao().createBookingWithDaoManager(daoManager, dateFrom, dateTo, roomNo, userId);
        } catch (DaoException e) {
            throw new ActionException("Исключение при создании записи BookingTable",e.getCause());
        }
        return bookingtable;
    }


}
