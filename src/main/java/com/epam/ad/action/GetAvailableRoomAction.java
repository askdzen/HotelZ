package com.epam.ad.action;

import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.h2.BookingTableDao;
import com.epam.ad.dao.h2.DaoFactory;
import com.epam.ad.dao.h2.RoomDao;
import com.epam.ad.entity.BookingTable;
import com.epam.ad.entity.Room;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAvailableRoomAction implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(GetAvailableRoomAction.class);

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        ActionResult customerform = new ActionResult("reservation", true);
        ActionResult welcome=new ActionResult("welcome");
        HttpSession session = request.getSession();
        String date1 = request.getParameter("calendar");
        String date2 = request.getParameter("calendar2");
        String roomType = request.getParameter("roomtype");
        String bedNo = request.getParameter("bedNo");
        if (roomType==null||bedNo==null){
        request.setAttribute("nullrooms","Вы не выбрали тип номера или кол-во мест!");
            return welcome;
        }
        session.setAttribute("dateFrom", date1);
        session.setAttribute("dateTo", date2);
        session.setAttribute("type", roomType);
        session.setAttribute("singledouble", bedNo);
        request.setAttribute("registrationGood", "");
     //   LOGGER.info(date1 + " " + date2 + " " + roomType + " " + bedNo);
            DaoFactory daoFactory=new DaoFactory();
            List<Room> roomList = getRooms(daoFactory);
            List<Room> selectedRooms = getUserSelectedRooms(roomType, bedNo, roomList);
            List<BookingTable> bookingRecords = selectRoomsByDate(date1, date2,daoFactory);
        RoomDao roomDao = null;
        try {
            roomDao = (RoomDao) daoFactory.getDao(Room.class);
        } catch (DaoException e) {
            throw new ActionException("Исключение при поиске таблицы Room",e.getCause());
        }
        Map<Integer, Integer> resultRooms = getFreeRoomsMap(selectedRooms, bookingRecords);
            for (Map.Entry<Integer, Integer> entry : resultRooms.entrySet()) {
                System.out.println(entry.getKey().toString() + " " +resultRooms.get(entry.getKey()));
                if ((entry.getKey()==0)){
                    request.setAttribute("nullrooms","Все комнаты в заданном диапазоне дат заняты!");
                    return welcome;
                }
                try {
                    session.setAttribute("randomRoom", roomDao.getByPK(entry.getKey()));
                } catch (DaoException e) {
                    throw new ActionException("Исключение при поиске записи по ключу в таблице Room",e.getCause());
                }
                session.setAttribute("prepayment", resultRooms.get(entry.getKey()));
            }
            daoFactory.releaseContext();
        return customerform;

    }


    private Map<Integer, Integer> getFreeRoomsMap(List<Room> selectedRooms, List<BookingTable> bookingTables) {
        Map<Integer, Integer> resultRooms = new HashMap<Integer, Integer>();
        List<Room>rooms=new ArrayList<>(selectedRooms);
        for (Room room : selectedRooms){
            for (BookingTable bookingRecord : bookingTables) {
                      if (room.getId()==(bookingRecord.getRoomNo())) {
                            System.out.println(room.getId() + "равно" + bookingRecord.getRoomNo());
                        rooms.remove(room);
                    }
                }
            }
        if (rooms.size()>0) {
            for (Room room1 : rooms) {
                resultRooms.put(room1.getId(), room1.getRoomRate());
                System.out.println(room1.getId());
            }
            return resultRooms;
        }else {resultRooms.put(0,0);
        return resultRooms;
        }
   }

    private List<BookingTable> selectRoomsByDate(String date1, String date2,DaoFactory daoFactory) throws ActionException {

        BookingTableDao bookingTableDao=daoFactory.createDaoManager().getBookingTableDao();
        List<BookingTable> bookingTables = null;
        try {
            bookingTables = bookingTableDao.getByDateIntervalId(date1,date2);
        } catch (DaoException e) {
            throw new ActionException("Исключение при поиске указанного интервала дат таблицы BookingTable",e.getCause());
        }
        for (BookingTable bookingTable1 : bookingTables) {
           // System.out.println(bookingTable1.getDateFrom()+" "+bookingTable1.getDateTo());
        }
        return bookingTables;
    }

    private List<Room> getUserSelectedRooms(String roomType, String bedNo, List<Room> roomList) {
        List<Room>selectedRooms = new ArrayList<Room>();
        for (Room room : roomList) {
            if (room.getRoomBed().equals(bedNo) && room.getRoomType().equals(roomType)) {
                selectedRooms.add(room);
            }
        }
        return selectedRooms;
    }

    private List<Room> getRooms(DaoFactory daoFactory) throws ActionException {

       RoomDao roomDao = daoFactory.createDaoManager().getRoomDao();
        try {
            return roomDao.getAll();
        } catch (DaoException e) {
            throw new ActionException("Исключение при поиске таблицы Room",e.getCause());
        }
    }

}
