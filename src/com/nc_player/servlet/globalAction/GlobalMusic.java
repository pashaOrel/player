package com.nc_player.servlet.globalAction;

import com.google.gson.Gson;
import com.nc_player.hibernate.POJOs.Track;
import com.nc_player.hibernate.model.TrackEntity;
import com.nc_player.hibernate.utils.Instance;
import com.nc_player.imitation.ImitTrack;
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
public class GlobalMusic extends HttpServlet {
    private static Logger logger = Logger.getLogger(GlobalMusic.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        Instance instance = Instance.getInstance();
        PrintWriter responce = resp.getWriter();
        Gson gson = new Gson();
        HttpSession session = req.getSession();
        if (req.getParameter("idMusic")!=null)
        {
            int addTrack = instance.userAddTrack((Long) session.getAttribute("id"), Long.parseLong(req.getParameter("idMusic")));
            responce.print(addTrack);
            logger.info("user " + session.getAttribute("session") + "add track id = " + req.getParameter("idMusic") );
        }
        else {
            List<Track> track = instance.getSongs((Long) session.getAttribute("id"), true);
            responce.print(gson.toJson(track));
            logger.info("get global songs list");
        }
        responce.flush();
        responce.close();


//        PrintWriter pr = resp.getWriter();
//        Gson gson = new Gson();
//        ArrayList<ImitTrack> list = new ArrayList<>();
//        ImitTrack t1 = new ImitTrack(1,"Рок",1,"https://psv4.vk.me/c4373/u27428279/audios/92b23d56d712.mp3?extra=AQhpQv1qcynkrWROP6gASOvU_14gj2o0A9Zq3lGlE6xFh14KdGRUgtQbGcBGzSnP5DEomidn4fPDWlYsNCQfGdX70wRrap9umUmeBqE1gLe0JNP4KQBLZ2Ttn6e3CUZ0SQm-hx6OLmIZSg,205","Кто-то1");
//        ImitTrack t2 = new ImitTrack(2,"Рок2",2,"https://psv4.vk.me/c6177/u10469030/audios/c28cc21aa1ba.mp3?extra=HXJbpZkfT1NwzCucLkSEVK-WjDNSHHEHutzUdSZuLzgCFYrPtwYnZo8EZ4WpLmk4SaOPzijBpoH9o2h30eP2GQynjG-DHW2BrBiBk75UVtXszXAXRbZ9p8grMnG49igHWoXPynltEXwKyw,246","Кто-то2");
//        ImitTrack t3 = new ImitTrack(3,"Рок3",3,"https://psv4.vk.me/c613820/u159805319/audios/294896c0b41d.mp3?extra=DAUyLzlTG07cJaT25120nD2-VAJkhNvwcHiczflBQd_xoPVULRa3RhaZaChCmlS9aCa4iQndlW9g6QyAc7aO8pHRGj0FQkTVij9alzIFS4xMGGmBT2HaYgJ4YkzuxTnl6VNdlAAZeup-dw,171","Кто-то3");
//        ImitTrack t4 = new ImitTrack(4,"Рок4",4,"http://mdn.github.io/media-source-buffer/viper.ogg","Кто-то4");
//        ImitTrack t5 = new ImitTrack(5,"Рок5",5,"https://psv4.vk.me/c6177/u10469030/audios/c28cc21aa1ba.mp3?extra=HXJbpZkfT1NwzCucLkSEVK-WjDNSHHEHutzUdSZuLzgCFYrPtwYnZo8EZ4WpLmk4SaOPzijBpoH9o2h30eP2GQynjG-DHW2BrBiBk75UVtXszXAXRbZ9p8grMnG49igHWoXPynltEXwKyw,246","Кто-то5");
//        ImitTrack t6 = new ImitTrack(6,"Рок6",6,"https://psv4.vk.me/c613820/u159805319/audios/294896c0b41d.mp3?extra=DAUyLzlTG07cJaT25120nD2-VAJkhNvwcHiczflBQd_xoPVULRa3RhaZaChCmlS9aCa4iQndlW9g6QyAc7aO8pHRGj0FQkTVij9alzIFS4xMGGmBT2HaYgJ4YkzuxTnl6VNdlAAZeup-dw,171","Кто-то6");
//        ImitTrack t7 = new ImitTrack(7,"Рок7",7,"https://psv4.vk.me/c4373/u27428279/audios/92b23d56d712.mp3?extra=AQhpQv1qcynkrWROP6gASOvU_14gj2o0A9Zq3lGlE6xFh14KdGRUgtQbGcBGzSnP5DEomidn4fPDWlYsNCQfGdX70wRrap9umUmeBqE1gLe0JNP4KQBLZ2Ttn6e3CUZ0SQm-hx6OLmIZSg,205","Кто-то7");
//        list.add(t1);
//        list.add(t2);
//        list.add(t3);
//        list.add(t4);
//        list.add(t5);
//        list.add(t6);
//        list.add(t7);
//        pr.print(gson.toJson(list));
    }
}
