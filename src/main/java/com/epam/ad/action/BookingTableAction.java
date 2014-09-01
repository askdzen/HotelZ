package com.epam.ad.action;

import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.h2.BookingTableDao;
import com.epam.ad.dao.h2.DaoFactory;
import com.epam.ad.entity.BookingTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class BookingTableAction implements Action {

    public static final Logger LOGGER = LoggerFactory.getLogger(BookingTableAction.class);


    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        ActionResult bookingtableadmin = new ActionResult("bookingtable");
        ActionResult bookingtableupdate = new ActionResult("bookingtableupdate");
        ActionResult bookingtablecreate = new ActionResult("bookingtablecreate", true);
        DaoFactory daoFactory = DaoFactory.getInstance();
        BookingTableDao bookingTableDao = null;
        try {
            bookingTableDao = (BookingTableDao) daoFactory.getDao(BookingTable.class);
        } catch (DaoException e) {
            throw new ActionException("Исключение при запросе к таблице BookingTable", e.getCause());
        }
        String bookingtableUpdateDataString = request.getParameter("update");
        String bookingtableCreate = request.getParameter("create");

        if (bookingtableCreate != null) {
            return bookingtablecreate;
        }
        if (bookingtableUpdateDataString != null) {
            setAttributesForUpdate(request, bookingTableDao, bookingtableUpdateDataString);
            return bookingtableupdate;
        } else {
            try {
                Pagination<BookingTable, BookingTableDao> pagination = new Pagination<>();
                pagination.executePaginationAction(request, bookingTableDao, "bookingtable");
                return bookingtableadmin;

            } catch (DaoException e) {
                throw new ActionException("Исключение при выводе таблицы BookingTable с заданными параметрами вывода количества строк ", e.getCause());
            }

        }
    }

    private void setAttributesForUpdate(HttpServletRequest request, BookingTableDao bookingTableDao, String bookingtableUpdateDataString) throws ActionException {
        int bookingtableUpdateDataId = Integer.parseInt(bookingtableUpdateDataString);
        BookingTable tableRecord = null;
        try {
            tableRecord = bookingTableDao.getByPK(bookingtableUpdateDataId);
        } catch (DaoException e) {
            throw new ActionException("Исключение при поиске таблицы BookingTable", e.getCause());
        }
        //  System.out.println(tableRecord.getId());
        HttpSession session = request.getSession();
        session.setAttribute("datefrom", tableRecord.getDateFrom());
        session.setAttribute("dateto", tableRecord.getDateTo());
        session.setAttribute("daycount", tableRecord.getDayCount());
        session.setAttribute("roomno", tableRecord.getRoomNo());
        session.setAttribute("userid", tableRecord.getUserId());
        session.setAttribute("confirmupdate", tableRecord.getConfirm());
        session.setAttribute("btid", tableRecord.getId());
    }


}
