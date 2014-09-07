package com.epam.ad.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class ShowPageAction implements Action {
    private ActionResult actionResult;

    public ShowPageAction(String page) {

        actionResult = new ActionResult(page);
    }


    @Override
    public ActionResult execute(HttpServletRequest req) {
        req.setAttribute("hidden","hidden=\"hidden\"");
            return actionResult;
       }

}
