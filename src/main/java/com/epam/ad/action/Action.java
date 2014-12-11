package com.epam.ad.action;

import com.epam.ad.dao.AbstractJDBCDao;
import com.epam.ad.dao.DaoException;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public interface Action {

    ActionResult execute(HttpServletRequest request) throws ActionException;

}
