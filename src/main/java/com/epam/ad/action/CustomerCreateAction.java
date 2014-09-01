package com.epam.ad.action;


import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.h2.CustomerDao;
import com.epam.ad.dao.h2.DaoFactory;
import com.epam.ad.entity.Customer;

import javax.servlet.http.HttpServletRequest;

public class CustomerCreateAction implements Action{

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        ActionResult customerdetail=new ActionResult("customerdetail",true);
        DaoFactory daoFactory=DaoFactory.getInstance();
        getParametersAndCreate(request, daoFactory);
        daoFactory.releaseContext();
        return customerdetail;

    }

    private void getParametersAndCreate(HttpServletRequest request, DaoFactory daoFactory) throws ActionException {
        String inputFirstName = request.getParameter("inputFirstNamec");
        String inputLastName = request.getParameter("inputLastNamec");
        String inputCity = request.getParameter("inputCityc");
        String inputRegion = request.getParameter("inputRegionc");
        String inputCountry = request.getParameter("inputCountryc");
        String inputPassport = request.getParameter("inputPassportc");
        String inputPhone = request.getParameter("inputPhonec");
        String inputEmail = request.getParameter("inputEmailc");
        String inputPrepayment = request.getParameter("inputPrepaymentc");
        String inputBookId = request.getParameter("inputBookIdc");
        String inputUserId = request.getParameter("userIdc");
        int bookId = Integer.parseInt(inputBookId);
        int userId = Integer.parseInt(inputUserId);
        int prepayment = (Integer.parseInt(inputPrepayment));
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
    }
}
