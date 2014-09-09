package com.epam.ad.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * Абстрактный класс предоставляющий базовую реализацию CRUD операций с использованием JDBC.
 *
 * @param <T>  тип объекта персистенции
 *
 */
public abstract class AbstractJDBCDao<T extends Identified> implements GenericDao<T> {

    private Connection connection;

    /**
     * Возвращает sql запрос для получения всех записей.
     * <p/>
     * SELECT * FROM [Table]
     */
    public abstract String getSelectQuery();
    public abstract String getSelectQueryForRange();
    /**
     * Возвращает sql запрос для вставки новой записи в базу данных.
     * <p/>
     * INSERT INTO [Table] ([column, column, ...]) VALUES (?, ?, ...);
     */
    public abstract String getCreateQuery();


    /**
     * Возвращает sql запрос для обновления записи.
     * <p/>
     * UPDATE [Table] SET [column = ?, column = ?, ...] WHERE id = ?;
     */
    public abstract String getUpdateQuery();

    /**
     * Возвращает sql запрос для удаления записи из базы данных.
     * <p/>
     * DELETE FROM [Table] WHERE id= ?;
     */
    public abstract String getDeleteQuery();

    /**
     * Разбирает ResultSet и возвращает список объектов соответствующих содержимому ResultSet.
     */
    public abstract List<T> parseResultSet(ResultSet rs) throws DaoException;

    /**
     * Устанавливает аргументы insert запроса в соответствии со значением полей объекта object.
     */
    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws DaoException;

    /**
     * Устанавливает аргументы update запроса в соответствии со значением полей объекта object.
     */
    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws DaoException;

    @Override
    public T persist(T object) throws DaoException {
        T persistInstance;
        // Добавляем запись
        String sql = getCreateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            prepareStatementForInsert(statement, object);
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new DaoException("On persist modify more then 1 record: " + count);
            }
        } catch (Exception e) {

             throw new DaoException(e);


        }
        // Получаем только что вставленную запись
        sql = getSelectQuery() + " AND id = last_insert_id();";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            List<T> list = parseResultSet(rs);
            if ((list == null) || (list.size() != 1)) {
                throw new DaoException("Exception on findByPK new persist data.");
            }
            persistInstance = list.iterator().next();
            return persistInstance;
        } catch (Exception e) {
              throw new DaoException("Проблема при создании новой записи",e.getCause());
                    }

    }

    @Override
    public T getByPK(Integer key) throws DaoException {
        List<T> list;
        String sql = getSelectQuery();
        sql += " AND id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, key);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new DaoException(e);

        }
        if (list == null || list.size() == 0) {

                throw new DaoException("Record with PK = " + key + " not found.");

        }
        if (list.size() > 1) {

                throw new DaoException("Received more than one record.");

        }
        return list.iterator().next();
    }

    @Override
    public void update(T object) throws DaoException {
        String sql = getUpdateQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            prepareStatementForUpdate(statement, object); // заполнение аргументов запроса оставим на совесть потомков
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new DaoException("On update modify more then 1 record: " + count);
            }

        } catch (Exception e) {
             throw new DaoException(e);
       }

    }

    @Override
    public void delete(T object) throws DaoException {
        String sql = getDeleteQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            try {
                statement.setObject(1, object.getId());
            } catch (Exception e) {
                throw new DaoException(e);
            }
            int count = statement.executeUpdate();
            if (count != 1) {
                throw new DaoException("On delete modify more then 1 record: " + count);
            }
            statement.close();
        } catch (Exception e) {

                throw new DaoException(e);

        }
    }

    @Override
    public List<T> getAll() throws DaoException {
        List<T> list;
        String sql = getSelectQuery();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
            return list;

        } catch (Exception e) {

                throw new DaoException(e);

        }
    }
    public List<T> getAll(String column,String value) throws DaoException {
        List<T> list;
        String sql = getSelectQuery()+" AND "+column+" LIKE "+"'%"+value+"%'";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
            return list;

        } catch (Exception e) {

            throw new DaoException(e);

        }
    }
    public List<T> getRange(int pageNumber, int pageSize) throws DaoException {
        List<T> list;
        String sql = getSelectQueryForRange();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,String.valueOf(pageSize));
            statement.setString(2,String.valueOf((pageNumber-1)*pageSize));

            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
            return list;

        } catch (Exception e) {

                throw new DaoException(e);

        }
    }
    public List<T> getRange(int pageNumber, int pageSize,String column,String param) throws DaoException {
        List<T> list;
        String sql;
        if (column.isEmpty()||param.isEmpty()){
           sql= getSelectQueryForRange();
        }else
        sql = getSelectQuery()+" AND "+column+" LIKE "+"'%"+param+"%'" +" ORDER BY ID LIMIT ? OFFSET ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1,String.valueOf(pageSize));
            statement.setString(2,String.valueOf((pageNumber-1)*pageSize));

            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
            return list;

        } catch (Exception e) {

            throw new DaoException(e);

        }
    }
    public AbstractJDBCDao(Connection connection) {
        this.connection = connection;
    }

}

