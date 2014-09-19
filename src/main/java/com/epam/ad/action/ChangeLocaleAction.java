package com.epam.ad.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;


public class ChangeLocaleAction implements Action {
    @Override
    public ActionResult execute(HttpServletRequest request) throws ActionException {

        ActionResult refererPage=new ActionResult("",true);
        String localeName = request.getParameter("locale");
        Locale locale=new Locale(localeName);
        HttpSession session=request.getSession();
        session.setAttribute("fmtlocale",locale);
        request.getHeader("Referer");
        System.out.println("получили локаль с параметра"+localeName);
        System.out.println("получили локаль с сессии"+session.getAttribute("fmtlocale"));
        return refererPage;
    }
}
