package com.epam.ad.servlets;



import com.epam.ad.crud.UserJPAService;
import com.epam.ad.entity.User;

import javax.inject.Named;

@Named("runner")
public class Runner {
    UserJPAService service=new UserJPAService();
    public void testSaveRecord()throws Exception {
        User user=new User();
//        FacesContext facesContext=FacesContext.getCurrentInstance();
//        Object o = facesContext.getAttributes().get(user.getUsername());

        user.setUsername("TestUser");
        user.setPassword("TestPass");
        user.setRole("admin");
        user.setDeleted(false);
        User user1=service.add(user);
        System.out.println(user1);
    }

    public static void main(String[] args) throws Exception {
       Runner runner=new Runner();
        runner.testSaveRecord();
    }
}
