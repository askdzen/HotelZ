package com.epam.ad.action;

import com.epam.ad.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


public class ShowLoginPageAction implements Action {
    public static final String USER = "user";
    private ActionResult login = new ActionResult("login");
    private ActionResult welcome = new ActionResult("welcome", true);


    @Override
    public ActionResult execute(HttpServletRequest req) {
        req.setAttribute("hidden", "hidden=\"hidden\"");
        HttpSession session = req.getSession();

//        if (session.getAttribute("language") == null||req.getParameter("language") == null) {
//            session.setAttribute("language", "");
//            session.setAttribute("bundlelang", "i18n.message");
//        } else {
//
//            session.setAttribute("language", req.getParameter("language"));
//            session.setAttribute("bundlelang", "i18n.message" + req.getParameter("language"));
//            Map<String, String> selected = new HashMap<>();
//            selected.put("_en", "selected");
//            selected.put("_ru", "selected");
//
//
//            for (String s : selected.keySet()) {
//                if (s.equals(req.getParameter("language"))) {
//                    req.setAttribute(s, selected.get(s));
//                }
//            }
//        }
//        System.out.println(session.getAttribute("language"));
//        System.out.println(req.getParameter("language"));
        User user = (User) session.getAttribute(USER);
        if (user != null) return welcome;
        return login;
    }
}
