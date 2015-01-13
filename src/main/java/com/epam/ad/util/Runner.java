package com.epam.ad.util;



import com.epam.ad.crud.JPADao;
import com.epam.ad.crud.UserJPADao;
import com.epam.ad.entity.UserEntity;

import javax.inject.Named;
import java.util.List;

@Named("runner")
public class Runner {
    UserJPADao service=new UserJPADao();
    public void testSaveRecord()throws Exception {
        UserEntity user=new UserEntity();
//        FacesContext facesContext=FacesContext.getCurrentInstance();
//        Object o = facesContext.getAttributes().get(user.getUsername());

        user.setLogin("TestUser");
        user.setPassword("TestPass");
        user.setRole("admin");
        user.setIsdeleted(false);
        UserEntity user1= (UserEntity) service.add(user);
        System.out.println(user1);
    }
    public void updateRecord(){

    }

    public List<UserEntity> getAllTest(){
        return service.getAll();


    }

    public List<UserEntity> getRange(){
        return service.getRange(3,3);
    }
    public List<UserEntity> getRangeForFind(){
        return service.getRange(3,3,"ROLE","CLIENT");
    }
    public List<UserEntity> getAllTestFind(){
        return service.getAll("ROLE","CLIENT");
    }

    public UserEntity getUserByUsername(){
        UserEntity userEntity=new UserEntity();
        userEntity=service.getUserByUsername("Sveta");
        return userEntity;
    }

    public static void main(String[] args) throws Exception {
       Runner runner=new Runner();
        runner.testSaveRecord();
        List<UserEntity> userEntities=runner.getAllTest();
        List<UserEntity> userEntities1=runner.getAllTestFind();
        List<UserEntity> userEntities2=runner.getRangeForFind();
        List<UserEntity>userEntities3=runner.getRange();


        for(UserEntity u : userEntities) {
            System.out.println(u);
        }
        System.out.println();
        for(UserEntity u : userEntities1) {
            System.out.println(u);
        }
        System.out.println();
        for(UserEntity u : userEntities2) {
            System.out.println(u);
        }
        System.out.println();

        for(UserEntity u : userEntities3) {
            System.out.println(u);
        }
        System.out.println();

        System.out.println(runner.getUserByUsername());

        System.out.println(runner.service.findByCredentials("Askar","1"));
    }
}
