package com.nc_player.MetaData;

import com.nc_player.hibernate.POJOs.Artist;
import com.nc_player.hibernate.model.ArtistEntity;
import com.nc_player.hibernate.utils.Instance;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by INDIGO-ПС on 26.04.2016.
 */
public class WikiParser {
    private static final String ARTIST_DEFAULT_PICTURE = "http://jbossewstestappplaye-rsatestapp.rhcloud.com/data/files/artist.jpg";
    private static Logger logger = Logger.getLogger(WikiParser.class);
    public void parseWiki (){
        Instance instance = Instance.getInstance();
        List<Artist> artistList  = instance.getNullArtist();
        logger.info("get from DB collection artist for parse wiki: "+artistList.size()+ " size " + new Date());
        if (artistList.size()>0) {
            for (Artist artists : artistList) {
                artists.setDescription(getDescription(artists.getName()));
                artists.setPicture(getPicture(artists.getName()));
            }
            instance.setArtistsData(artistList);
        }
    }

    public String getDescription(String artistName){

        String url = "https://ru.wikipedia.org/wiki/" + artistName;
        Document doc = null;
        String description = "";
        try {
            doc = Jsoup.connect(url).get();

            Elements paragraphs = doc.select(".infobox p");
            Element firstParagraph = paragraphs.first();
            Element lastParagraph = paragraphs.last();
            Element p;
            int i = 1;
            p = firstParagraph;
            while (p != lastParagraph) {
                p = paragraphs.get(i);
                i++;
            }
            Elements paragraphs1 = doc.select(".mw-content-ltr p");
            Element p1;

            for (int a = i; a < i + 4; a++) {
                p1 = paragraphs1.get(a);
                description += p1.text();
            }
        } catch (IOException e) {
            logger.info("can't get description from wiki for "+artistName + " " + new Date());
            description = "null";
        }

        logger.info("get description from wiki for "+artistName + " " + new Date());
        return description;
    }

    public String getPicture(String artistName){
        String url = "https://ru.wikipedia.org/wiki/" + artistName;
        String imageUrl = null;
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
            Elements images = doc.select(".mw-content-ltr img");
            imageUrl = images.attr("src");
        } catch (IOException e) {
            imageUrl = ARTIST_DEFAULT_PICTURE;
            logger.info("can't get picture from wiki for "+artistName + " " + new Date());
        }
        logger.info("get picture from wiki for "+artistName + " " + new Date());
        return imageUrl;
    }
}
