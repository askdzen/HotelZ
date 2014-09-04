package com.epam.ad.action;

import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.DaoManager;
import com.epam.ad.dao.PersistenceAction.BookingPersistenceAction;
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
//        DaoFactory daoFactory=DaoFactory.getInstance();
//        BookingTableDao bookingTableDao= null;
//        try {
//            bookingTableDao = (BookingTableDao) daoFactory.getDao(BookingTable.class);
//        } catch (DaoException e) {
//            throw new ActionException("Исключение при поиске таблицы BookingTable",e.getCause());
//        }
        String bookingtableUnprocessedString = request.getParameter("unprocessed");
        String bookingtableConfirmedIdString=request.getParameter("confirm");
        String bookingtableUnConfirmedIdString=request.getParameter("unconfirm");
        String bookingtableDelete = request.getParameter("delete");
        DaoManager daoManager=new DaoManager();

        if (bookingtableConfirmedIdString !=null){
              bookConfirm(daoManager, bookingtableConfirmedIdString, BookingTable.Confirm.CONFIRM);
            daoManager.releaseConnection();
            return bookingtable;

        }
        if (bookingtableUnConfirmedIdString !=null){
            bookConfirm(daoManager, bookingtableUnConfirmedIdString, BookingTable.Confirm.UNCONFIRM);
            daoManager.releaseConnection();
            return bookingtable;
        }
        if (bookingtableUnprocessedString !=null){
           bookConfirm(daoManager,bookingtableUnprocessedString, BookingTable.Confirm.UNPROCESSED);
            daoManager.releaseConnection();
            return bookingtable;
        }
        if (bookingtableDelete !=null){
            bookDelete(daoManager, bookingtableDelete);
            daoManager.releaseConnection();
//            daoFactory.releaseContext();
            return bookingtable;
       }

        getParametersAndUpdate(request, daoManager);
        return bookingtable;



    }

    private void getParametersAndUpdate(HttpServletRequest request, DaoManager daoManager) throws ActionException {
        String dateFrom= request.getParameter("datefrom");
        String dateTo = request.getParameter("dateto");
        String dayCount = request.getParameter("daycount");
        String roomNo = request.getParameter("roomno");
        String userId=request.getParameter("userid");
        String confirm=request.getParameter("confirmupdate");
        String btId=request.getParameter("btid");
        BookingPersistenceAction persistenceAction=new BookingPersistenceAction(daoManager);
        persistenceAction.setDateFrom(dateFrom);
        persistenceAction.setDateTo(dateTo);
        persistenceAction.setDayCount(dayCount);
        persistenceAction.setRoomNo(roomNo);
        persistenceAction.setUserId(userId);
        persistenceAction.setConfirm(confirm);
        persistenceAction.setId(Integer.parseInt(btId));
        try {
            persistenceAction.doUpdateAction();
        } catch (DaoException e) {
            throw new ActionException("Исключение при обновлении записи таблицы BookingTable",e.getCause());
        }
//        try {
//            bookingTableDao.updateRecord(dateFrom, dateTo, dayCount, roomNo, userId, confirm, btId);
//        } catch (DaoException e) {
//            throw new ActionException("Исключение при обновлении записи таблицы BookingTable",e.getCause());
//        }
    }

    private void bookDelete(DaoManager daoManager, String bookingtableDelete) throws ActionException {
        int bookingRecordDeleteId=Integer.parseInt(bookingtableDelete);
//        BookingTable tableRecord = null;
//        try {
//            tableRecord = bookingTableDao.getByPK(bookingRecordDeleteId);
//        } catch (DaoException e) {
//            throw new ActionException("Исключение при поиске записи по ключу в таблице BookingTable",e.getCause());
//        }
        //  tableRecord.setDelete(true);
        // bookingTableDao.update(tableRecord);
        try {
            BookingTable booking=daoManager.getBookingTableDao().getByPK(bookingRecordDeleteId);
           // daoManager.getBookingTableDao().delete(booking);
            booking.setDelete(true);
          daoManager.getBookingTableDao().update(booking);
        } catch (DaoException e) {
            throw new ActionException("Исключение при удалении записи таблицы BookingTable",e.getCause());
        }
    }

    private void bookConfirm(DaoManager daoManager, String bookingtableConfirmVar, BookingTable.Confirm confirm) throws ActionException {
        int bookingtableConfirmId=Integer.parseInt(bookingtableConfirmVar);
//        BookingTable tableRecord= null;
//        try {
//            tableRecord = bookingTableDao.getByPK(bookingtableConfirmId);
//        } catch (DaoException e) {
//            throw new ActionException("Исключение при поиске записи по ключу в таблице BookingTable",e.getCause());
//        }

//        try {
////            BookingTable booking=daoManager.getBookingTableDao().getByPK(bookingtableConfirmId);
////            booking.setConfirmed(true);
////            booking.setConfirm(BookingTable.Confirm.CONFIRM);
////            bookingTableDao.update(booking);
//        } catch (DaoException e) {
//            throw new ActionException("Исключение при обновлении таблицы BookingTable",e.getCause());
//        }finally {
//            daoFactory.releaseContext();
//        }
        try {
        BookingPersistenceAction persistenceAction=new BookingPersistenceAction(daoManager);
        BookingTable booking=daoManager.getBookingTableDao().getByPK(bookingtableConfirmId);
            persistenceAction.setDateFrom(String.valueOf(booking.getDateFrom()));
            persistenceAction.setDateTo(String.valueOf(booking.getDateTo()));
            persistenceAction.setDayCount(String.valueOf(booking.getDayCount()));
            persistenceAction.setRoomNo(String.valueOf(booking.getRoomNo()));
            persistenceAction.setUserId(String.valueOf(booking.getUserId()));
            persistenceAction.setConfirm(String.valueOf(confirm));
            persistenceAction.setId(booking.getId());
            persistenceAction.doUpdateAction();
        } catch (DaoException e) {
            throw new ActionException("Исключение при обновлении таблицы BookingTable",e.getCause());
        }
    }


}
