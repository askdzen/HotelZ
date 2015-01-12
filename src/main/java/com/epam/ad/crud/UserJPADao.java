package com.epam.ad.crud;





import com.epam.ad.entity.User;
import com.epam.ad.entity.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;


public class UserJPADao extends AbstractJPADao{
    public EntityManager em= Persistence.createEntityManagerFactory("COLIBRI").createEntityManager();

    private final String GET_ALL_QUERY ="SELECT u FROM UserEntity u";


    public UserJPADao() {
        super(UserEntity.class);

    }

    @Override
    public String getAllQuery() {
        return GET_ALL_QUERY;
    }

    @Override
    public List getAll(String column, String value) {
        String GET_ALL_QUERY_FOR_FIND ="SELECT u FROM UserEntity u AND "+column+" LIKE "+"'%"+value+"%'";
        TypedQuery<UserEntity> namedQuery = em.createQuery(GET_ALL_QUERY_FOR_FIND, UserEntity.class);
        return namedQuery.getResultList();
    }

//    public UserEntity add(UserEntity user){
//        em.getTransaction().begin();
//        UserEntity userFromDB = em.merge(user);
//        em.getTransaction().commit();
//        return userFromDB;
//    }
//
//
//    public void delete(int id){
//        em.getTransaction().begin();
//        em.remove(get(id));
//        em.getTransaction().commit();
//    }
//    public UserEntity get(int id){
//        return em.find(UserEntity.class, id);
//
//    }
//    public void update(UserEntity user){
//        em.getTransaction().begin();
//        em.merge(user);
//        em.getTransaction().commit();
//    }
//    public List<UserEntity> getAll(){
//        TypedQuery<UserEntity> namedQuery = em.createNamedQuery("UserEntity.getAll",UserEntity.class);
//        return namedQuery.getResultList();
//    }
}
