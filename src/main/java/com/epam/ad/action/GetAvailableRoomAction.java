package com.epam.ad.action;

import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.DaoManager;
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
    private ActionResult result;

    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        ActionResult reservation = new ActionResult("reservation", true);  // страница на которую переходят, если номер найден
        ActionResult welcome=new ActionResult("welcome"); //страница на которую переходят, если номер с указанными характеристиками в заданном диапазоне дат не найден
        HttpSession session = request.getSession();
        String date1 = request.getParameter("calendar"); //получение параметров заказа для выполнения логики поиска комнаты с указанными характеристиками в заданном диапазоне дат и последующей передачи их на страницу бронирования
        String date2 = request.getParameter("calendar2");
        String roomType = request.getParameter("roomtype");
        String roomBed = request.getParameter("roombed");
        if (roomType==null||roomBed==null){
        request.setAttribute("nullrooms","Вы не выбрали тип номера или кол-во мест!");
            return welcome;
        }
        session.setAttribute("dateFrom", date1);   // передача атрибутов на страницу бронирования
        session.setAttribute("dateTo", date2);
        session.setAttribute("type", roomType);
        session.setAttribute("singledouble", roomBed);
        request.setAttribute("registrationGood", "");
       LOGGER.info("Параметры комнаты, выбранные пользователем: {}, {}, {}, {}", date1, date2 , roomType , roomBed);
            DaoFactory daoFactory=new DaoFactory();
        DaoManager daoManager=daoFactory.createDaoManager();
        try {
            daoManager.transactionAndClose(new DaoManager.DaoCommand() {
                @Override
                public Object execute(DaoManager daoManager) throws DaoException, SQLException, ActionException {

                    List<Room> selectedRooms=daoManager.getRoomDao().getAllbyTypeAndBed(roomType,roomBed);   // получение списка всех комнат с указанными характеристиками
                    List<BookingTable> bookingRecords  = daoManager.getBookingTableDao().getByDateIntervalId(date1,date2); //получение списка всех броней в заданном диапазоне дат
                    Map<Integer, Integer> resultRooms = getFreeRoomsMap(selectedRooms, bookingRecords); //получение из списка свободных номеров и предоплат случайную(первую в списке) комнату с соответствующей предоплатой
                    for (Map.Entry<Integer, Integer> entry : resultRooms.entrySet()) {
                        LOGGER.info("Предложенная системой комната:{}, {}", entry.getKey().toString() ,resultRooms.get(entry.getKey()));
                        if ((entry.getKey()==0)){
                            request.setAttribute("nullrooms","Все комнаты в заданном диапазоне дат заняты!");
                            daoFactory.releaseContext();
                            result=welcome;
                            return result;
                        }
                        try {
                            session.setAttribute("randomRoom", daoManager.getRoomDao().getByPK(entry.getKey()));
                        } catch (DaoException e) {
                            throw new ActionException("Исключение при поиске записи по ключу в таблице Room",e.getCause());
                        }
                        session.setAttribute("prepayment", resultRooms.get(entry.getKey()));

                    }
                    result=reservation;
                    return result;
                }
            });
        } catch (DaoException e) {
            throw new ActionException("Исключение при выполнении поиска комнаты по заданным параметрам",e.getCause());
        }
        daoFactory.releaseContext();
        return result;

    }


    private Map<Integer, Integer> getFreeRoomsMap(List<Room> selectedRooms, List<BookingTable> bookingTables) {
        Map<Integer, Integer> resultRooms = new HashMap<Integer, Integer>();
        List<Room>rooms=new ArrayList<>(selectedRooms); // создание списка комнат с указанными характеристиками, для последующего удаления из него комнат в занятый диапазон дат
        for (Room room : selectedRooms){
            for (BookingTable bookingRecord : bookingTables) {
                      if (room.getId()==(bookingRecord.getRoomNo())) {   // если id комнаты равен значению одноименного поля брони из списка броней, то эту комнату удаляем из результирующего списка
                            LOGGER.info("Комната c id {} равна комнате {} в списке занятых, будет удалена",room.getId(), bookingRecord.getRoomNo());
                        rooms.remove(room);
                    }
                }
            }
        if (rooms.size()>0) {
            for (Room room1 : rooms) {
                resultRooms.put(room1.getId(), room1.getRoomRate()); // если результирующий список не пуст, то создаем список со значениями id комнаты и поля prepayment
                LOGGER.info("{} - этот номер свободен",room1.getId());
            }
            return resultRooms;
        }else {resultRooms.put(0,0);  // если результирующий список пуст, то создаем список с нулевыми значениями, для последующего вывода сообщения на той же странице
        return resultRooms;
        }
   }





}
