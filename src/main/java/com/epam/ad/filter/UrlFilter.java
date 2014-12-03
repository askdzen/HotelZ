package com.epam.ad.filter;

import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UrlFilter implements Filter {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(UrlFilter.class);

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        doFilter((HttpServletRequest) req, (HttpServletResponse) resp, chain);
    }

    private void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {

        String pathInfo = req.getRequestURI().substring(req.getContextPath().length());
        LOGGER.info(pathInfo + ": way of coming to the URL filter");
        if (pathInfo.startsWith("/static/") || pathInfo.startsWith("/webjars/")) {
            chain.doFilter(req, resp);
            return;
        }

        req.getRequestDispatcher("/do" + pathInfo).forward(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
