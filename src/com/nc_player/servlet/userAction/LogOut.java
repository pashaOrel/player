package com.nc_player.servlet.userAction;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by INDIGO-ПС on 03.04.2016.
 */
public class LogOut extends HttpServlet {
    private static Logger logger = Logger.getLogger(LogOut.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        logger.info("Logout user - "+ session.getAttribute("id")+". "+session.getAttribute("session"));
        session.invalidate();
        req.getRequestDispatcher("JSPfiles/intro_page.jsp").forward(req,resp);
    }
}
