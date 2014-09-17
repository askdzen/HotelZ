package com.epam.ad.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


public class ShowPageAction implements Action {
    private ActionResult actionResult;

    public ShowPageAction(String page) {

        actionResult = new ActionResult(page);
    }


    @Override
    public ActionResult execute(HttpServletRequest req) {
        req.setAttribute("hidden","hidden=\"hidden\"");
         HttpSession session=req.getSession();
        if ( req.getParameter("language")==null){
            if (session.getAttribute("language")==null){
                session.setAttribute("language","");
                session.setAttribute("bundlelang","i18n.message");
            }
            session.setAttribute("language",session.getAttribute("language"));
            session.setAttribute("bundlelang","i18n.message"+session.getAttribute("language"));
        }else{
            session.setAttribute("language",req.getParameter("language"));
            System.out.println(req.getParameter("language")+" from ShowPageAction req.getParameter(\"language\")");
            session.setAttribute("bundlelang","i18n.message"+req.getParameter("language"));
            Map<String,String> selected=new HashMap<>();
            selected.put("_en","selected");
            selected.put("_ru","selected");


            for (String s : selected.keySet()) {
                if (s.equals(req.getParameter("language"))){
                    req.setAttribute(s,selected.get(s));
                }
            }}
        System.out.println(session.getAttribute("language")+" from ShowPageAction");

            return actionResult;
       }

}
