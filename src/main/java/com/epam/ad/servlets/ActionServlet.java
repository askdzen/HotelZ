package com.epam.ad.servlets;

import com.epam.ad.action.Action;
import com.epam.ad.action.ActionException;
import com.epam.ad.action.ActionFactory;
import com.epam.ad.action.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ActionServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActionServlet.class);

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String actionName = request.getMethod() + request.getPathInfo();
        LOGGER.info(" Экшиннэйм приходящий в фабрику: {}", actionName);
        Action action = ActionFactory.getAction(actionName);
        if (action == null) {
            try {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "url.not.found");
            } catch (IOException e) {

                throw new ServletException(e);

            }
            LOGGER.info("Это путь контекстный в null: {}", request.getContextPath());
            return;
        }

        ActionResult result = null;
        try {
            result = action.execute(request);

        } catch (ActionException e) {
            throw new ServletException(e);
        }
        if (result.isRedirection()) {


            response.sendRedirect(request.getContextPath() + "/" + result.getView());

            LOGGER.info("Это путь контекстный в редеректе + имя странички на которую перенаправляет Экшен {},{},{}", request.getContextPath(), "/", result.getView());

            return;
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/" + result.getView() + ".jsp");


        requestDispatcher.forward(request, response);


        LOGGER.info("Путь дисптетчеру, если запрос не отправляется на экшен: {},{},{}", "/WEB-INF/", result.getView(), ".jsp");


    }
}

