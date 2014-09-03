package com.epam.ad.action;

import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.DaoManager;
import com.epam.ad.dao.Identified;
import com.epam.ad.dao.PersistenceAction.CustomerPersistenceAction;
import com.epam.ad.dao.h2.CustomerDao;
import com.epam.ad.dao.h2.DaoFactory;
import com.epam.ad.entity.Customer;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;


public class CustomerEditAction implements Action {



    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        ActionResult customerDetail = new ActionResult("customerdetail", true);
        String customerDelete = request.getParameter("delete");
        DaoManager daoManager=new DaoManager();
        CustomerDao customerDao= null;
        customerDao = daoManager.getCustomerDao();
        if (customerDelete !=null){
            customerDelete(customerDelete, customerDao);
            return customerDetail;
        }

        ActionResult result=getParametersAndUpdate(request,daoManager);
        return result;
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

    private ActionResult getParametersAndUpdate(HttpServletRequest request, DaoManager daoManager) throws ActionException {
        ActionResult customerUpdate = new ActionResult("customerupdate");
        ActionResult customerDetail = new ActionResult("customerdetail", true);
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
        if (inputBookId.isEmpty()){
            request.setAttribute("bookidisempty","Введите ID заказа клиента!");
            return customerUpdate;
        }
        if (inputUserId.isEmpty()){
            request.setAttribute("useridisempty","Введите ID пользователя, оформляющего заказ!");
            return customerUpdate;
        }
        if (inputPrepayment.isEmpty()){
            request.setAttribute("prepaymentisnull","Введите сумму предоплаты!");
            return customerUpdate;
        }
        int userId = Integer.parseInt(inputUserId);
        int bookId = Integer.parseInt(inputBookId);
        int prepayment = (Integer.parseInt(inputPrepayment));
        int id = Integer.parseInt(customerId);

        try {
            daoManager.getCustomerDao().updateWithDaoManager(daoManager, inputFirstName, inputLastName, inputCity, inputRegion, inputCountry, inputPassport, inputPhone, inputEmail, userId, bookId, prepayment, id);
        } catch (DaoException e) {
            throw new ActionException("Исключение при обновлении записи таблицы Customer",e.getCause());
        }
        return customerDetail;
    }



}
