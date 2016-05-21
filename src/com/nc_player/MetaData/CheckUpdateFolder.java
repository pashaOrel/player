package com.nc_player.MetaData;

import org.apache.log4j.Logger;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by INDIGO-ПС on 13.04.2016.
 */
public class CheckUpdateFolder {
    private static Logger logger = Logger.getLogger(CheckUpdateFolder.class);
    private ArrayList<File> listWithFileName;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM.dd.yyyy");
    private Date todayDate = new Date();
    private static final String FILE_PATH = "/var/lib/openshift/56fbca750c1e6601a3000115/app-root/data/files/artists";

    public CheckUpdateFolder(){
        listWithFileName = new ArrayList<>();
    }
    public ArrayList<File> getNewFiles(){

        listFiles(FILE_PATH);
        logger.info("get new file collection "+ new Date());
        return listWithFileName;
    }
    public void listFiles(String str) {
        File file = new File(str);
        for (File fileList : file.listFiles() ) {
            if (fileList.isFile() &&
                    dateFormat.format(todayDate).equals(dateFormat.format(new Date(fileList.lastModified())))) {
                if (fileList.getName().lastIndexOf(".") != -1 && fileList.getName().lastIndexOf(".") != 0) {
                    if (fileList.getName().substring(fileList.getName().lastIndexOf(".") + 1).equals("mp3"))
                    {
                        listWithFileName.add(fileList);
                        logger.info( "add new file " + fileList.getName());
                    }
                }
            } else if (fileList.isDirectory()) {
                listFiles(fileList.getAbsolutePath());
            }
        }
    }
}
