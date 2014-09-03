package com.epam.ad.action;


import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.DaoManager;
import com.epam.ad.dao.PersistenceAction.CustomerPersistenceAction;
import com.epam.ad.dao.h2.CustomerDao;
import com.epam.ad.dao.h2.DaoFactory;
import com.epam.ad.entity.Customer;

import javax.servlet.http.HttpServletRequest;

public class CustomerCreateAction implements Action{

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {

       // DaoFactory daoFactory=DaoFactory.getInstance();
       ActionResult result= getParametersAndCreate(request);
      //  daoFactory.releaseContext();
        return result;

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
        int prepayment = (Integer.parseInt(inputPrepayment));
        DaoManager daoManager=new DaoManager();
        CustomerPersistenceAction persistenceAction=new CustomerPersistenceAction(daoManager);
        persistenceAction.setInputFirstName(inputFirstName);
        persistenceAction.setInputLastName(inputLastName);
        persistenceAction.setInputCity(inputCity);
        persistenceAction.setInputRegion(inputRegion);
        persistenceAction.setInputCountry(inputCountry);
        persistenceAction.setInputPassport(inputPassport);
        persistenceAction.setInputPhone(inputPhone);
        persistenceAction.setInputEmail(inputEmail);
        persistenceAction.setPrepayment(prepayment);
        persistenceAction.setUserId(userId);
        persistenceAction.setBookId(bookId);
        try {
            persistenceAction.doCreateAction();
        } catch (DaoException e) {
            e.printStackTrace();
        }

        return customerdetail;
    }
}
