package com.nc_player.imitation;

import java.util.List;

/**
 * Created by Павел on 23.04.2016.
 */
public class ImitAlbum {
    private int id;
    private String picture;
    private String name;
    private int artistID;
    private List<ImitTrack> trackEntities;

    public ImitAlbum(int albumID, String albumPicture, String albumName, int artistID, List<ImitTrack> trackEntities) {
        this.id = albumID;
        this.picture = albumPicture;
        this.name = albumName;
        this.artistID = artistID;
        this.trackEntities = trackEntities;

    }

    public int getId() {
        return id;
    }

    public String getPicture() {
        return picture;
    }

    public String getName() {
        return name;
    }

    public int getArtistID() {
        return artistID;
    }

    public List<ImitTrack> getTrackEntities() {
        return trackEntities;
    }

    public void setTrackEntities(List<ImitTrack> trackEntities) {
        this.trackEntities = trackEntities;
    }
}
