package com.epam.ad.action;

import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.h2.CustomerDao;
import com.epam.ad.dao.h2.DaoFactory;
import com.epam.ad.entity.Customer;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


public class CustomerTableAction implements Action {
    public static final int DEFAULT_PAGE_NUMBER = 1;
    public static final int DEFAULT_ROWS_COUNT = 10;



    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        ActionResult customeradmin = new ActionResult("customerdetail");
            DaoFactory daoFactory = DaoFactory.getInstance();
            int pageNumber = DEFAULT_PAGE_NUMBER;
            int rowsCount = DEFAULT_ROWS_COUNT;
            String pageString = request.getParameter("page");
            String rowsString = request.getParameter("rows");
            if (pageString != null) pageNumber = Integer.valueOf(pageString);
            if (rowsString != null) rowsCount = Integer.valueOf(rowsString);


        CustomerDao customerDao = null;
        try {
            customerDao = (CustomerDao) daoFactory.getDao(Customer.class);
        } catch (DaoException e) {
            throw new ActionException("Исключение при поиске таблицы Customer",e.getCause());
        }

        List<Customer> tableList = null;
        try {
            tableList = customerDao.getRange(pageNumber, rowsCount);
        } catch (DaoException e) {
            throw new ActionException("Исключение при выводе таблицы Customer с заданными параметрами выводо количества строк",e.getCause());
        }
        List<Customer> pagLenghtList = null;
        try {
            pagLenghtList = customerDao.getAll();
        } catch (DaoException e) {
            throw new ActionException("Исключение при выводе всех данных таблицы Customer",e.getCause());
        }
        int tableLenght = pagLenghtList.size();
            List<Integer> paginationList = new ArrayList<>();
            for (int i = 0; i < tableLenght / rowsCount + 1; i++) {
                paginationList.add(i + 1);
            }
            if (pageNumber == tableLenght / rowsCount + 1) {
                request.setAttribute("nextdisabled", "disabled");
            }
            if (pageNumber == 1) {
                request.setAttribute("backdisabled", "disabled");
            }
            request.setAttribute("paginationlist", paginationList);
            request.setAttribute("list", tableList);
            request.setAttribute("pageNumber", pageNumber);
            request.setAttribute("rowsCount", rowsCount);

            daoFactory.releaseContext();
            return customeradmin;

    }

}
