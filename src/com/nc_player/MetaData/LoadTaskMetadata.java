package com.nc_player.MetaData;

import com.nc_player.RSS.RSSParser;
import com.nc_player.hibernate.POJOs.Event;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by INDIGO-ПС on 06.05.2016.
 */
public class LoadTaskMetadata implements javax.servlet.ServletContextListener {

    private static Logger logger = Logger.getLogger(LoadTaskMetadata.class);
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
        Runnable metadata = new Runnable() {
            public void run() {
                new AudioParser().checkMetadata();
                new WikiParser().parseWiki();
                logger.info("metadata trigger start : " + new Date());
            }
        };
        ses.scheduleAtFixedRate(metadata,12,24,TimeUnit.HOURS);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}