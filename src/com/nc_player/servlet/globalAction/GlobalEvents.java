package com.nc_player.servlet.globalAction;

import com.google.gson.Gson;
import com.nc_player.hibernate.POJOs.Event;
import com.nc_player.hibernate.model.EventEntity;
import com.nc_player.hibernate.utils.Instance;
import com.nc_player.imitation.ImitEvent;
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
public class GlobalEvents extends HttpServlet{
    private static Logger logger = Logger.getLogger(GlobalEvents.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        Instance instance = Instance.getInstance();
        HttpSession session = req.getSession();
        Gson gson = new Gson();
        PrintWriter responce = resp.getWriter();
        if (req.getParameter("idEvent")!=null)
        {
            int addEvent =  instance.userAddEvent((Long) session.getAttribute("id"), Long.parseLong(req.getParameter("idEvent")));
            responce.print(addEvent);
            logger.info("user " + session.getAttribute("session") + "add event id = " + req.getParameter("idEvent") );
        }
        else {
            List<Event> eventList = instance.getEvents((Long) session.getAttribute("id"), true);
            responce.print(gson.toJson(eventList));
            logger.info("get global events list");
        }

        responce.flush();
        responce.close();

   /*     PrintWriter pr = resp.getWriter();
        ImitEvent e1 = new ImitEvent(1,"smth1","http://cdn.tvc.ru/pictures/tb/481/03.jpg","Событие1","http://cdn.tvc.ru/pictures/tb/481/03.jpg");
        ImitEvent e2 = new ImitEvent(2,"smth2","http://kovchegsochi.ru/upload/iblock/101/101d47dfa348b2a4e5e58790c5a1c22e.jpg","Событие2","http://kovchegsochi.ru/upload/iblock/101/101d47dfa348b2a4e5e58790c5a1c22e.jpg");
        ImitEvent e3 = new ImitEvent(3,"smth3","https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTyg6gUvvStFFe-53fe5rrGPrDH36axNunwLKjkGM-VaiiYghTXWg","Событие3","https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcTyg6gUvvStFFe-53fe5rrGPrDH36axNunwLKjkGM-VaiiYghTXWg");
        ImitEvent e4 = new ImitEvent(4,"smth4","http://lamcdn.net/the-village.ru/post-cover/gn1lVuVxlo_8vAjC209UDQ-default.jpg","Событие4","http://lamcdn.net/the-village.ru/post-cover/gn1lVuVxlo_8vAjC209UDQ-default.jpg");
        ImitEvent e5 = new ImitEvent(5,"smth5","http://www.oszone.net/figs/u/72715/091205184333/ev3-02.jpg","Событие5","http://www.oszone.net/figs/u/72715/091205184333/ev3-02.jpg5");
        ImitEvent e6 = new ImitEvent(6,"smth6","http://www.topnews.ru/upload/news/2011/12/9c3d4b01/9c3d4b01_1.jpg","Событие6","http://www.topnews.ru/upload/news/2011/12/9c3d4b01/9c3d4b01_1.jpg");
        ImitEvent e7 = new ImitEvent(7,"smth7","https://img.championat.com/news/big/h/j/valuev-protiv-sebja-i-tajna-neraskrytogo-olimpijskogo-kolca_1418034069663474126.jpg","Событие7","https://img.championat.com/news/big/h/j/valuev-protiv-sebja-i-tajna-neraskrytogo-olimpijskogo-kolca_1418034069663474126.jpg");
        ArrayList<ImitEvent> list = new ArrayList<>();
        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);
        list.add(e5);
        list.add(e6);
        list.add(e7);
        list.add(e7);
        list.add(e7);
        list.add(e7);
        list.add(e7);
        list.add(e7);
        list.add(e7);
        list.add(e7);
        Gson gson = new Gson();
        pr.print(gson.toJson(list));
        pr.flush();
        pr.close();*/
    }
}
