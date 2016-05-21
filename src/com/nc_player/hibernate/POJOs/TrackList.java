package com.nc_player.hibernate.POJOs;

/**
 * Created by beno on 04.05.2016.
 */
public class TrackList {

    private long id;
    private String name;
    private long order;

    public TrackList(long id, String name, long order) {
        this.id = id;
        this.name = name;
        this.order = order;
    }

    public TrackList() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrder() {
        return order;
    }

    public void setOrder(long order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
