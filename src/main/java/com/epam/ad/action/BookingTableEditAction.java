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
            //System.out.println(bookingtableConfirmedIdString);
            int bookingtableConfirmedId=Integer.parseInt(bookingtableConfirmedIdString);
            BookingTable tableRecord= null;
            try {
                tableRecord = bookingTableDao.getByPK(bookingtableConfirmedId);
            } catch (DaoException e) {
                throw new ActionException("Исключение при поиске записи по ключу в таблице BookingTable",e.getCause());
            }
            tableRecord.setConfirmed(true);
            tableRecord.setConfirm(BookingTable.Confirm.CONFIRM);
            try {
                bookingTableDao.update(tableRecord);
            } catch (DaoException e) {
                throw new ActionException("Исключение при обновлении таблицы BookingTable",e.getCause());
            }
            // System.out.println(tableRecord.getId()+" "+tableRecord.getRoomNo()+" "+tableRecord.isConfirmed());
            return bookingtable;
        }
        if (bookingtableUnConfirmedIdString !=null){
            int bookingtableUnConfirmedId=Integer.parseInt(bookingtableUnConfirmedIdString);
            BookingTable tableRecord= null;
            try {
                tableRecord = bookingTableDao.getByPK(bookingtableUnConfirmedId);
            } catch (DaoException e) {
                throw new ActionException("Исключение при поиске записи по ключу в таблице BookingTable",e.getCause());
            }
            tableRecord.setConfirmed(false);
            tableRecord.setConfirm(BookingTable.Confirm.UNCONFIRM);
            try {
                bookingTableDao.update(tableRecord);
            } catch (DaoException e) {
                throw new ActionException("Исключение при обновлении таблицы BookingTable",e.getCause());
            }
            // System.out.println(tableRecord.getId()+" "+tableRecord.getRoomNo()+" "+tableRecord.isConfirmed());
            return bookingtable;
        }
        if (bookingtableUnprocessedString !=null){
            int bookingtableUnprocessedId=Integer.parseInt(bookingtableUnprocessedString);
            BookingTable tableRecord= null;
            try {
                tableRecord = bookingTableDao.getByPK(bookingtableUnprocessedId);
            } catch (DaoException e) {
                throw new ActionException("Исключение при поиске записи по ключу в таблице BookingTable",e.getCause());
            }
            tableRecord.setConfirmed(false);
            tableRecord.setConfirm(BookingTable.Confirm.UNPROCESSED);
            try {
                bookingTableDao.update(tableRecord);
            } catch (DaoException e) {
                throw new ActionException("Исключение при обновлении таблицы BookingTable",e.getCause());
            }
            // System.out.println(tableRecord.getId()+" "+tableRecord.getRoomNo()+" "+tableRecord.isConfirmed());
            return bookingtable;
        }
        if (bookingtableDelete !=null){
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
            return bookingtable;
       }


        String dateFrom= request.getParameter("datefrom");
        String dateTo = request.getParameter("dateto");
        String dayCount = request.getParameter("daycount");
        String roomNo = request.getParameter("roomno");
        String userId=request.getParameter("userid");
        String confirm=request.getParameter("confirmupdate");

        String btId=request.getParameter("btid");
        Date datefromDate=Date.valueOf(dateFrom);
        Date dateToDate = Date.valueOf(dateTo);
        int dayCountInt=Integer.parseInt(dayCount);
        int roomNoInt=Integer.parseInt(roomNo);
        int userIdInt = Integer.parseInt(userId);
        BookingTable.Confirm confirmEnum= BookingTable.Confirm.valueOf(confirm);
        int id=Integer.parseInt(btId);

        BookingTable tableRecord =new BookingTable();
        tableRecord.setDateFrom(datefromDate);
        tableRecord.setDateTo(dateToDate);
        tableRecord.setDayCount(dayCountInt);
        tableRecord.setRoomNo(roomNoInt);
        tableRecord.setUserId(userIdInt);
        tableRecord.setConfirm(confirmEnum);
        tableRecord.setId(id);
        try {
            bookingTableDao.update(tableRecord);
        } catch (DaoException e) {
            throw new ActionException("Исключение при обновлении таблицы BookingTable",e.getCause());
        }
        return bookingtable;



    }
}
