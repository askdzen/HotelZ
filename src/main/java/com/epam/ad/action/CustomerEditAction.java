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
        String customerDelete = request.getParameter("delete");
        DaoFactory daoFactory = DaoFactory.getInstance();
        CustomerDao customerDao= null;
        try {
            customerDao = (CustomerDao) daoFactory.getDao(Customer.class);
        } catch (DaoException e) {
            throw new ActionException("Исключение при получении таблицы BookingTable",e.getCause());
        }
        if (customerDelete !=null){
            customerDelete(customerDelete, customerDao);
            return createcustomeradmin;
        }

        getParametersAndUpdate(request, customerDao);

        daoFactory.releaseContext();
        return createcustomeradmin;
    }

    private void customerDelete(String customerDelete, CustomerDao customerDao) throws ActionException {
        //  tableRecord.setDelete(true);
        // bookingTableDao.update(tableRecord);
        try {

            int customerRecordDeleteId=Integer.parseInt(customerDelete);
            Customer tableRecord = customerDao.getByPK(customerRecordDeleteId);
            customerDao.delete(tableRecord);
        } catch (DaoException e) {
            throw new ActionException("Исключение при удалении записи таблицы Customer",e.getCause());
        }
    }

    private void getParametersAndUpdate(HttpServletRequest request, CustomerDao customerDao) throws ActionException {
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
        String customerId=request.getParameter("customerId");
        int bookId = Integer.parseInt(inputBookId);
        int userId = Integer.parseInt(inputUserId);
        int prepayment = (Integer.parseInt(inputPrepayment));
        int id = Integer.parseInt(customerId);
        try {
            customerDao.updateRecord(inputFirstName, inputLastName, inputCity, inputRegion, inputCountry, inputPassport, inputPhone, inputEmail, bookId, userId, prepayment, id);
        } catch (DaoException e) {
            throw new ActionException("Исключение при обновлении записи таблицы Customer",e.getCause());
        }
    }


}
