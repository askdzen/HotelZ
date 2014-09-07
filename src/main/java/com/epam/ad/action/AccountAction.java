package com.epam.ad.action;

import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.DaoManager;
import com.epam.ad.dao.h2.*;
import com.epam.ad.entity.BookingTable;
import com.epam.ad.entity.Customer;
import com.epam.ad.entity.Room;
import com.epam.ad.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AccountAction implements Action {


    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        DaoFactory daoFactory = new DaoFactory();

        DaoManager daoManager = daoFactory.createDaoManager();

        ActionResult accountPage = new ActionResult("account");
        HttpSession session = request.getSession();
        session.setAttribute("hidden","hidden=\"hidden\"");
        User user = (User) session.getAttribute("user");
        try {
            BookingTableDao bookingTableDao = daoManager.getBookingTableDao();
            CustomerDao customerDao = daoManager.getCustomerDao();
            RoomDao roomDao = daoManager.getRoomDao();
           // UserDao userDao=daoManager.getUserDao();
            daoManager.transactionAndClose(new DaoManager.DaoCommand() {
                @Override
                public Object execute(DaoManager daoManager) throws DaoException, SQLException {
                    List<BookingTable> bookingList = bookingTableDao.getByUserId(user.getId());
                    int bookIdNumber;
                    int roomIdNumber;
                    bookIdNumber = bookingList.get(0).getId();
                    roomIdNumber = bookingList.get(0).getRoomNo();
                    String bookIdString = request.getParameter("bookid");
                    String roomIdString = request.getParameter("roomid");
                    if (!(bookIdString == null)) bookIdNumber = Integer.parseInt(bookIdString);
                    if (!(roomIdString == null)) roomIdNumber = Integer.parseInt(roomIdString);
                    request.setAttribute("bookinglist", bookingList);

                    List<Customer> customerList = new ArrayList<Customer>();

                    Customer customer = customerDao.getByBookId(bookIdNumber);
                    customerList.add(customer);
                    request.setAttribute("customerlist", customerList);

                    List<Room> roomList = new ArrayList<>();
                    Room room = roomDao.getByPK(roomIdNumber);
                    roomList.add(room);
                    request.setAttribute("roomlist", roomList);
                     List<User> userList=new ArrayList<User>();
                    userList.add(user);
                    request.setAttribute("userlist",userList);
                    return null;
                }
            });

        } catch (DaoException e) {

            throw new ActionException("Исключение при формировании данных личного кабинета", e.getCause());

        }


        daoFactory.releaseContext();


        return accountPage;
    }


}
