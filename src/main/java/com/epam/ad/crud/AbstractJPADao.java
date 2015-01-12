package com.epam.ad.crud;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Абстрактный класс предоставляющий базовую реализацию CRUD операций с использованием JPA.
 *
 * @param <T>  тип объекта персистенции
 *
 */
public abstract class AbstractJPADao<T> implements GenericJPADao<T> {
    private final Class <T> ENTITYCLASS;
    public AbstractJPADao(Class<T> ENTITYCLASS) {
       this.ENTITYCLASS = ENTITYCLASS;
    }

    public EntityManager em= Persistence.createEntityManagerFactory("COLIBRI").createEntityManager();




    @Override
    public T add(T entity) {
        em.getTransaction().begin();
        T obFromDB = em.merge(entity);
        em.getTransaction().commit();
        return obFromDB;
    }

    @Override
    public T getByPK(int key)  {
        return em.find(ENTITYCLASS, key);
    }

    @Override
    public void update(T entity) {
        em.getTransaction().begin();
        em.merge(entity);
        em.getTransaction().commit();

    }

    @Override
    public void delete(int id) {
        em.getTransaction().begin();
        em.remove(getByPK(id));
        em.getTransaction().commit();
    }

    public String getAllQuery(){
        return null;
    }
    public String getAllQueryForFind(){
        return null;
    }

    @Override
    public List<T> getAll()  {
        String getAllQuery =this.getAllQuery();
        TypedQuery<T> namedQuery = em.createQuery(getAllQuery, ENTITYCLASS);
        return namedQuery.getResultList();
    }



    public List<T> getAll(String column,String value)  {

        String getAllQueryForFind=this.getAllQueryForFind();
        TypedQuery<T> namedQuery = em.createQuery(getAllQueryForFind, ENTITYCLASS);
        return namedQuery.getResultList();

    }


//    public List<T> getRange(int pageNumber, int pageSize) throws DaoException {
//        List<T> list;
//        String sql = getSelectQueryForRange();
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setString(1,String.valueOf(pageSize));
//            statement.setString(2,String.valueOf((pageNumber-1)*pageSize));
//
//            ResultSet rs = statement.executeQuery();
//            list = parseResultSet(rs);
//            return list;
//
//        } catch (Exception e) {
//
//                throw new DaoException(e);
//
//        }
//        TypedQuery<T> getRangeQuery = em.createNamedQuery("SELECT u FROM "+ENTITYCLASS + " u AND ")
//
//    }
//    public List<T> getRange(int pageNumber, int pageSize,String column,String param) throws DaoException {
//        List<T> list;
//        String sql;
//        if (column.isEmpty()||param.isEmpty()){
//           sql= getSelectQueryForRange();
//        }else
//        sql = getSelectQuery()+" AND "+column+" LIKE "+"'%"+param+"%'" +" ORDER BY ID LIMIT ? OFFSET ?;";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//
//            statement.setString(1,String.valueOf(pageSize));
//            statement.setString(2,String.valueOf((pageNumber-1)*pageSize));
//
//            ResultSet rs = statement.executeQuery();
//            list = parseResultSet(rs);
//            return list;
//
//        } catch (Exception e) {
//
//            throw new DaoException(e);
//
//        }
//    }
//    public List<T> getRange(int pageNumber, int pageSize, int userid)throws  DaoException{
//        List<T> list;
//
//       String sql = getSelectQuery()+" AND USER_ID = " +String.valueOf(userid)+" ORDER BY ID LIMIT ? OFFSET ?;";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setString(1,String.valueOf(pageSize));
//            statement.setString(2,String.valueOf((pageNumber-1)*pageSize));
//
//            ResultSet rs = statement.executeQuery();
//            list = parseResultSet(rs);
//            return list;
//
//        } catch (Exception e) {
//
//            throw new DaoException(e);
//
//        }
//    }
//    public List<T> getByUserId(int key) throws DaoException {
//        List<T> list;
//        String sql = getSelectQuery();
//        sql += " AND USER_ID = ?";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setInt(1, key);
//            ResultSet rs = statement.executeQuery();
//            list = parseResultSet(rs);
//            if (list == null || list.size() == 0) {
//                throw  new DaoException("У Вас еще не было ни одного заказа! " + key + " not found.");
//            }
//            return list;
//        } catch (Exception e) {
//            throw new DaoException("Исключение при поиске записи по ключу UserID в таблице BookingTable",e.getCause());
//        }
//    }



}

