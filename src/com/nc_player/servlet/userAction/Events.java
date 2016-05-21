package com.nc_player.servlet.userAction;

import com.google.gson.Gson;
import com.nc_player.hibernate.POJOs.Event;
import com.nc_player.hibernate.utils.Instance;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by INDIGO-ПС on 09.04.2016.
 */
public class Events extends HttpServlet {
    private static Logger logger = Logger.getLogger(Event.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        Instance instance = Instance.getInstance();
        HttpSession session = req.getSession();
        PrintWriter pr = resp.getWriter();
        Gson gson = new Gson();
        if (req.getParameter("deleteEvent")!=null)
        {
            instance.userDeleteEvent((Long) session.getAttribute("id"), Long.parseLong(req.getParameter("deleteEvent")));
            logger.info("user " + session.getAttribute("session") + " delete event id = " + req.getParameter("deleteEvent") );
        }
        else{
            List<Event> event = instance.getEvents((Long) session.getAttribute("id"),false);
            pr.print(gson.toJson(event));
            logger.info("get user events "+session.getAttribute("session") );
        }
        pr.flush();
        pr.close();
    }
}
