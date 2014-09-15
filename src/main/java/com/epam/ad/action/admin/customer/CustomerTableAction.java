package com.epam.ad.action.admin.customer;

import com.epam.ad.action.Action;
import com.epam.ad.action.ActionException;
import com.epam.ad.action.ActionResult;
import com.epam.ad.action.Pagination;
import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.DaoManager;
import com.epam.ad.dao.h2.CustomerDao;
import com.epam.ad.dao.h2.DaoFactory;
import com.epam.ad.entity.Customer;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


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
                request.setAttribute("column",0);
                request.setAttribute("value",0);
                request.setAttribute("hiddenButton","");
                request.setAttribute("disabled","");
                Pagination<Customer, CustomerDao> pagination = new Pagination<>();
                pagination.executePaginationAction(request, customerDao, "customerdetail");
                Map<String,String> selectedColumn=new HashMap<>();
                selectedColumn.put("ID","selected");
                selectedColumn.put("NAME","selected");
                selectedColumn.put("LAST_NAME", "selected");
                selectedColumn.put("CITY", "selected");
                selectedColumn.put("REGION","selected");
                selectedColumn.put("COUNTRY","selected");
                selectedColumn.put("PASSPORT", "selected");
                selectedColumn.put("PHONE", "selected");
                selectedColumn.put("EMAIL","selected");
                selectedColumn.put("PREPAYMENT","selected");
                selectedColumn.put("BOOK_ID", "selected");
                selectedColumn.put("USER_ID", "selected");

                for (String s : selectedColumn.keySet()) {
                    if (s.equals(request.getParameter("column"))){
                        request.setAttribute(s,selectedColumn.get(s));
                    }
                }
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
