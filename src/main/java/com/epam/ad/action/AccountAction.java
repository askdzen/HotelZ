package com.epam.ad.action;

import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.DaoManager;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AccountAction implements Action {


    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        DaoFactory daoFactory = new DaoFactory();        //создаем ДАОфабрику и ДАОменеджер
        DaoManager daoManager = daoFactory.createDaoManager();
        ActionResult accountPage = new ActionResult("account");    //переменная с именем возвращаемой страницы
        HttpSession session = request.getSession();
//        session.setAttribute("hidden","hidden=\"hidden\"");
        User user = (User) session.getAttribute("user");
        try {
            BookingTableDao bookingTableDao = daoManager.getBookingTableDao(); //получение ДАО трех таблиц для отображения выбранных записей по ID пользователя
            CustomerDao customerDao = daoManager.getCustomerDao();
            RoomDao roomDao = daoManager.getRoomDao();
            daoManager.transactionAndClose(new DaoManager.DaoCommand() {
                @Override                                                //открытие и закрытие транзакции
                public Object execute(DaoManager daoManager) throws DaoException, SQLException {
                    List<BookingTable> bookingList = bookingTableDao.getByUserId(user.getId());
                    int bookIdNumber;
                    int roomIdNumber;
                    bookIdNumber = bookingList.get(0).getId(); //первое значение ID записи брони (при открытии страницы)
                    roomIdNumber = bookingList.get(0).getRoomNo();//первое значение ID комнаты (при открытии страницы)
                    String bookIdString = request.getParameter("bookid"); // получение параметра запроса ID записи брони
                    String roomIdString = request.getParameter("roomid");
                    if (!(bookIdString == null)) bookIdNumber = Integer.parseInt(bookIdString);
                    if (!(roomIdString == null)) roomIdNumber = Integer.parseInt(roomIdString);
                    request.setAttribute("bookinglist", bookingList);

                    List<Customer> customerList = new ArrayList<Customer>();

                    Customer customer = customerDao.getByBookId(bookIdNumber); // получение данных клиента  из таблицы клиентов по значению поля bookId
                    customerList.add(customer); //создание списка с клиентом для передачи его странице jsp
                    request.setAttribute("customerlist", customerList); //установка атрибута "список с клиентом"

                    List<Room> roomList = new ArrayList<>();
                    Room room = roomDao.getByPK(roomIdNumber); // получение данных клиента из таблицы комнаты по значению поля Id
                    roomList.add(room);
                    request.setAttribute("roomlist", roomList);
                    List<User> userList = new ArrayList<User>();
                    userList.add(user); //создание списка с пользователем, бронирующим заказ для передачи его странице jsp
                    request.setAttribute("userlist", userList);
                    return null;
                }
            });

        } catch (DaoException e) {

            throw new ActionException("Исключение при формировании данных личного кабинета", e.getCause());

        }


        daoFactory.releaseContext(); // освобождаем Connection отдавая его обратно в пул


        return accountPage;
    }


}
