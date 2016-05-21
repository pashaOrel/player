package com.nc_player.servlet.globalAction;

import com.google.gson.Gson;
import com.nc_player.RSS.RSS;
import com.nc_player.hibernate.POJOs.Album;
import com.nc_player.hibernate.POJOs.Track;
import com.nc_player.hibernate.model.AlbumEntity;
import com.nc_player.hibernate.model.ArtistEntity;
import com.nc_player.hibernate.model.TrackEntity;
import com.nc_player.hibernate.utils.Instance;
import com.nc_player.imitation.ImitAlbum;
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
public class GlobalAlbums extends HttpServlet {
    private static Logger logger = Logger.getLogger(GlobalAlbums.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");
        Instance instance = Instance.getInstance();
        PrintWriter responce = resp.getWriter();
        Gson gson = new Gson();
        HttpSession session = req.getSession();
        if (req.getParameter("idAlbum")!=null)
        {
            List<Track> track = instance.getTracksOfAlbum(Long.parseLong(req.getParameter("idAlbum")));
            responce.print(gson.toJson(track));
            logger.info("get track of album id = " + req.getParameter("idAlbum"));
        }
        else {
            List<Album> album = instance.getAlbums((Long) session.getAttribute("id"));
            responce.print(gson.toJson(album));
            logger.info("get global album list");
        }
        responce.flush();
        responce.close();



//        PrintWriter pr = resp.getWriter();
//
//        ImitTrack t1 = new ImitTrack(1,"Рок",1,"https://psv4.vk.me/c4373/u27428279/audios/92b23d56d712.mp3?extra=AQhpQv1qcynkrWROP6gASOvU_14gj2o0A9Zq3lGlE6xFh14KdGRUgtQbGcBGzSnP5DEomidn4fPDWlYsNCQfGdX70wRrap9umUmeBqE1gLe0JNP4KQBLZ2Ttn6e3CUZ0SQm-hx6OLmIZSg,205","Кто-то1");
//        ImitTrack t2 = new ImitTrack(2,"Рок2",2,"https://psv4.vk.me/c6177/u10469030/audios/c28cc21aa1ba.mp3?extra=HXJbpZkfT1NwzCucLkSEVK-WjDNSHHEHutzUdSZuLzgCFYrPtwYnZo8EZ4WpLmk4SaOPzijBpoH9o2h30eP2GQynjG-DHW2BrBiBk75UVtXszXAXRbZ9p8grMnG49igHWoXPynltEXwKyw,246","Кто-то2");
//        ImitTrack t3 = new ImitTrack(3,"Рок3",3,"https://psv4.vk.me/c613820/u159805319/audios/294896c0b41d.mp3?extra=DAUyLzlTG07cJaT25120nD2-VAJkhNvwcHiczflBQd_xoPVULRa3RhaZaChCmlS9aCa4iQndlW9g6QyAc7aO8pHRGj0FQkTVij9alzIFS4xMGGmBT2HaYgJ4YkzuxTnl6VNdlAAZeup-dw,171","Кто-то3");
//        ArrayList<ImitTrack> listik = new ArrayList<>();
//        listik.add(t1);
//        listik.add(t2);
//        listik.add(t3);
//
//        ImitAlbum a1 = new ImitAlbum(1,"http://pulson.ru/wp-content/uploads/2012/12/wallpaper-1911991.jpg","first",1,listik);
//        ImitAlbum a2 = new ImitAlbum(2,"http://nibler.ru/uploads/users/2012-03-27/%D0%BA%D0%B0%D1%80%D1%82%D0%B8%D0%BD%D0%BA%D0%B8-%D1%81%D1%82%D0%BE%D0%BB-%D0%A0%D0%B0%D0%B1%D0%BE%D1%87%D0%B8%D0%B9-%D0%BA%D0%B0%D1%80%D1%82%D0%B8%D0%BD%D0%BA%D0%B8-%D1%81%D0%BC%D0%B5%D1%88%D0%BD%D1%8B%D0%B5%20%D0%BA%D0%B0%D1%80%D1%82%D0%B8%D0%BD%D0%BA%D0%B8-%D1%84%D0%BE%D1%82%D0%BE%D0%BF%D1%80%D0%B8%D0%BA%D0%BE%D0%BB%D1%8B_8617971351.jpg","second",2,listik);
//        ImitAlbum a3 = new ImitAlbum(3,"http://www.ellf.ru/uploads/posts/2015-07/1437948968_001-ellf.ru.jpg","third",3,listik);
//
//
//        Gson gson = new Gson();
//        ArrayList<ImitAlbum> list = new ArrayList<>();
//        list.add(a1);
//        list.add(a2);
//        list.add(a3);
//        pr.print(gson.toJson(list));
//        pr.flush();
//        pr.close();
    }
}
