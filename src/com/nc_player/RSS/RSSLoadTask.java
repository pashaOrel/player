package com.nc_player.RSS;

import com.nc_player.MetaData.AudioParser;
import com.nc_player.MetaData.WikiParser;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by INDIGO-ПС on 12.04.2016.
 */
public class RSSLoadTask implements javax.servlet.ServletContextListener {

    private static Logger logger = Logger.getLogger(RSSLoadTask.class);
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
        Runnable pinger = new Runnable() {
            public void run() {
                new RSSParser().parseNews();
                logger.info("RSS trigger start : " + new Date());
            }
        };
        ses.scheduleAtFixedRate(pinger,1,3,TimeUnit.HOURS);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
