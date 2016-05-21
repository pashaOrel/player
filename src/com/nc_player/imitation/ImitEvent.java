package com.nc_player.imitation;

import com.nc_player.hibernate.model.EventEntity;

/**
 * Created by Павел on 24.04.2016.
 */
public class ImitEvent {

    private int id;
    private String description;
    private String picture;
    private String title;
    private String link;

    public ImitEvent(int id, String description, String picture, String title, String link) {
        this.id = id;
        this.description = description;
        this.picture = picture;
        this.title = title;
        this.link = link;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

}
