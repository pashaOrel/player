package com.nc_player.servlet.userAction;

import com.google.gson.Gson;
import com.nc_player.hibernate.POJOs.Track;
import com.nc_player.hibernate.utils.Instance;
import com.nc_player.imitation.ImitTrack;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by INDIGO-ПС on 09.04.2016.
 */
public class Songs  extends HttpServlet {
    private static Logger logger = Logger.getLogger(Songs.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        Instance instance = Instance.getInstance();
        HttpSession session = req.getSession();
        PrintWriter pr = resp.getWriter();
        Gson gson = new Gson();
        if (req.getParameter("deleteMusic")!=null)
        {
            instance.userDeleteTrack((Long) session.getAttribute("id"), Long.parseLong(req.getParameter("deleteMusic")));
            logger.info("user " + session.getAttribute("session") + "delete track id = " + req.getParameter("deleteMusic") );
        }
        else {
            List<Track> track = instance.getSongs((Long) session.getAttribute("id"),false);
            pr.print(gson.toJson(track));
            logger.info("get user songs");
        }
        pr.flush();
        pr.close();


//        PrintWriter pr = resp.getWriter();
//        ArrayList list = new ArrayList();
//        Gson gson = new Gson();
//        pr.print(gson.toJson(list));
//
//        //проверяем является ли полученный запрос multipart/form-data
//        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
//        if (!isMultipart) {
//            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
//            return;
//        }
//        // Создаём класс фабрику
//        DiskFileItemFactory factory = new DiskFileItemFactory();
//
//        // Максимальный буфера данных в байтах,
//        // при его привышении данные начнут записываться на диск во временную директорию
//        // устанавливаем один мегабайт
//        factory.setSizeThreshold(1024*1024);
//
//        // устанавливаем временную директорию
//        File tempDir = (File)getServletContext().getAttribute("javax.servlet.context.tempdir");
//        factory.setRepository(tempDir);
//
//        //Создаём сам загрузчик
//        ServletFileUpload upload = new ServletFileUpload(factory);
//
//        //максимальный размер данных который разрешено загружать в байтах
//        //по умолчанию -1, без ограничений. Устанавливаем 10 мегабайт.
//        upload.setSizeMax(1024 * 1024 * 10);
//
//        try {
//            // MP3File mp3File = (MP3File) AudioFileIO.read((File) upload.parseRequest(req).get(0));
//            processUploadedFile((FileItem) upload.parseRequest(req).get(0));
//        } catch (Exception e) {
//            // req.getRequestDispatcher("JSPfiles/test.jsp").forward(req, resp);
//        }
//        //  req.getRequestDispatcher("JSPfiles/test.jsp").forward(req, resp);
    }



//    private void processUploadedFile(FileItem item) throws Exception {
//        File uploadetFile = null;
//        //выбираем файлу имя пока не найдём свободное
//        do{
//            String path = "D:\\var\\"+ item.getName();
//            uploadetFile = new File(path);
//        }while(uploadetFile.exists());
//
//        //создаём файл
//        uploadetFile.createNewFile();
//        //записываем в него данные
//        item.write(uploadetFile);
//    }
}
