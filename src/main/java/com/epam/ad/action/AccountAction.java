package com.epam.ad.action;

import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.DaoManager;
import com.epam.ad.dao.GenericDao;
import com.epam.ad.dao.PersistenceActionBase;
import com.epam.ad.dao.h2.BookingTableDao;
import com.epam.ad.dao.h2.CustomerDao;
import com.epam.ad.dao.h2.DaoFactory;
import com.epam.ad.dao.h2.RoomDao;
import com.epam.ad.entity.BookingTable;
import com.epam.ad.entity.Customer;
import com.epam.ad.entity.Room;
import com.epam.ad.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AccountAction implements Action {




    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        DaoFactory daoFactory = DaoFactory.getInstance();
       // daoFactory.getContext();

        int bookIdNumber;
        int roomIdNumber;
        ActionResult accountPage = new ActionResult("account");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
try {
    BookingTableDao bookingTableDao = (BookingTableDao) daoFactory.getDao(BookingTable.class);
    List<BookingTable> bookingList = bookingTableDao.getByUserId(user.getId());

    bookIdNumber = bookingList.get(0).getId();
    roomIdNumber = bookingList.get(0).getRoomNo();
    String bookIdString = request.getParameter("bookid");
    String roomIdString = request.getParameter("roomid");
    if (!(bookIdString == null)) bookIdNumber = Integer.parseInt(bookIdString);
    if (!(roomIdString == null)) roomIdNumber = Integer.parseInt(roomIdString);
    request.setAttribute("bookinglist", bookingList);
}catch (DaoException e){

   throw  new ActionException("Проблема с получением DAO BookingTable",e.getCause());

}


        List<Customer> customerList = new ArrayList<Customer>();
        CustomerDao customerDao = null;
        try {
            customerDao = (CustomerDao) daoFactory.getDao(Customer.class);
            Customer customer = customerDao.getByBookId(bookIdNumber);
            customerList.add(customer);
            request.setAttribute("customerlist", customerList);
        } catch (DaoException e) {
            throw new ActionException("Проблема с получением DAO Customer",e.getCause());
        }

        List<Room> roomList = new ArrayList<>();
        RoomDao roomDao = null;
        try {
            roomDao = (RoomDao) daoFactory.getDao(Room.class);
            Room room = roomDao.getByPK(roomIdNumber);
            roomList.add(room);
            request.setAttribute("roomlist", roomList);
        } catch (DaoException e) {
            throw new ActionException("Проблема с получением DAO Room",e.getCause());
        }



        daoFactory.releaseContext();



        return accountPage;
    }


}
