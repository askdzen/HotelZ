package com.epam.ad.servlets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorHandlerServlet extends HttpServlet {
    public static final Logger LOGGER = LoggerFactory.getLogger(ErrorHandlerServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// Analyze the servlet exception
        Throwable throwable = (Throwable)
                request.getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer)
                request.getAttribute("javax.servlet.error.status_code");
        String servletName = (String)
                request.getAttribute("javax.servlet.error.servlet_name");
        String message = (String)
                request.getAttribute("javax.servlet.error.message");
        if (servletName == null) {
            servletName = "Unknown";
        }
        String requestUri = (String)
                request.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }
        LOGGER.info("Status code: {}; ",statusCode, message, requestUri, servletName, throwable);
        request.setAttribute("requesturi",requestUri);
        request.setAttribute("statuscode",statusCode);
        request.setAttribute("message",message);
        request.setAttribute("servletname",servletName);
        request.setAttribute("throwable",throwable);
        request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
    }

}
