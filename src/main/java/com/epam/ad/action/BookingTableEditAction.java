package com.epam.ad.action;

import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.h2.BookingTableDao;
import com.epam.ad.dao.h2.DaoFactory;
import com.epam.ad.entity.BookingTable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class BookingTableEditAction implements Action {

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
        String bookingtableUnprocessedString = request.getParameter("unprocessed");
        String bookingtableConfirmedIdString=request.getParameter("confirm");
        String bookingtableUnConfirmedIdString=request.getParameter("unconfirm");
        String bookingtableDelete = request.getParameter("delete");


        if (bookingtableConfirmedIdString !=null){
              bookConfirm(daoFactory, bookingTableDao, bookingtableConfirmedIdString, BookingTable.Confirm.CONFIRM);
            return bookingtable;

        }
        if (bookingtableUnConfirmedIdString !=null){
            bookConfirm(daoFactory, bookingTableDao, bookingtableUnConfirmedIdString, BookingTable.Confirm.UNCONFIRM);
                return bookingtable;
        }
        if (bookingtableUnprocessedString !=null){
           bookConfirm(daoFactory,bookingTableDao,bookingtableUnprocessedString, BookingTable.Confirm.UNPROCESSED);
            return bookingtable;
        }
        if (bookingtableDelete !=null){
            bookDelete(bookingTableDao, bookingtableDelete);
            daoFactory.releaseContext();
            return bookingtable;
       }

        getParametersAndUpdate(request, bookingTableDao);
        return bookingtable;



    }

    private void getParametersAndUpdate(HttpServletRequest request, BookingTableDao bookingTableDao) throws ActionException {
        String dateFrom= request.getParameter("datefrom");
        String dateTo = request.getParameter("dateto");
        String dayCount = request.getParameter("daycount");
        String roomNo = request.getParameter("roomno");
        String userId=request.getParameter("userid");
        String confirm=request.getParameter("confirmupdate");
        String btId=request.getParameter("btid");
        try {
            bookingTableDao.updateRecord(dateFrom, dateTo, dayCount, roomNo, userId, confirm, btId);
        } catch (DaoException e) {
            throw new ActionException("Исключение при обновлении записи таблицы BookingTable",e.getCause());
        }
    }

    private void bookDelete(BookingTableDao bookingTableDao, String bookingtableDelete) throws ActionException {
        int bookingRecordDeleteId=Integer.parseInt(bookingtableDelete);
        BookingTable tableRecord = null;
        try {
            tableRecord = bookingTableDao.getByPK(bookingRecordDeleteId);
        } catch (DaoException e) {
            throw new ActionException("Исключение при поиске записи по ключу в таблице BookingTable",e.getCause());
        }
        //  tableRecord.setDelete(true);
        // bookingTableDao.update(tableRecord);
        try {
            bookingTableDao.delete(tableRecord);
        } catch (DaoException e) {
            throw new ActionException("Исключение при удалении записи таблицы BookingTable",e.getCause());
        }
    }

    private void bookConfirm(DaoFactory daoFactory, BookingTableDao bookingTableDao, String bookingtableConfirmVar, BookingTable.Confirm confirm) throws ActionException {
        int bookingtableConfirmId=Integer.parseInt(bookingtableConfirmVar);
        BookingTable tableRecord= null;
        try {
            tableRecord = bookingTableDao.getByPK(bookingtableConfirmId);
        } catch (DaoException e) {
            throw new ActionException("Исключение при поиске записи по ключу в таблице BookingTable",e.getCause());
        }
        tableRecord.setConfirmed(true);
        tableRecord.setConfirm(BookingTable.Confirm.CONFIRM);
        try {
            bookingTableDao.update(tableRecord);
        } catch (DaoException e) {
            throw new ActionException("Исключение при обновлении таблицы BookingTable",e.getCause());
        }finally {
            daoFactory.releaseContext();
        }
    }


}
