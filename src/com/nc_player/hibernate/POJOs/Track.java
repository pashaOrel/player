package com.nc_player.hibernate.POJOs;

/**
 * Created by beno on 04.05.2016.
 */
public class Track {
    private long id;
    private String name;
    private String file;
    private String artistName;
    private String genre;

    public Track(long id, String name, String file, String artistName, String genre) {
        this.id = id;
        this.name = name;
        this.file = file;
        this.artistName = artistName;
        this.genre = genre;
    }

    public Track() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}