package com.epam.ad.servlets;



import com.epam.ad.crud.UserJPAService;
import com.epam.ad.entity.User;
import com.epam.ad.entity.UserEntity;

import javax.inject.Named;

@Named("runner")
public class Runner {
    UserJPAService service=new UserJPAService();
    public void testSaveRecord()throws Exception {
        UserEntity user=new UserEntity();
//        FacesContext facesContext=FacesContext.getCurrentInstance();
//        Object o = facesContext.getAttributes().get(user.getUsername());

        user.setLogin("TestUser");
        user.setPassword("TestPass");
        user.setRole("admin");
        user.setIsdeleted(false);
        UserEntity user1=service.add(user);
        System.out.println(user1);
    }

    public static void main(String[] args) throws Exception {
       Runner runner=new Runner();
        runner.testSaveRecord();
    }
}
