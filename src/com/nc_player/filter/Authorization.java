package com.nc_player.filter;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import com.nc_player.Constant;
import com.nc_player.RSS.RSSLoadTask;
import com.nc_player.hibernate.utils.Instance;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * Created by INDIGO-ПС on 03.04.2016.
 */
public class Authorization implements Filter {

    private static Logger logger = Logger.getLogger(Authorization.class);

    public void init(FilterConfig filterConfig) throws ServletException {
        //  new RSSLoadTask().task();
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        request.setCharacterEncoding("UTF-8");
        if (request.getParameter(Constant.AUT_LOGIN_BTN) != null || request.getParameter(Constant.AUT_REGISTRATION_BTN) != null) {
            if (request.getParameter(Constant.AUT_LOGIN_BTN) != null) {
                /*HttpSession session = request.getSession();
                session.setAttribute("id", 1);
                session.setAttribute("session", request.getParameter(Constant.AUT_LOGIN).toLowerCase());
                response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                response.setHeader("Pragma", "no-cache");
                response.setDateHeader("Expires", 0);
                request.getRequestDispatcher("JSPfiles/player_page.jsp").forward(request, response);*/
//                Instance instance = new Instance();
//                long id = instance.validate(request.getParameter(Constant.AUT_LOGIN).toLowerCase(), DigestUtils.md5Hex(request.getParameter(Constant.AUT_PASSWORD)));
//                switch ((int) id) {
//                    case -1:
//                        logger.info("Authorization: " + request.getParameter(Constant.AUT_LOGIN).toLowerCase() + " invalid password");
//                        request.setAttribute("error", "Неверный пароль");
//                        request.getRequestDispatcher("JSPfiles/intro_page.jsp").forward(request, response);
//                        break;
//                    case -2:
//                        logger.info("Authorization: " + request.getParameter(Constant.AUT_LOGIN).toLowerCase() + " user doesn't exist");
//                        request.setAttribute("error", "Пользователь не существует");
//                        request.getRequestDispatcher("JSPfiles/intro_page.jsp").forward(request, response);
//                        break;
//                    default:
//                        logger.info("Authorization: user- " + id + ". " + request.getParameter(Constant.AUT_LOGIN).toLowerCase());
//                        HttpSession session = request.getSession();
//                        session.setAttribute("id", id);
//                        session.setAttribute("session", request.getParameter(Constant.AUT_LOGIN).toLowerCase());
//                        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
//                        response.setHeader("Pragma", "no-cache");
//                        response.setDateHeader("Expires", 0);
//                        request.getRequestDispatcher("JSPfiles/player_page.jsp").forward(request, response);//изменить название главной страницы
//                        break;
//                }
                Instance instance = Instance.getInstance();
                long id = instance.validate(request.getParameter(Constant.AUT_LOGIN).toLowerCase(), DigestUtils.md5Hex(request.getParameter(Constant.AUT_PASSWORD)));
                switch ((int) id) {
                    case -1:
                        logger.info("Authorization: " + request.getParameter(Constant.AUT_LOGIN).toLowerCase() + " invalid password");
                        request.setAttribute("error", "Неверный пароль");
                        request.getRequestDispatcher("JSPfiles/intro_page.jsp").forward(request, response);
                        break;
                    case -2:
                        logger.info("Authorization: " + request.getParameter(Constant.AUT_LOGIN).toLowerCase() + " user doesn't exist");
                        request.setAttribute("error", "Пользователь не существует");
                        request.getRequestDispatcher("JSPfiles/intro_page.jsp").forward(request, response);
                        break;
                    default:
                        logger.info("Authorization: user- " + id + ". " + request.getParameter(Constant.AUT_LOGIN).toLowerCase());
                        HttpSession session = request.getSession();
                        session.setAttribute("id", id);
                        session.setAttribute("session", request.getParameter(Constant.AUT_LOGIN).toLowerCase());
                        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
                        response.setHeader("Pragma", "no-cache");
                        response.setDateHeader("Expires", 0);
                        request.getRequestDispatcher("JSPfiles/player_page.jsp").forward(request, response);//изменить название главной страницы
                        break;
                }
            }
            if (request.getParameter(Constant.AUT_REGISTRATION_BTN) != null) {
                request.getRequestDispatcher("JSPfiles//registration_page.jsp").forward(request, response);
            }
        } else {
            HttpSession session = request.getSession();
            if (session.getAttribute("session") != null && session.getAttribute("id") != null) {
                request.getRequestDispatcher("JSPfiles/player_page.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("JSPfiles/intro_page.jsp").forward(request, response);
            }
        }
    }

    public void destroy() {

    }
}
