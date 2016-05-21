package com.nc_player.servlet.userAction;

import com.google.gson.Gson;
import com.nc_player.hibernate.POJOs.Track;
import com.nc_player.hibernate.POJOs.TrackList;
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
public class Lists extends HttpServlet {
    private static Logger logger = Logger.getLogger(Lists.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        Instance instance = Instance.getInstance();
        HttpSession session = req.getSession();
        PrintWriter pr = resp.getWriter();
        Gson gson = new Gson();

        if (req.getParameter("deleteMusicFromList")!= null && req.getParameter("idList")!=null)
        {
            instance.userDeleteTrackFromTrackList(Long.parseLong(req.getParameter("deleteMusicFromList")), Long.parseLong(req.getParameter("idList")));
            logger.info("user " + session.getAttribute("session") + " delete track id = " + req.getParameter("deleteMusicFromList") + " from trackList "+req.getParameter("idList") );
        }
        if (req.getParameter("listId")!=null)
        {
            List<Track> trackFromList = instance.getTracksFromList(Long.parseLong(req.getParameter("listId")));
            pr.print(gson.toJson(trackFromList));
            logger.info("user "+session.getAttribute("session")+ " get track list id = "+ req.getParameter("listId") );
        }

        if (req.getParameter("nameList")!=null)
        {
            long idTrackList = instance.userCreateList((Long) session.getAttribute("id"),req.getParameter("nameList"));
            logger.info("user "+session.getAttribute("session")+ "create track list id = "+ idTrackList +" "+ " name = " + req.getParameter("nameList"));
            pr.print(idTrackList);
            if(req.getParameter("idMusicArray[]")!=null) {
                String[] qwe = req.getParameterValues("idMusicArray[]");
                for (int i = 0; i < qwe.length; i++) {
                    instance.userAddTrackToList(Long.parseLong(qwe[i]), idTrackList);
                    logger.info("user " + session.getAttribute("session") + "add track to list id = " + idTrackList + " " + " track id = " + qwe[i]);
                }
            }
        }

        if (req.getParameter("delTrackList")!=null){
            instance.userDeleteTrackList(Long.parseLong(req.getParameter("delTrackList")));
            logger.info("user " + session.getAttribute("session") + "delete trackList id = " + req.getParameter("delTrackList"));
        }

        if (req.getParameter("addMusic")!=null && req.getParameter("inList")!=null)
        {
            instance.userAddTrackToList(Long.parseLong(req.getParameter("addMusic")), Long.parseLong(req.getParameter("inList")));
            logger.info("user "+session.getAttribute("session")+ "add track to list id = "+ req.getParameter("inList") +" "+ " track id = " + req.getParameter("addMusic"));
        }



        if (req.getParameter("deleteMusicFromList")== null && req.getParameter("idList")==null && req.getParameter("listId")==null &&
                req.getParameter("idMusicArray[]")==null && req.getParameter("nameList")==null && req.getParameter("addMusic")==null
                && req.getParameter("inList")==null && req.getParameter("delTrackList")==null) {
            List<TrackList> tracklist = instance.getLists((Long) session.getAttribute("id"));
            pr.print(gson.toJson(tracklist));
            logger.info("user "+session.getAttribute("session")+ "get track lists");
        }


        pr.flush();
        pr.close();
    }
}
