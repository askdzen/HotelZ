package com.epam.ad.crud;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Абстрактный класс предоставляющий базовую реализацию CRUD операций с использованием JPA.
 *
 * @param <T>  тип объекта персистенции
 *
 */
public class JPADao<T> implements GenericJPADao<T> {
    private final Class <T> ENTITYCLASS;

    public EntityManager em= Persistence.createEntityManagerFactory("COLIBRI").createEntityManager();

    public JPADao(Class<T> ENTITYCLASS) {
       this.ENTITYCLASS = ENTITYCLASS;
    }



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
        String ENTITY_NAME=ENTITYCLASS.getName();
        return "SELECT u FROM "+ENTITY_NAME.substring(0)+" u";
    }


    @Override
    public List<T> getAll()  {
        String getAllQuery =this.getAllQuery();
        TypedQuery<T> namedQuery = em.createQuery(getAllQuery, ENTITYCLASS);
        return namedQuery.getResultList();
    }



    public List<T> getAll(String column,String value)  {

       String GET_ALL_QUERY_FOR_FIND =getAllQuery()+" WHERE "+column+" LIKE "+"'%"+value+"%'";
        TypedQuery<T> namedQuery = em.createQuery(GET_ALL_QUERY_FOR_FIND, ENTITYCLASS);
        return namedQuery.getResultList();

    }


    public List<T> getRange(int pageNumber, int pageSize)  {

        String GET_ALL_QUERY_FOR_RANGE =getAllQuery()+" WHERE u.isdeleted=FALSE ORDER BY u.id" ;
        Query query = em.createQuery(GET_ALL_QUERY_FOR_RANGE);
        query.setFirstResult(pageNumber);
        query.setMaxResults(pageSize);

        return query.getResultList();

    }
    public List<T> getRange(int pageNumber, int pageSize,String column,String param) {

       String GET_ALL_QUERY_FOR_FIND_RANGE = getAllQuery()+" WHERE "+column+" LIKE "+"'%"+param+"%'" +" AND u.isdeleted=FALSE ORDER BY u.id";
        Query query = em.createQuery(GET_ALL_QUERY_FOR_FIND_RANGE);
        query.setFirstResult(pageNumber);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }
    public List<T> getRange(int pageNumber, int pageSize, int userid){


       String sql = getAllQuery()+" AND u.userId = " +String.valueOf(userid)+" AND u.isdeleted=FALSE ORDER BY u.id";
        Query query = em.createQuery(sql);
        query.setFirstResult(pageNumber);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }
    public List<T> getByUserId(int key)  {

       String sql = getAllQuery()+" WHERE u.userId="+key;
        TypedQuery<T> namedQuery = em.createQuery(sql, ENTITYCLASS);
        return namedQuery.getResultList();
    }



}

