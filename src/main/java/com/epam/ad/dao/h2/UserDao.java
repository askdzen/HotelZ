package com.epam.ad.dao.h2;

import com.epam.ad.dao.AbstractJDBCDao;
import com.epam.ad.dao.DaoException;
import com.epam.ad.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


public class UserDao extends AbstractJDBCDao<User> {

Connection connection;
    public UserDao(Connection connection) {
        super(connection);
        this.connection=connection;
    }

    public User findByCredentials(String username, String password) throws DaoException {
        User user = getUserByUsername(username);
        if (!user.getUsername().equals(username) || !user.getPassword().equals(password)) return null;

        return user;
    }

public User getUserByUsername(String username) throws DaoException {
    List<User> list;
    String sql = getSelectQuery();
    sql += " AND LOGIN = ?";
    try (PreparedStatement statement = connection.prepareStatement(sql)) {
        statement.setString(1, username);
        ResultSet rs = statement.executeQuery();
        list = parseResultSet(rs);

        if (list == null || list.size() == 0) {

                throw new DaoException("Record with PK = " + username + " not found.");

        }
        if (list.size() > 1) {

                throw new DaoException("Received more than one record.");

        }
        return list.iterator().next();
    } catch (SQLException e) {
        throw new DaoException("Исключение при поиске пользователя по логину");
    }


}
    @Override
    public String getSelectQuery() {
        return "SELECT ID,LOGIN,PASSWORD,ROLE, ISDELETED FROM USER WHERE ISDELETED=FALSE";
    }

    @Override
    public String getSelectQueryForRange() {
        return "SELECT ID,LOGIN,PASSWORD,ROLE, ISDELETED FROM USER WHERE ISDELETED=FALSE ORDER BY ID LIMIT ? OFFSET ?;";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO USER(LOGIN,PASSWORD,ROLE, ISDELETED) \n" +
                "VALUES (?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE USER \n" +
                "SET LOGIN = ?, PASSWORD = ?, ROLE = ? , ISDELETED = ? \n" +
                "WHERE ID = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM USER WHERE ID= ?;";
    }

    @Override
    public List<User> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<User> result = new LinkedList<User>();
        try {
            while (rs.next()) {
                PersistUser persistUser = new PersistUser();
                persistUser.setId(rs.getInt("ID"));
                persistUser.setUsername(rs.getString("LOGIN"));
                persistUser.setPassword(rs.getString("PASSWORD"));
                persistUser.setRole(rs.getString("ROLE"));
                persistUser.setDeleted(rs.getBoolean("ISDELETED"));
                result.add(persistUser);
            }
        } catch (Exception e) {

                throw new DaoException("Исключение при чтении данных с таблицы User",e.getCause());

        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User object) throws DaoException {
        try {
            statement.setString(1,object.getUsername());
            statement.setString(2, object.getPassword());
            statement.setString(3, object.getRole());
            statement.setBoolean(4,object.isDeleted());


        }catch (Exception e){

            throw new DaoException("Исключение при вводе данных в таблицу User",e.getCause());

        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User object) throws DaoException {
        try {
            statement.setString(1,object.getUsername());
            statement.setString(2,object.getPassword());
            statement.setString(3, object.getRole());
            statement.setBoolean(4,object.isDeleted());
            statement.setInt(5, object.getId());

        }catch (SQLException e){

                throw new DaoException("Исключение при обновлении данных таблицы User",e.getCause());


        }
    }

    @Override
    public User create() throws DaoException {
        User user=new User();
        user.setUsername("неопред");
        user.setPassword("неопр");
        user.setRole("неопред");
        user.setDeleted(false);
        return persist(user);
    }

    public void updateRecord(String username, String password, String role, String userId) throws DaoException {
        UserDao userDao=new UserDao(connection);
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        user.setId(Integer.parseInt(userId));
        user.setDeleted(false);
        userDao.update(user);
    }

    public void createRecord(String username, String password, String role) throws DaoException {
        UserDao userDao=new UserDao(connection);
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        user.setDeleted(false);
        user.setId(userDao.create().getId());
        userDao.update(user);
    }

    private class PersistUser extends User {
        @Override
        public void setId(int id) {
            super.setId(id);
        }
    }
}
