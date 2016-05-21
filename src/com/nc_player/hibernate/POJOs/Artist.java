package com.nc_player.hibernate.POJOs;

/**
 * Created by beno on 04.05.2016.
 */
public class Artist {
    private long id;
    private String description;
    private String picture;
    private String name;

    public Artist(long id, String description, String picture, String name) {
        this.id = id;
        this.description = description;
        this.picture = picture;
        this.name = name;
    }

    public Artist() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
