package com.epam.ad.action.admin.customer;


import com.epam.ad.action.Action;
import com.epam.ad.action.ActionException;
import com.epam.ad.action.ActionResult;
import com.epam.ad.dao.DaoManager;
import com.epam.ad.dao.h2.DaoFactory;

import javax.servlet.http.HttpServletRequest;

public class CustomerCreateAction implements Action {

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        return getParametersAndCreate(request);
    }

    private ActionResult getParametersAndCreate(HttpServletRequest request) throws ActionException {
        ActionResult customerdetail=new ActionResult("customerdetail",true);
        ActionResult customercreate = new ActionResult("customercreate");
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
        if (inputBookId.isEmpty()){
            request.setAttribute("bookidisempty","Введите ID заказа клиента!");
            return customercreate;
        }
        int bookId = Integer.parseInt(inputBookId);
        if (inputUserId.isEmpty()){
            request.setAttribute("useridisempty","Введите ID пользователя, оформляющего заказ!");
            return customercreate;
        }
        int userId = Integer.parseInt(inputUserId);
        if (inputPrepayment.isEmpty()){
            request.setAttribute("prepaymentisnull","Введите сумму предоплаты!");
            return customercreate;
        }
        DaoFactory daoFactory=new DaoFactory();
        DaoManager daoManager=daoFactory.createDaoManager();
        int prepayment = (Integer.parseInt(inputPrepayment));
        daoManager.getCustomerDao().createCustomerWithDaoManager(daoManager,inputFirstName, inputLastName, inputCity, inputRegion, inputCountry, inputPassport, inputPhone, inputEmail, bookId, userId, prepayment);
        return customerdetail;
    }


}
