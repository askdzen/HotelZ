package com.epam.ad.dao.h2;

import com.epam.ad.dao.AbstractJDBCDao;
import com.epam.ad.dao.DaoException;
import com.epam.ad.entity.BookingTable;


import java.sql.*;
import java.util.LinkedList;
import java.util.List;


public class BookingTableDao extends AbstractJDBCDao<BookingTable>{
  Connection connection;
    private class PersistBookingTable extends BookingTable{
        @Override
        public void setId(int id) {
            super.setId(id);
        }
    }

    @Override
    public String getSelectQuery() {
        return "SELECT ID, DATE_FRO, DATE_TO, NO_OF_DAY, ROOM_NO, USER_ID, CONFIRMED, CONFIRM FROM BOOKINGTABLE";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO BOOKINGTABLE(DATE_FRO, DATE_TO, NO_OF_DAY, ROOM_NO, USER_ID, CONFIRMED, CONFIRM) \n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE BOOKINGTABLE \n" +
                "SET DATE_FRO = ?, DATE_TO = ?, NO_OF_DAY = ?, ROOM_NO = ?, USER_ID=?, CONFIRMED=?, CONFIRM=? \n" +
                "WHERE ID = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM BOOKINGTABLE WHERE ID= ?;";
    }

    @Override
    public List<BookingTable> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<BookingTable> result = new LinkedList<BookingTable>();

        try {
            while (rs.next()) {
                PersistBookingTable bookingTable = new PersistBookingTable();
                bookingTable.setId(rs.getInt("ID"));
                bookingTable.setDateFrom(rs.getDate("DATE_FRO"));
                bookingTable.setDateTo(rs.getDate("DATE_TO"));
                bookingTable.setDayCount(rs.getInt("NO_OF_DAY"));
                bookingTable.setRoomNo(rs.getInt("ROOM_NO"));
                bookingTable.setUserId(rs.getInt("USER_ID"));
                bookingTable.setConfirmed(rs.getBoolean("CONFIRMED"));
                bookingTable.setConfirm((BookingTable.Confirm) rs.getObject("CONFIRM"));
        //        bookingTable.setDelete(rs.getBoolean("DEL"));
                result.add(bookingTable);

            }
        } catch (SQLException e) {
           throw  new DaoException("Проблема при получении данных из таблицы BookingTable",e.getCause());
        }

        return result;
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, BookingTable object) throws DaoException {
        try {
            Date sqlDateFrom = convert(object.getDateFrom());
            Date sqlDateTo = convert(object.getDateTo());

            statement.setDate(1, sqlDateFrom);
            statement.setDate(2, sqlDateTo);
            statement.setInt(3, object.getDayCount());
            statement.setInt(4, object.getRoomNo());
            statement.setInt(5, object.getUserId());
            statement.setBoolean(6, object.isConfirmed());
            statement.setObject(7, object.getConfirm());
   //         statement.setBoolean(8,object.isDelete());
            statement.setInt(8, object.getId());

        } catch (SQLException e) {
           throw  new DaoException("Исключение при обновлении таблицы BookingTable",e.getCause());
        }
    }
        @Override
    protected void prepareStatementForInsert(PreparedStatement statement, BookingTable object) throws DaoException {
try {
    Date sqlDateFrom=convert(object.getDateFrom());
    Date sqlDateTo=convert(object.getDateTo());
    int roomNo;
    int userId;
    if (object.getRoomNo() == null) roomNo = 1;
    else roomNo = object.getRoomNo();
    if (object.getUserId() == null) userId = 1;
    else userId = object.getUserId();
    statement.setDate(1, sqlDateFrom);
    statement.setDate(2,sqlDateTo);
    statement.setInt(3,object.getDayCount());
    statement.setInt(4,roomNo);
    statement.setInt(5,userId);
    statement.setBoolean(6,object.isConfirmed());
    statement.setObject(7,object.getConfirm());
  //  statement.setBoolean(8,object.isDelete());

} catch (SQLException e) {

        throw new DaoException("Ошибка при создании новой строки!",e.getCause());

}
    }

    public BookingTableDao(Connection connection) {
        super(connection);
        this.connection=connection;

    }

    @Override
    public BookingTable create() throws DaoException {
        BookingTable bookingTable=new BookingTable();

        return persist(bookingTable);
    }
    protected java.sql.Date convert(java.util.Date date) {
        if (date == null) {
            return null;
        }
        return new java.sql.Date(date.getTime());
    }
    @Override
    public String getSelectQueryForRange() {
        //todo PreparedStatement
        return "SELECT ID, DATE_FRO, DATE_TO, NO_OF_DAY, ROOM_NO, USER_ID, CONFIRMED, CONFIRM FROM BOOKINGTABLE ORDER BY ID LIMIT ? OFFSET ?;";
    }
    public List<BookingTable> getByUserId(int key) throws DaoException {
            List<BookingTable> list;
            String sql = getSelectQuery();
            sql += " WHERE USER_ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, key);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
            if (list == null || list.size() == 0) {
               throw  new DaoException("У Вас еще не было ни одного заказа! " + key + " not found.");
            }
            return list;
              } catch (SQLException e) {
            throw new DaoException("Исключение при поиске записи по ключу UserID в таблице BookingTable",e.getCause());
        }
    }

    public BookingTable getRecordByUserId(int key) throws DaoException {
        List<BookingTable> list;
        String sql = getSelectQuery();
        sql += " WHERE USER_ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, key);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
            if (list == null || list.size() == 0) {
                throw new DaoException("Record with PK = " + key + " not found.");
            }
           if (list.size() > 1) {
                throw new DaoException("Received more than one record.");
            }
            return list.iterator().next();

        } catch (SQLException  e) {

            throw new DaoException("Исключение при поиске записи по UserId ",e.getCause());
        }
    }
    public List<BookingTable> getByDateIntervalId(String dateFrom, String dateTo) throws DaoException {
        List<BookingTable> list;
        String sql = getSelectQuery();
        sql += " WHERE DATE_FRO >= ? AND DATE_TO <= ? OR DATE_FRO <= ? AND DATE_TO >= ? OR DATE_FRO >= ? AND DATE_FRO <= ? OR DATE_TO >=? AND DATE_TO <=? ";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, dateFrom);
            statement.setString(2, dateTo);
            statement.setString(3,dateFrom);
            statement.setString(4,dateTo);
            statement.setString(5,dateFrom);
            statement.setString(6,dateTo);
            statement.setString(7,dateFrom);
            statement.setString(8,dateTo);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
            return list;
        } catch (SQLException e) {

            throw new DaoException("Исключение при создании списка дат в указанном интервале",e.getCause());
        }
    }
    public BookingTable createBookingTableRecord(String dateFrom, String dateTo, String roomId, int userId, boolean confirmed, BookingTable.Confirm confirm) throws DaoException {
        BookingTableDao bookingTableDao = new BookingTableDao(connection);
        BookingTable bookingTable=new BookingTable();
        Date dateFromSql = Date.valueOf(dateFrom);
        Date dateToSql = Date.valueOf(dateTo);
        bookingTable.setDateFrom(dateFromSql);
        bookingTable.setDateTo(dateToSql);
        bookingTable.setDayCount((int) (dateToSql.getTime() - dateFromSql.getTime()) / (24 * 60 * 60 * 1000));
        bookingTable.setRoomNo(Integer.valueOf(roomId));
        bookingTable.setUserId(userId);
        bookingTable.setConfirmed(confirmed);
        bookingTable.setConfirm(confirm);
      //  bookingTable.setDelete(false);
        bookingTable.setId(bookingTableDao.create().getId());
        bookingTableDao.update(bookingTable);
     return bookingTable;
    }
}
