package com.epam.ad.action;

import com.epam.ad.dao.AbstractJDBCDao;
import com.epam.ad.dao.DaoException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Askar on 01.09.2014.
 */
public class Pagination<T ,E extends AbstractJDBCDao> {
  public static final int DEFAULT_PAGE_NUMBER = 1;
  public static final int DEFAULT_ROWS_COUNT = 10;

    public void executePaginationAction(HttpServletRequest request, E genericDao, String pagename) throws DaoException {

        int pageNumber = DEFAULT_PAGE_NUMBER;
        int rowsCount = DEFAULT_ROWS_COUNT;
        String pageString = request.getParameter("page");
        String rowsString = request.getParameter("rows");
        if (pageString != null) pageNumber = Integer.valueOf(pageString);
        if (rowsString != null) rowsCount = Integer.valueOf(rowsString);
        List<T> tableList = null;
        tableList = genericDao.getRange(pageNumber, rowsCount);
        List<T> pagLenghtList = genericDao.getAll();
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
        request.setAttribute("pagename",pagename);

    }
}
