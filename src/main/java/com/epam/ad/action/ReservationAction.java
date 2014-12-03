package com.epam.ad.action;

import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.DaoManager;
import com.epam.ad.dao.PersistenceAction.ReservationPersistenceAction;
import com.epam.ad.dao.h2.DaoFactory;
import com.epam.ad.entity.BookingTable;
import com.epam.ad.entity.Customer;
import com.epam.ad.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;


public class ReservationAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        ActionResult reservation = new ActionResult("reservation");
        String dateFrom = request.getParameter("inDateFrom");
        String dateTo = request.getParameter("inDateTo");
        String roomId = request.getParameter("inRoomId");

        HttpSession session = request.getSession();
        session.setAttribute("hidden","");
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
        DaoManager daoManager=daoFactory.createDaoManager();
        BookingTable bookingTable = daoManager.getBookingTableDao().createBooking(dateFrom, dateTo, roomId, userId, confirm);
        Customer customer = daoManager.getCustomerDao().create(inputFirstName, inputLastName, inputCity, inputRegion, inputCountry, inputPassport, inputPhone, inputEmail, prepayment, userId);
        ReservationPersistenceAction reservationPersistenceAction =new ReservationPersistenceAction(daoManager,bookingTable,customer);
        try {
            reservationPersistenceAction.doAction();
        } catch (DaoException e) {
            throw new ActionException("Исключение при выполнении транзакции",e.getCause());
        }
        daoFactory.releaseContext();
        return reservation;
    }






}
