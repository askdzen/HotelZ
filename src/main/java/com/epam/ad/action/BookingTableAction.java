package com.epam.ad.action;

import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.h2.BookingTableDao;
import com.epam.ad.dao.h2.DaoFactory;
import com.epam.ad.entity.BookingTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BookingTableAction implements Action {

    public static final Logger LOGGER = LoggerFactory.getLogger(BookingTableAction.class);
    public static final int DEFAULT_PAGE_NUMBER = 1;
    public static final int DEFAULT_ROWS_COUNT = 10;


    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        ActionResult bookingtableadmin = new ActionResult("bookingtable");
        ActionResult bookingtableupdate=new ActionResult("bookingtableupdate");
        ActionResult bookingtablecreate=new ActionResult("bookingtablecreate",true);
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookingTableDao bookingTableDao= null;
        try {
            bookingTableDao = (BookingTableDao) daoFactory.getDao(BookingTable.class);
        } catch (DaoException e) {
            throw new ActionException("Исключение при запросе к таблице BookingTable",e.getCause());
        }
        String bookingtableUpdateDataString=request.getParameter("update");
        String bookingtableCreate = request.getParameter("create");
        if (bookingtableCreate !=null){
        return bookingtablecreate;
        }
         if(bookingtableUpdateDataString !=null){

        int bookingtableUpdateDataId =Integer.parseInt(bookingtableUpdateDataString);
             BookingTable tableRecord = null;
             try {
                 tableRecord = bookingTableDao.getByPK(bookingtableUpdateDataId);
             } catch (DaoException e) {
                 throw new ActionException("Исключение при поиске таблицы BookingTable",e.getCause());
             }
             //  System.out.println(tableRecord.getId());
        HttpSession session=request.getSession();
        session.setAttribute("datefrom",tableRecord.getDateFrom());
        session.setAttribute("dateto",tableRecord.getDateTo());
        session.setAttribute("daycount",tableRecord.getDayCount());
        session.setAttribute("roomno",tableRecord.getRoomNo());
        session.setAttribute("userid",tableRecord.getUserId());
        session.setAttribute("confirmupdate",tableRecord.getConfirm());
        session.setAttribute("btid",tableRecord.getId());
        return bookingtableupdate;
         }else {

             int pageNumber = DEFAULT_PAGE_NUMBER;
             int rowsCount = DEFAULT_ROWS_COUNT;
             String pageString = request.getParameter("page");
             String rowsString = request.getParameter("rows");
             if (pageString != null) pageNumber = Integer.valueOf(pageString);
             if (rowsString != null) rowsCount = Integer.valueOf(rowsString);
             // BookingTableDao bookingTableDao = (BookingTableDao) daoFactory.getDao(BookingTable.class);
             List<BookingTable> tableList = null;
             try {
                 tableList = bookingTableDao.getRange(pageNumber, rowsCount);
                 List<BookingTable> pagLenghtList = bookingTableDao.getAll();
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
                 request.setAttribute("pagename","bookingtable");
                 daoFactory.releaseContext();
                 return bookingtableadmin;
             } catch (DaoException e) {
                 throw new ActionException("Исключение при выводе таблицы BookingTable с заданными параметрами вывода количества строк ",e.getCause());
             }
         }
    }

}
