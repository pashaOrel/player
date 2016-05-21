package com.nc_player.imitation;

/**
 * Created by Павел on 23.04.2016.
 */
public class ImitArtist {
    private int id;
    private String description;
    private String picture;
    private String name;

    public ImitArtist(int artistId, String artistDescription, String artistPicture, String primaryName) {
        this.id = artistId;
        this.description = artistDescription;
        this.picture = artistPicture;
        this.name = primaryName;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
