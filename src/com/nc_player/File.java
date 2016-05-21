package com.nc_player;

import com.nc_player.hibernate.utils.Instance;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by INDIGO-ПС on 14.05.2016.
 */
public class File extends HttpServlet {

    private ArrayList<String> list;
    private Random random = new Random();
    private static final String USER_FOLDER_PATH = "/var/lib/openshift/56fbca750c1e6601a3000115/app-root/data/files/user/";
    private static Logger logger = Logger.getLogger(File.class);

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Instance instance = Instance.getInstance();
        HttpSession session = req.getSession();
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (isMultipart) {
            try {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                factory.setSizeThreshold(1024 * 1024 * 10);
                java.io.File tempDir = (java.io.File) getServletContext().getAttribute("javax.servlet.context.tempdir");
                factory.setRepository(tempDir);
                ServletFileUpload upload = new ServletFileUpload(factory);
                upload.setSizeMax(1024 * 1024 * 10);
                List items = upload.parseRequest(req);
                Iterator iter = items.iterator();
                list = new ArrayList<>();
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (item.isFormField()) {
                        list.add(Streams.asString(item.getInputStream(), "UTF-8"));
                    } else {
                        String path = processUploadedFile(item);
                        instance.userLoadTrack((Long) session.getAttribute("id"),list.get(1),path,list.get(0));
                        logger.info("user id = " +  session.getAttribute("id") +" add track name = "+list.get(1)
                        +" artistName = " + list.get(0)+ "path file = " + path);
                    }
                }
            } catch (FileUploadException e) {
                logger.info("Parsing file upload failed.", e);
            } catch (Exception e) {
                logger.info("Parsing file upload failed.", e);
            }
        }
    }

    private  String processUploadedFile(FileItem item) throws Exception {
        java.io.File uploadetFile = null;
        String path = null;
        do {
            path = USER_FOLDER_PATH + random.nextInt() + item.getName();
            uploadetFile = new java.io.File(path);
        } while (uploadetFile.exists());
        uploadetFile.createNewFile();
        item.write(uploadetFile);
        return path;
    }

}
