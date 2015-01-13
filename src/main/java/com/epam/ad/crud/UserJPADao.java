package com.epam.ad.crud;

import com.epam.ad.entity.UserEntity;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Admin on 1/13/2015.
 */
public class UserJPADao extends JPADao {

    public UserJPADao() {
       super(UserEntity.class);
    }
    public UserEntity findByCredentials(String username, String password) {
        UserEntity user = getUserByUsername(username);
        if (!user.getLogin().equals(username) || !user.getPassword().equals(password)) return null;

        return user;
    }

    public UserEntity getUserByUsername(String username)  {


        String GET_ALL_QUERY_FOR_FIND ="SELECT u FROM UserEntity u WHERE u.login LIKE "+"'"+username+"'";
        TypedQuery<UserEntity> namedQuery = em.createQuery(GET_ALL_QUERY_FOR_FIND,UserEntity.class);
        return namedQuery.getResultList().get(0);

    }

}
