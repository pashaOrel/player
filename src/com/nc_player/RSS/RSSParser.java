package com.nc_player.RSS;

/**
 * Created by INDIGO-ПС on 29.03.2016.
 */
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.path.xml.XmlPath;
import com.nc_player.Constant;
import com.nc_player.hibernate.utils.Instance;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class RSSParser {
        private Date date = new Date();
        private final String DEFAULT_IMAGE_PATH = "http://jbossewstestappplaye-rsatestapp.rhcloud.com/data/files/default.jpg";
        private SimpleDateFormat format = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss Z", Locale.US);
        public ArrayList<RSS> rss;

        public void parseNews() {
                rss = new ArrayList<RSS>();
                List<String> rssconfigList  = getConfigList();
                Instance instance = Instance.getInstance();
                List<String> hashList = instance.getHashEvent();
                for (String url : rssconfigList) {
                        String xml = RestAssured.get(url).andReturn().asString();
                        XmlPath xmlPath = new XmlPath(xml).setRoot("rss.channel");
                        List<Object> title = xmlPath.getList("item.title");
                        List<Object> description = xmlPath.getList("item.description");
                        List<Object> link = xmlPath.getList("item.link");
                        List<Object> pubDate = xmlPath.getList("item.pubDate");
                        List<Object> picture = xmlPath.getList("item.image.url");
                        for (int i = 0; i < title.size(); i++) {
                                int checkEqualHash = 0;
                                //проверяем по базе на существующих hash
                                for (String hash : hashList) {
                                        if (DigestUtils.md5Hex((String) title.get(i)).equals(hash)) {
                                                checkEqualHash++;
                                                break;
                                        }
                                }
                                // если hash не нашелся то добавляем в коллекцию
                                if (checkEqualHash == 0) {
                                        try {
                                                if (pubDate.size() == 0) {
                                                        if (picture.size() == 0) {
                                                                rss.add(new RSS((String) title.get(i), (String) description.get(i),
                                                                        (String) link.get(i), date, DigestUtils.md5Hex((String) title.get(i)), DEFAULT_IMAGE_PATH));
                                                        } else {
                                                                rss.add(new RSS((String) title.get(i), (String) description.get(i),
                                                                        (String) link.get(i), date, DigestUtils.md5Hex((String) title.get(i)), (String) picture.get(i)));
                                                        }
                                                } else {
                                                        if (picture.size() == 0) {
                                                                rss.add(new RSS((String) title.get(i), (String) description.get(i),
                                                                        (String) link.get(i), format.parse((String) pubDate.get(i)), DigestUtils.md5Hex((String) title.get(i)), DEFAULT_IMAGE_PATH));
                                                        } else {
                                                                rss.add(new RSS((String) title.get(i), (String) description.get(i),
                                                                        (String) link.get(i), format.parse((String) pubDate.get(i)), DigestUtils.md5Hex((String) title.get(i)), (String) picture.get(i)));

                                                        }
                                                }
                                        } catch (ParseException e) {
                                                e.printStackTrace();
                                        }
                                }
                        }
                }
                instance.insertEvents(rss);

        }


        public List<String> getConfigList() {
                List<String> list = new ArrayList<String>();
                BufferedReader reader;
                try {
                        URL url = new URL(Constant.RSS_PATH_CONFIG);
                        reader = new BufferedReader(new InputStreamReader(url.openStream()));
                        String line;
                        while ((line = reader.readLine()) != null) {
                                list.add(line);
                                System.out.println(line);
                        }
                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                } catch (IOException e) {
                        e.printStackTrace();
                }
                return list;
        }
}
