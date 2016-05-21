package com.nc_player.servlet.userAction;

import com.google.gson.Gson;
import com.nc_player.hibernate.POJOs.Artist;
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
public class Artists extends HttpServlet {
    private static Logger logger = Logger.getLogger(Artists.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        Instance instance = Instance.getInstance();
        HttpSession session = req.getSession();
        List<Artist> artist =  instance.getArtists((Long) session.getAttribute("id"),false);
        PrintWriter pr = resp.getWriter();
        Gson gson = new Gson();
        pr.print(gson.toJson(artist));
        logger.info("user " + session.getAttribute("session") + " get artist list");
        pr.flush();
        pr.close();
    }
}
