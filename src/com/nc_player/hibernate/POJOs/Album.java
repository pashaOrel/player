package com.nc_player.hibernate.POJOs;

/**
 * Created by beno on 04.05.2016.
 */
public class Album {
    public Album(long id, String picture, String name) {
        this.id = id;
        this.picture = picture;
        this.name = name;
    }

    public Album() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    private long id;
    private String picture;
    private String name;
}
