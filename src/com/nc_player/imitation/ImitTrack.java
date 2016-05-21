package com.nc_player.imitation;

/**
 * Created by Павел on 24.04.2016.
 */
public class ImitTrack {

    private int id;
    private String name;
    private int artisID;
    private String file;
    private String artistName;

    public ImitTrack(int trackID, String trackName, int artisID, String trackFile, String trakArtistName) {
        this.id = trackID;
        this.name = trackName;
        this.artisID = artisID;
        this.file = trackFile;
        this.artistName = trakArtistName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArtisID() {
        return artisID;
    }

    public void setArtisID(int artisID) {
        this.artisID = artisID;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
}
