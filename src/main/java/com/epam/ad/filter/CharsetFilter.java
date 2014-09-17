package com.epam.ad.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Askar on 15.08.2014.
 */
public class CharsetFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        doFilter((HttpServletRequest) req, (HttpServletResponse) resp, chain);

    }
    private void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
//        HttpSession session = req.getSession();
//        if ( session.getAttribute("language")==null){session.setAttribute("language","");
//            session.setAttribute("bundlelang","i18n.message");
//        }else {
////        System.out.println(session.getAttribute("language"));
//            session.setAttribute("language",req.getParameter("language"));
//            session.setAttribute("bundlelang","i18n.message"+req.getParameter("language"));
//            session.setAttribute("language",req.getParameter("language"));
//            session.setAttribute("bundlelang","i18n.message"+req.getParameter("language"));
//            Map<String,String> selected=new HashMap<>();
//            selected.put("en","selected");
//            selected.put("ru","selected");
//
//
//            for (String s : selected.keySet()) {
//                if (s.equals(req.getParameter("language"))){
//                    req.setAttribute(s,selected.get(s));
//                }
//            }}
        chain.doFilter(req, resp);
    }
    public void init(FilterConfig config) throws ServletException {

    }

}
