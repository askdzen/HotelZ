package com.epam.ad.action;

import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.DaoManager;
import com.epam.ad.dao.h2.CustomerDao;
import com.epam.ad.dao.h2.DaoFactory;
import com.epam.ad.entity.Customer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


public class CustomerTableAction implements Action {


    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        ActionResult customeradmin = new ActionResult("customerdetail");
        ActionResult customerupdate = new ActionResult("customerupdate");
        ActionResult customercreate = new ActionResult("customercreate", true);
        DaoFactory daoFactory=new DaoFactory();
        DaoManager daoManager=daoFactory.createDaoManager();

        CustomerDao customerDao = daoManager.getCustomerDao();

        String customerUpdateDataString = request.getParameter("update");
        String customerCreate = request.getParameter("create");
        if (customerCreate != null) {
            daoFactory.releaseContext();
            return customercreate;
        }
        if (customerUpdateDataString != null) {
            SetAttributesForUpdate(request, daoManager.getCustomerDao(), customerUpdateDataString);
            daoFactory.releaseContext();
            return customerupdate;
        } else {
            try {
                Pagination<Customer, CustomerDao> pagination = new Pagination<>();
                pagination.executePaginationAction(request, customerDao, "customerdetail");
                daoFactory.releaseContext();
                return customeradmin;
            } catch (DaoException e) {
                throw new ActionException("Исключение при выводе всех данных таблицы Customer", e.getCause());
            }

        }
    }

    private void SetAttributesForUpdate(HttpServletRequest request, CustomerDao customerDao, String customerUpdateDataString) throws ActionException {
        int customerUpdateDataId = Integer.parseInt(customerUpdateDataString);
        Customer tableRecord = null;
        try {
            tableRecord = customerDao.getByPK(customerUpdateDataId);
        } catch (DaoException e) {
            throw new ActionException("Исключение при поиске таблицы BookingTable", e.getCause());
        }
        HttpSession session = request.getSession();
        session.setAttribute("firstname", tableRecord.getFirstName());
        session.setAttribute("lastname", tableRecord.getLastName());
        session.setAttribute("city", tableRecord.getCity());
        session.setAttribute("region", tableRecord.getRegion());
        session.setAttribute("country", tableRecord.getCountry());
        session.setAttribute("passport", tableRecord.getPassport());
        session.setAttribute("phone", tableRecord.getPhone());
        session.setAttribute("prepayment", tableRecord.getPrepayment());
        session.setAttribute("bookid", tableRecord.getBookId());
        session.setAttribute("userid", tableRecord.getUserId());
        session.setAttribute("customerid", tableRecord.getId());
    }
}
