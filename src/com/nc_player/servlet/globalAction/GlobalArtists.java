package com.nc_player.servlet.globalAction;

import com.google.gson.Gson;
import com.nc_player.hibernate.POJOs.Album;
import com.nc_player.hibernate.POJOs.Artist;
import com.nc_player.hibernate.model.AlbumEntity;
import com.nc_player.hibernate.model.ArtistEntity;
import com.nc_player.hibernate.model.TrackEntity;
import com.nc_player.hibernate.utils.Instance;
import com.nc_player.imitation.ImitArtist;
import com.nc_player.servlet.userAction.Songs;
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
public class GlobalArtists extends HttpServlet {
    private static Logger logger = Logger.getLogger(GlobalArtists.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setCharacterEncoding("UTF-8");
        Instance instance = Instance.getInstance();
        PrintWriter responce = resp.getWriter();
        Gson gson = new Gson();
        HttpSession session = req.getSession();
        if (req.getParameter("idArtist")!=null)
        {
            List<Album> album = instance.getAlbumsOfArtist(Long.parseLong(req.getParameter("idArtist")));
            responce.print(gson.toJson(album));
            logger.info("get album of artists id = " + req.getParameter("idArtist"));
        }
        else {
            List<Artist> artists= instance.getArtists((Long) session.getAttribute("id"),true);
            responce.print(gson.toJson(artists));
            logger.info("get global artists list");
        }
        responce.flush();
        responce.close();



//        PrintWriter pr = resp.getWriter();
//       // pr.print(8);
//        ImitArtist a1 = new ImitArtist(1,"Description1","http://rg.ru/i/gallery/f9e74241/0b4ac853.jpg","King");
//        ImitArtist a2 = new ImitArtist(2,"Description2","http://img2.ntv.ru/home/news/20131115/putin.jpg","Putin");
//        ImitArtist a3 = new ImitArtist(3,"Description3","http://velvetmusic.ru/meladze.jpg","3");
//        ImitArtist a4 = new ImitArtist(4,"Description4","http://rushit.pro/newshit/uploads/1408396899_500.jpg","4");
//        ImitArtist a5 = new ImitArtist(5,"Description5","http://bezumno.ru/uploads/posts/2011-10/1318173151_6.jpg","5");
//        ImitArtist a6 = new ImitArtist(6,"Description6","http://izvestiacontent.ru/media/3/news/2013/05/549871/ZIF_1439_1.JPG","6");
//        ImitArtist a7 = new ImitArtist(7,"Description7","http://thesweetdreams.ru/wp-content/uploads/2014/01/Artistyi-moskovskoy-kaver-gruppyi-5.jpg","7");
//        ImitArtist a8 = new ImitArtist(7,"Description7","http://thesweetdreams.ru/wp-content/uploads/2014/01/Artistyi-moskovskoy-kaver-gruppyi-5.jpg","7");
//        ImitArtist a9 = new ImitArtist(7,"Description7","http://thesweetdreams.ru/wp-content/uploads/2014/01/Artistyi-moskovskoy-kaver-gruppyi-5.jpg","7");
//        ImitArtist a10 = new ImitArtist(7,"Description7","http://thesweetdreams.ru/wp-content/uploads/2014/01/Artistyi-moskovskoy-kaver-gruppyi-5.jpg","7");
//        ImitArtist a11 = new ImitArtist(7,"Description7","http://thesweetdreams.ru/wp-content/uploads/2014/01/Artistyi-moskovskoy-kaver-gruppyi-5.jpg","7");
//        Gson gson = new Gson();
//        ArrayList<ImitArtist> list = new ArrayList<>();
//        list.add(a1);
//        list.add(a2);
//        list.add(a3);
//        list.add(a4);
//        list.add(a5);
//        list.add(a6);
//        list.add(a7);
//        list.add(a8);
//        list.add(a9);
//        list.add(a10);
//        list.add(a11);
//        pr.print(gson.toJson(list));
//        pr.flush();
//        pr.close();
    }
}
