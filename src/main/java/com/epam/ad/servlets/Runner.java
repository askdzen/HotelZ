package com.epam.ad.servlets;



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
    public List<UserEntity> getAllTestFind(){
        return service.getAll("ROLE","CLIENT");
    }

    public static void main(String[] args) throws Exception {
       Runner runner=new Runner();
//        runner.testSaveRecord();
//        List<UserEntity> userEntities=runner.getAllTest();
        List<UserEntity> userEntities=runner.getAllTestFind();
        for(UserEntity u : userEntities) {
            System.out.println(u);
        }
    }
}
