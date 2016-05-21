package com.nc_player.hibernate.POJOs;

import java.util.Date;

/**
 * Created by beno on 04.05.2016.
 */
public class Event {
    private long id;
    private String description;
    private String picture;
    private String title;
    private String link;
    private String hash;
    private Date date;

    public Event(long id, String description, String picture, String title, String link, String hash, Date date) {
        this.id = id;
        this.description = description;
        this.picture = picture;
        this.title = title;
        this.link = link;
        this.hash = hash;
        this.date = date;
    }

    public Event() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
