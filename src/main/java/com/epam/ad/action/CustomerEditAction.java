package com.epam.ad.action;

import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.Identified;
import com.epam.ad.dao.h2.CustomerDao;
import com.epam.ad.dao.h2.DaoFactory;
import com.epam.ad.entity.Customer;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;


public class CustomerEditAction implements Action {



    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        ActionResult createcustomeradmin = new ActionResult("customerdetail", true);
        String inputFirstName = request.getParameter("inputFirstName");
        String inputLastName = request.getParameter("inputLastName");
        String inputCity = request.getParameter("inputCity");
        String inputRegion = request.getParameter("inputRegion");
        String inputCountry = request.getParameter("inputCountry");
        String inputPassport = request.getParameter("inputPassport");
        String inputPhone = request.getParameter("inputPhone");
        String inputEmail = request.getParameter("inputEmail");
        String inputPrepayment = request.getParameter("inputPrepayment");
        String inputBookId = request.getParameter("inputBookId");
        String inputUserId = request.getParameter("userId");
        int bookId = Integer.parseInt(inputBookId);
        int userId = Integer.parseInt(inputUserId);
        int prepayment = (Integer.parseInt(inputPrepayment));
        DaoFactory daoFactory = DaoFactory.getInstance();
        CustomerDao customerDao= null;
        try {
            customerDao = (CustomerDao) daoFactory.getDao(Customer.class);
        } catch (DaoException e) {
            throw new ActionException("Исключение при поиске таблицы Customer",e.getCause());
        }
        try {
            customerDao.createCustomerRecord(inputFirstName, inputLastName, inputCity, inputRegion, inputCountry, inputPassport, inputPhone, inputEmail, prepayment, bookId, userId);
        } catch (DaoException e) {
            throw new ActionException("Исключение при создании записи в таблице Customer",e.getCause());
        }
        daoFactory.releaseContext();
        return createcustomeradmin;
    }


}
