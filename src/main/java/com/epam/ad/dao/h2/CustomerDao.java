package com.epam.ad.dao.h2;

import com.epam.ad.action.ActionException;
import com.epam.ad.dao.AbstractJDBCDao;
import com.epam.ad.dao.DaoException;
import com.epam.ad.dao.DaoManager;
import com.epam.ad.dao.Identified;
import com.epam.ad.dao.PersistenceAction.CustomerPersistenceAction;
import com.epam.ad.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


public class CustomerDao extends AbstractJDBCDao<Customer> {
    Connection connection;
    public CustomerDao(Connection connection) {
        super(connection);
        this.connection=connection;
    }

    @Override
    public String getSelectQuery() {
        return "SELECT ID, NAME, LAST_NAME, CITY, REGION, COUNTRY, PASSPORT, PHONE, EMAIL,  PREPAYMENT, BOOK_ID, USER_ID, ISDELETED FROM CUSTDETAIL WHERE ISDELETED=FALSE";
    }

    @Override
    public String getSelectQueryForRange() {
        return "SELECT ID, NAME, LAST_NAME, CITY, REGION, COUNTRY, PASSPORT, PHONE, EMAIL,  PREPAYMENT, BOOK_ID, USER_ID, ISDELETED FROM CUSTDETAIL WHERE ISDELETED=FALSE  ORDER BY ID LIMIT ? OFFSET ?;";
    }

    @Override
    public String getCreateQuery() {
        return "INSERT INTO  CUSTDETAIL(NAME, LAST_NAME, CITY, REGION, COUNTRY, PASSPORT, PHONE, EMAIL,  PREPAYMENT, BOOK_ID, USER_ID, ISDELETED) \n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    }

    @Override
    public String getUpdateQuery() {
        return "UPDATE  CUSTDETAIL \n" +
                "SET NAME = ?, LAST_NAME = ?, CITY = ?, REGION = ?, COUNTRY= ?, PASSPORT= ?, PHONE= ?, EMAIL= ?,  PREPAYMENT= ?, BOOK_ID= ?, USER_ID= ?, ISDELETED=? \n" +
                "WHERE ID = ?;";
    }

    @Override
    public String getDeleteQuery() {
        return "DELETE FROM CUSTDETAIL WHERE ID= ?;";
    }

    @Override
    public List<Customer> parseResultSet(ResultSet rs) throws DaoException {
        LinkedList<Customer> result = new LinkedList<Customer>();

        try {
            while (rs.next()) {
                PersistCustomer customer = new PersistCustomer();
                customer.setId(rs.getInt("ID"));
                customer.setFirstName(rs.getString("NAME"));
                customer.setLastName(rs.getString("LAST_NAME"));
                customer.setCity(rs.getString("CITY"));
                customer.setRegion(rs.getString("REGION"));
                customer.setCountry(rs.getString("COUNTRY"));
                customer.setPassport(rs.getString("PASSPORT"));
                customer.setPhone(rs.getString("PHONE"));
                customer.setEmail(rs.getString("EMAIL"));
                customer.setPrepayment(rs.getInt("PREPAYMENT"));
                customer.setBookId(rs.getInt("BOOK_ID"));
                customer.setUserId(rs.getInt("USER_ID"));
                customer.setIsDeleted(rs.getBoolean("ISDELETED"));
                result.add(customer);
            }
        } catch (SQLException e) {
            throw new DaoException("Исключение при получении данных из таблицы Customer",e.getCause());
        }

        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Customer object) throws DaoException {
        try {
            int bookId;
            int userId;
            if (object.getUserId() == null) userId = 1;
            else userId = object.getUserId();
            if (object.getBookId() == null) bookId = 1;
            else bookId = object.getBookId();
            statement.setString(1, object.getFirstName());
            statement.setString(2, object.getLastName());
            statement.setString(3, object.getCity());
            statement.setString(4, object.getRegion());
            statement.setString(5, object.getCountry());
            statement.setString(6, object.getPassport());
            statement.setString(7, object.getPhone());
            statement.setString(8, object.getEmail());
            statement.setInt(9, object.getPrepayment());
            statement.setInt(10, bookId);
            statement.setInt(11, userId);
            statement.setBoolean(12, object.isDeleted());
        } catch (SQLException e) {
            throw new DaoException("Исключение при вводе данных в таблицу Customer",e.getCause());
        }
    }
        @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Customer object) throws DaoException {

            try {
                statement.setString(1, object.getFirstName());
                statement.setString(2, object.getLastName());
                statement.setString(3, object.getCity());
                statement.setString(4, object.getRegion());
                statement.setString(5, object.getCountry());
                statement.setString(6, object.getPassport());
                statement.setString(7, object.getPhone());
                statement.setString(8, object.getEmail());
                statement.setInt(9, object.getPrepayment());
                statement.setInt(10, object.getBookId());
                statement.setInt(11, object.getUserId());
                statement.setBoolean(12,object.isDeleted());
                statement.setInt(13, object.getId());
            } catch (SQLException e) {
                throw new DaoException("Исключение при обновлении данных таблицы Customer",e.getCause());
            }

    }

    @Override
    public Customer create() throws DaoException {
        Customer customer = new Customer();

        return persist(customer);
    }

    public Customer getByBookId(int bookId) throws DaoException {
        List<Customer> list;
        String sql = getSelectQuery();
        sql += " AND BOOK_ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, String.valueOf(bookId));
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {

                throw new DaoException("Исключение при поиске записи по ключу BookId в таблице Customer",e);

        }
        if (list == null || list.size() == 0) {

                throw new DaoException("Record with PK = " + bookId + " not found.");

        }
        if (list.size() > 1) {

                throw new DaoException("Received more than one record.");

        }
        return list.iterator().next();

    }

    private class PersistCustomer extends Customer {
        @Override
        public void setId(int id) {
            super.setId(id);
        }
    }


    public void update(DaoManager daoManager, String inputFirstName, String inputLastName, String inputCity, String inputRegion, String inputCountry, String inputPassport, String inputPhone, String inputEmail, int userId, int bookId, int prepayment, int id) throws  DaoException {
        Customer customer=new Customer();
        customer.setFirstName(inputFirstName);
        customer.setLastName(inputLastName);
        customer.setCity(inputCity);
        customer.setRegion(inputRegion);
        customer.setCountry(inputCountry);
        customer.setPassport(inputPassport);
        customer.setPhone(inputPhone);
        customer.setEmail(inputEmail);
        customer.setPrepayment(prepayment);
        customer.setBookId(bookId);
        customer.setUserId(userId);
        customer.setId(id);

        CustomerPersistenceAction persistenceAction=new CustomerPersistenceAction(daoManager,customer);

        try {
            persistenceAction.doUpdateAction();

        } catch (Exception e) {
            throw new DaoException("Исключение при обновлении записи таблицы Customer",e.getCause());
        }
    }
    public void create(DaoManager daoManager, String inputFirstName, String inputLastName, String inputCity, String inputRegion, String inputCountry, String inputPassport, String inputPhone, String inputEmail, int bookId, int userId, int prepayment) throws  DaoException {
        Customer customer=new Customer();
        customer.setFirstName(inputFirstName);
        customer.setLastName(inputLastName);
        customer.setCity(inputCity);
        customer.setRegion(inputRegion);
        customer.setCountry(inputCountry);
        customer.setPassport(inputPassport);
        customer.setPhone(inputPhone);
        customer.setEmail(inputEmail);
        customer.setPrepayment(prepayment);
        customer.setBookId(bookId);
        customer.setUserId(userId);
        CustomerPersistenceAction persistenceAction=new CustomerPersistenceAction(daoManager,customer);

        try {
            persistenceAction.doCreateAction();
        } catch (DaoException e) {
            throw new DaoException("Исключение при создании записи таблицы Customer",e.getCause());
        }
    }
    public Customer create(String inputFirstName, String inputLastName, String inputCity, String inputRegion, String inputCountry, String inputPassport, String inputPhone, String inputEmail, int prepayment, int userId) {
        Customer customer=new Customer();
        customer.setFirstName(inputFirstName);
        customer.setLastName(inputLastName);
        customer.setCity(inputCity);
        customer.setRegion(inputRegion);
        customer.setCountry(inputCountry);
        customer.setPassport(inputPassport);
        customer.setPhone(inputPhone);
        customer.setEmail(inputEmail);
        customer.setPrepayment(prepayment);
        customer.setUserId(userId);
        return customer;
    }
}
