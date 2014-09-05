package com.epam.ad.action;

import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.DaoManager;
import com.epam.ad.dao.Identified;
import com.epam.ad.dao.PersistenceAction.ConfirmTransactionPersistenceAction;
import com.epam.ad.dao.h2.BookingTableDao;
import com.epam.ad.dao.h2.CustomerDao;
import com.epam.ad.dao.h2.DaoFactory;
import com.epam.ad.entity.BookingTable;
import com.epam.ad.entity.Customer;
import com.epam.ad.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;


public class ConfirmAction implements Action {




    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        ActionResult reservation = new ActionResult("welcome", true);
        String dateFrom = request.getParameter("inDateFrom");
        String dateTo = request.getParameter("inDateTo");
        String roomId = request.getParameter("inRoomId");

        HttpSession session = request.getSession();
        session.setAttribute("yes", "Вы успешно забронировали номер!");
        User user = (User) session.getAttribute("user");
        String inputFirstName = request.getParameter("inputFirstName");
        String inputLastName = request.getParameter("inputLastName");
        String inputCity = request.getParameter("inputCity");
        String inputRegion = request.getParameter("inputRegion");
        String inputCountry = request.getParameter("inputCountry");
        String inputPassport = request.getParameter("inputPassport");
        String inputPhone = request.getParameter("inputPhone");
        String inputEmail = request.getParameter("inputEmail");
        String inputPrepayment = request.getParameter("inputPrepayment");
        int prepayment = (Integer.parseInt(inputPrepayment) * 50) / 100;
        int userId = user.getId();
        BookingTable.Confirm confirm= BookingTable.Confirm.UNPROCESSED;
        DaoFactory daoFactory=new DaoFactory();
//        BookingTableDao bookingTableDao= null;
//        try {
//            bookingTableDao = (BookingTableDao) daoFactory.getDao(BookingTable.class);
//
//        } catch (DaoException e) {
//            throw new ActionException("Исключение при поиске таблицы BookingTable",e.getCause());
//        }

//        boolean confirmed = false;
//
//        BookingTable bookingTable = null;
//        try {
//            bookingTable = daoManager.getBookingTableDao().createBookingTableRecord(dateFrom, dateTo, roomId, userId, confirmed, confirm);
//        } catch (DaoException e) {
//            throw new ActionException("Исключение при создании записи в таблице BookingTable",e.getCause());
//        }
//        int bookId=(bookingTable.getId());
//        CustomerDao customerDao= null;
//        try {
//            customerDao = (CustomerDao) daoFactory.getDao(Customer.class);
//        } catch (DaoException e) {
//            throw new ActionException("Исключение при поиске таблицы Customer",e.getCause());
//        }

//        try {
//            daoManager.getCustomerDao().createCustomerRecord(inputFirstName, inputLastName, inputCity, inputRegion, inputCountry, inputPassport, inputPhone, inputEmail, prepayment, bookId, userId);
//        } catch (DaoException e) {
//            e.printStackTrace();
//        }
//
//        daoManager.releaseConnection();

        DaoManager daoManager=daoFactory.createDaoManager();
        ConfirmTransactionPersistenceAction transactionPersistenceAction =new ConfirmTransactionPersistenceAction(daoManager);
        transactionPersistenceAction.setDateFrom(dateFrom);
        transactionPersistenceAction.setDateTo(dateTo);
        transactionPersistenceAction.setRoomNo(roomId);
        transactionPersistenceAction.setInputFirstName(inputFirstName);
        transactionPersistenceAction.setInputLastName(inputLastName);
        transactionPersistenceAction.setInputCity(inputCity);
        transactionPersistenceAction.setInputRegion(inputRegion);
        transactionPersistenceAction.setInputCountry(inputCountry);
        transactionPersistenceAction.setInputPassport(inputPassport);
        transactionPersistenceAction.setInputPhone(inputPhone);
        transactionPersistenceAction.setInputEmail(inputEmail);
        transactionPersistenceAction.setPrepayment(prepayment);
        transactionPersistenceAction.setConfirm(String.valueOf(confirm));
        transactionPersistenceAction.setUserId(String.valueOf(userId));

        try {
            transactionPersistenceAction.doAction();
        } catch (DaoException e) {
            throw new ActionException("Исключение при выполнении транзакции",e.getCause());
        }

        return reservation;
    }



}
