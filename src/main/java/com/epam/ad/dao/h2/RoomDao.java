package com.epam.ad.dao.h2;

import com.epam.ad.dao.AbstractJDBCDao;
import com.epam.ad.dao.DaoException;
import com.epam.ad.entity.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Askar on 06.08.2014.
 */
public class RoomDao extends AbstractJDBCDao<Room>{

Connection connection;


    private class PersistRoom extends Room {
        @Override
        public void setId(int ID) {
            super.setId(ID);
        }
    }

    @Override
    public String getSelectQuery() {
        return "SELECT ID, ROOM_TYPE, ROOM_RATE, ROOM_BED, ROOM_NO FROM ROOMDETAIL";
    }

    @Override
    public String getSelectQueryForRange() {
        return  "SELECT ID, ROOM_TYPE, ROOM_RATE, ROOM_BED, ROOM_NO FROM ROOMDETAIL ORDER BY ID LIMIT ? OFFSET ?;";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO ROOMDETAIL(ROOM_TYPE, ROOM_RATE, ROOM_BED, ROOM_NO) \n" +
                "VALUES (?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE ROOMDETAIL \n" +
                "SET ROOM_TYPE = ?, ROOM_RATE = ?, ROOM_BED = ?, ROOM_NO = ? \n" +
                "WHERE ID = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM ROOMDETAIL WHERE ID= ?;";
    }

    @Override
    public List<Room> parseResultSet(ResultSet rs)  {
        LinkedList<Room> result = new LinkedList<Room>();
        try {
            while (rs.next()) {
                PersistRoom persistRoom = new PersistRoom();
               persistRoom.setId(rs.getInt("ID"));
                persistRoom.setRoomType(rs.getString("ROOM_TYPE"));
                persistRoom.setRoomRate(rs.getInt("ROOM_RATE"));
                persistRoom.setRoomBed(rs.getString("ROOM_BED"));
                persistRoom.setRoomNo(rs.getInt("ROOM_NO"));
                result.add(persistRoom);
            }
        } catch (Exception e) {
            try {
                throw new DaoException(e);
            } catch (DaoException e1) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Room object) {
try {
    statement.setString(1,object.getRoomType());
    statement.setInt(2,object.getRoomRate());
    statement.setString(3, object.getRoomBed());
    statement.setInt(4,object.getRoomNo());


}catch (Exception e){
    try {
        throw new DaoException(e);
    } catch (DaoException e1) {
        throw new RuntimeException(e);
    }
}
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Room object) {
        try {

            statement.setString(1,object.getRoomType());
            statement.setInt(2,object.getRoomRate());
            statement.setString(3,object.getRoomBed());
            statement.setInt(4,object.getRoomNo());
            statement.setInt(5,object.getId());

        }catch (Exception e){
            try {
                throw new DaoException(e);
            } catch (DaoException e1) {
                throw new RuntimeException(e);
            }
        }

    }

    public RoomDao(Connection connection) {
        super(connection);
        this.connection=connection;
    }

    @Override
    public Room create() throws DaoException {
        Room room=new Room();
        room.setRoomType("неопред");
        room.setRoomBed("неопр");
        room.setRoomNo(0);
        room.setRoomRate(0);
         return persist(room);
    }

    public void createRecord(String roomNo, String roomtype, String bedtype, String tarif) throws  DaoException {
RoomDao roomDao=new RoomDao(connection);
        Room room = new Room();
        room.setRoomNo(Integer.parseInt(roomNo));
        room.setRoomType(roomtype);
        room.setRoomBed(bedtype);
        room.setRoomRate(Integer.parseInt(tarif));
        room.setId(roomDao.create().getId());
        roomDao.update(room);
    }
    public void updateRecord(String roomNo, String roomType, String bedType, String tarif, String roomId) throws DaoException {
       RoomDao roomDao = new RoomDao(connection);
       int roomNoInt=Integer.parseInt(roomNo);
       int tarifInt=Integer.parseInt(tarif);
       int id=Integer.parseInt(roomId);
        Room room=new Room();
        room.setRoomNo(roomNoInt);
        room.setRoomType(roomType);
        room.setRoomBed(bedType);
        room.setRoomRate(tarifInt);
        room.setId(id);
        roomDao.update(room);
    }

}
