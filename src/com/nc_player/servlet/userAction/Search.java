package com.nc_player.servlet.userAction;

import com.google.gson.Gson;
import com.nc_player.hibernate.POJOs.*;
import com.nc_player.hibernate.model.*;
import com.nc_player.hibernate.utils.Instance;
import com.nc_player.imitation.ImitAlbum;

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
public class Search  extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");

        String bttn = req.getParameter("bttn");
        String stroka = req.getParameter("strochka");
        String bool = req.getParameter("bool");
        PrintWriter responce = resp.getWriter();

        Gson gson = new Gson();
        if (bttn != null && stroka != null) {
            HttpSession session = req.getSession();
            Instance instance = Instance.getInstance();
            long id = (Long) session.getAttribute("id");
            switch (bttn)
            {
                case "1":
                    List<Event> userEvent = instance.searchEvents(id,stroka,false);
                    responce.print(gson.toJson(userEvent));
                    break;
                case "2":
                    List<Artist> userArtist = instance.searchArtists(id,stroka,false);
                    responce.print(gson.toJson(userArtist));
                    break;
                case "3":
                    List<TrackList> trackList = instance.searchTrackLists(id,stroka);
                    responce.print(gson.toJson(trackList));
                    break;
                case "4":
                    List<Track> userTrack = instance.searchTracks(id, stroka,false, Boolean.parseBoolean(bool));
                    responce.print(gson.toJson(userTrack));
                    break;
                case "5":
                    List<Track> globalTrack = instance.searchTracks(id, stroka,true, Boolean.parseBoolean(bool));
                    responce.print(gson.toJson(globalTrack));
                    break;
                case "6":
                    List<Event> globalEvent = instance.searchEvents(id,stroka,true);
                    responce.print(gson.toJson(globalEvent));
                    break;
                case "7":
                    List<Artist> globalArtist = instance.searchArtists(id,stroka,true);
                    responce.print(gson.toJson(globalArtist));
                    break;
                case "8":
                    List<Album> globalAlbum = instance.searchAlbums(id,stroka);
                    responce.print(gson.toJson(globalAlbum));
                    break;
            }
            responce.flush();
            responce.close();
            //userEvents - 1
            //userArtists - 2
            //userLists - 3
            //userSongs - 4
            //globMus - 5
            //globEv - 6
            //globArt - 7
            //globAlb - 8
        }
        else {
            ArrayList<String> list = new ArrayList<>();
            responce.print(gson.toJson(list));
            responce.flush();
            responce.close();
        }
    }
}
