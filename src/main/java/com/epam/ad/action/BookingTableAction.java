package com.epam.ad.action;

import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.DaoManager;
import com.epam.ad.dao.h2.BookingTableDao;
import com.epam.ad.dao.h2.DaoFactory;
import com.epam.ad.entity.BookingTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class BookingTableAction implements Action {

    public static final Logger LOGGER = LoggerFactory.getLogger(BookingTableAction.class);


    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        ActionResult bookingtableadmin = new ActionResult("bookingtable");
        ActionResult bookingtableupdate = new ActionResult("bookingtableupdate");
        ActionResult bookingtablecreate = new ActionResult("bookingtablecreate", true);
        DaoFactory daoFactory = new DaoFactory();

        String bookingtableUpdateDataString = request.getParameter("update");
        String bookingtableCreate = request.getParameter("create");
        DaoManager daoManager=daoFactory.createDaoManager();
        BookingTableDao bookingTableDao= daoManager.getBookingTableDao();

        if (bookingtableCreate != null) {
            daoFactory.releaseContext();
            return bookingtablecreate;
        }
        if (bookingtableUpdateDataString != null) {
            setAttributesForUpdate(request, bookingTableDao, bookingtableUpdateDataString,daoManager);
            daoFactory.releaseContext();
            return bookingtableupdate;
        } else {
            try {
                request.setAttribute("column",0);
                request.setAttribute("value",0);
                request.setAttribute("hiddenButton","");
                request.setAttribute("disabled","");
                Pagination<BookingTable, BookingTableDao> pagination = new Pagination<>();
                pagination.executePaginationAction(request, bookingTableDao, "bookingtable");
                Map<String,String> selectedColumn=new HashMap<>();
                selectedColumn.put("ID","selected");
                selectedColumn.put("DATE_FRO","selected");
                selectedColumn.put("DATE_TO", "selected");
                selectedColumn.put("NO_OF_DAY", "selected");
                selectedColumn.put("ROOM_NO","selected");
                selectedColumn.put("USER_ID","selected");
                selectedColumn.put("CONFIRM","selected");

                for (String s : selectedColumn.keySet()) {
                    if (s.equals(request.getParameter("column"))){
                        request.setAttribute(s,selectedColumn.get(s));
                    }
                }
                daoFactory.releaseContext();
                return bookingtableadmin;

            } catch (DaoException e) {
                throw new ActionException("Исключение при выводе таблицы BookingTable с заданными параметрами вывода количества строк ", e.getCause());
            }

        }
    }

    private void setAttributesForUpdate(HttpServletRequest request, BookingTableDao bookingTableDao, String bookingtableUpdateDataString,DaoManager daoManager) throws ActionException {
        try {
            daoManager.transactionAndClose(new DaoManager.DaoCommand() {
                @Override
                public Object execute(DaoManager daoManager) throws DaoException, SQLException {
                    int bookingtableUpdateDataId = Integer.parseInt(bookingtableUpdateDataString);
                    BookingTable tableRecord=bookingTableDao.getByPK(bookingtableUpdateDataId);
                    HttpSession session = request.getSession();
                    session.setAttribute("datefrom", tableRecord.getDateFrom());
                    session.setAttribute("dateto", tableRecord.getDateTo());
                    session.setAttribute("daycount", tableRecord.getDayCount());
                    session.setAttribute("roomno", tableRecord.getRoomNo());
                    session.setAttribute("userid", tableRecord.getUserId());
                    session.setAttribute("confirmupdate", tableRecord.getConfirm());
                    session.setAttribute("btid", tableRecord.getId());
                    return null;
                }
            });
        } catch (DaoException e) {
            throw new ActionException("Исключение при поиске таблицы BookingTable", e.getCause());
        }

    }


}
