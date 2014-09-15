package com.epam.ad.action.admin.booking;

import com.epam.ad.action.Action;
import com.epam.ad.action.ActionException;
import com.epam.ad.action.ActionResult;
import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.DaoManager;
import com.epam.ad.dao.PersistenceAction.BookingPersistenceAction;
import com.epam.ad.dao.h2.DaoFactory;
import com.epam.ad.entity.BookingTable;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.sql.SQLException;

public class BookingTableEditAction implements Action {

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        ActionResult bookingtable=new ActionResult("bookingtable",true);
        DaoFactory daoFactory=new DaoFactory();
        String bookingtableUnprocessedString = request.getParameter("unprocessed");
        String bookingtableConfirmedIdString=request.getParameter("confirm");
        String bookingtableUnConfirmedIdString=request.getParameter("unconfirm");
        String bookingtableDelete = request.getParameter("delete");
        DaoManager daoManager=daoFactory.createDaoManager();

        if (bookingtableConfirmedIdString !=null){
              bookConfirm(daoManager, bookingtableConfirmedIdString, BookingTable.Confirm.CONFIRM);
            daoFactory.releaseContext();
            return bookingtable;

        }
        if (bookingtableUnConfirmedIdString !=null){
            bookConfirm(daoManager, bookingtableUnConfirmedIdString, BookingTable.Confirm.UNCONFIRM);
            daoFactory.releaseContext();
            return bookingtable;
        }
        if (bookingtableUnprocessedString !=null){
           bookConfirm(daoManager,bookingtableUnprocessedString, BookingTable.Confirm.UNPROCESSED);
            daoFactory.releaseContext();
            return bookingtable;
        }
        if (bookingtableDelete !=null){
            bookDelete(daoManager, bookingtableDelete);
            daoFactory.releaseContext();
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

    }

    private void bookDelete(DaoManager daoManager, String bookingtableDelete) throws ActionException {
        int bookingRecordDeleteId=Integer.parseInt(bookingtableDelete);

        try {
            daoManager.transactionAndClose(new DaoManager.DaoCommand() {
                @Override
                public Object execute(DaoManager daoManager) throws DaoException, SQLException, ActionException {
                    BookingTable booking=daoManager.getBookingTableDao().getByPK(bookingRecordDeleteId);
                    booking.setDateFrom(Date.valueOf("1900-01-01"));
                    booking.setDateTo(Date.valueOf("1900-01-01"));
                    booking.setDelete(true);
                    daoManager.getBookingTableDao().update(booking);
                    return null;
                }
            });

        } catch (DaoException e) {
            throw new ActionException("Исключение при удалении записи таблицы BookingTable",e.getCause());
        }
    }

    private void bookConfirm(DaoManager daoManager, String bookingtableConfirmVar, BookingTable.Confirm confirm) throws ActionException {
        int bookingtableConfirmId=Integer.parseInt(bookingtableConfirmVar);

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
