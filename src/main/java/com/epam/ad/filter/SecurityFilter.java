package com.epam.ad.filter;

import com.epam.ad.action.ActionException;
import com.epam.ad.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class SecurityFilter implements Filter {

    Map<String, String> pageRoles=new HashMap<>();


    public void destroy() {
    }

    public void init(FilterConfig config) throws ServletException {
        pageRoles.put("/bookingtable", "ADMIN");
        pageRoles.put("/customer", "ADMIN");
        pageRoles.put("/room", "ADMIN");
        pageRoles.put("/user", "ADMIN");
        pageRoles.put("/admin", "ADMIN");
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        try {
            doFilter0((HttpServletRequest) req, (HttpServletResponse) resp, chain);
        } catch (ActionException e) {
            throw new ServletException(e);
        }
    }

    private void doFilter0(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException, ActionException {
        HttpSession session = req.getSession();
        String pathInfo = req.getPathInfo();
        User user = (User) session.getAttribute("user");
        String roleName=null;
        for (Map.Entry<String,String> e : pageRoles.entrySet()) {
            if (pathInfo.startsWith(e.getKey())){
                roleName=e.getValue();
                break;
            }

        }
        if (roleName!=null&&(!user.getRole().equals(roleName))){
            resp.sendError(HttpServletResponse.SC_FORBIDDEN,"У вас не достаточно прав");
        }
        chain.doFilter(req, resp);


    }

}
