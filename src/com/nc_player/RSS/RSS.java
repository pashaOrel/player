package com.nc_player.RSS;

import java.util.Date;

/**
 * Created by INDIGO-ПС on 03.04.2016.
 */
public class RSS {
    private String title;
    private String description;
    private String link;
    private Date pubDate;
    private String hash;
    private String picture;

    public RSS(String title,String description,String link, Date pubDate, String hash, String picture){
        this.title = title;
        this.description = description;
        this.link = link;
        this.pubDate = pubDate;
        this.hash = hash;
        this.picture = picture;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getPubDate() {
        return pubDate;
    }

    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "RSS{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                ", pubDate=" + pubDate +
                ", hash='" + hash + '\'' +
                ", picture='" + picture + '\'' +
                '}';
    }
}
