package com.nc_player.servlet;

import com.nc_player.Constant;
import com.nc_player.hibernate.utils.Instance;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by INDIGO-ПС on 27.03.2016.
 */
public class RegistrationServlet extends HttpServlet{

    private static Logger logger = Logger.getLogger(RegistrationServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        resp.setHeader("Pragma", "no-cache");
        resp.setDateHeader("Expires", 0);
        if(session.getAttribute("session")!=null) {
            req.getRequestDispatcher("JSPfiles/player_page.jsp").forward(req,resp);
        }
        else {
            req.getRequestDispatcher("JSPfiles/registration_page.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if (req.getParameter(Constant.REG_REGISTRATION_BTN) != null)
        {
            if (req.getParameter(Constant.REG_NAME).isEmpty() || req.getParameter(Constant.REG_SURNAME).isEmpty()
                    || req.getParameter(Constant.REG_LOGIN).isEmpty() || req.getParameter(Constant.REG_PASSWORD).isEmpty())
            {
                //выводить сообщение об ошибке (незаполнено поле )
                req.getRequestDispatcher("JSPfiles/registration_page.jsp").forward(req,resp);
            }
            else
            {
                Instance instance = Instance.getInstance();
                long id = instance.insertUser(req.getParameter(Constant.REG_LOGIN).toLowerCase(),req.getParameter(Constant.REG_NAME),
                        req.getParameter(Constant.REG_SURNAME), DigestUtils.md5Hex(req.getParameter(Constant.REG_PASSWORD)));
                if (id == -1){
                    req.setAttribute("error", req.getParameter(Constant.REG_LOGIN).toLowerCase()+" логин существует");
                    req.getRequestDispatcher("JSPfiles/registration_page.jsp").forward(req,resp);
                }
                else {
                    logger.info("Registration: user- " + id + ". " + req.getParameter(Constant.REG_LOGIN).toLowerCase());
                    HttpSession session = req.getSession();
                    session.setAttribute("id",id);
                    session.setAttribute("session", req.getParameter(Constant.REG_LOGIN).toLowerCase());
                    resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                    resp.setHeader("Pragma", "no-cache");
                    resp.setDateHeader("Expires", 0);
                    req.getRequestDispatcher("JSPfiles/player_page.jsp").forward(req, resp);
                }
            }
        }
    }
}
