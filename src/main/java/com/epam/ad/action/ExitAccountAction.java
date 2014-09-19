package com.epam.ad.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class ExitAccountAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {
        ActionResult login =new ActionResult("",true);
        HttpSession session=request.getSession();
        session.invalidate();
        return login;
    }
}
