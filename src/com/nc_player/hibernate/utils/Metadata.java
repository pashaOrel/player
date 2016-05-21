package com.nc_player.hibernate.utils;

import com.nc_player.hibernate.model.*;

/**
 * Created by beno on 12.04.2016.
 */
public class Metadata {

    private TrackEntity trackEntity = null;
    private AlbumEntity albumEntity = null;
    private ArtistEntity artistEntity = null;

    public Metadata(TrackEntity trackEntity, AlbumEntity albumEntity, ArtistEntity artistEntity) {
        this.trackEntity = trackEntity;
        this.albumEntity = albumEntity;
        this.artistEntity = artistEntity;
    }

    public Metadata() {
    }

    public Metadata(TrackEntity trackEntity, ArtistEntity artistEntity) {
        this.trackEntity = trackEntity;
        this.artistEntity = artistEntity;
    }

    public Metadata(TrackEntity trackEntity) {
        this.trackEntity = trackEntity;
    }

    public TrackEntity getTrackEntity() {
        return trackEntity;
    }

    public void setTrackEntity(TrackEntity trackEntity) {this.trackEntity = trackEntity;}

    public AlbumEntity getAlbumEntity() {
        return albumEntity;
    }

    public void setAlbumEntity(AlbumEntity albumEntity) {
        this.albumEntity = albumEntity;
    }

    public ArtistEntity getArtistEntity() {
        return artistEntity;
    }

    public void setArtistEntity(ArtistEntity artistEntity) {
        this.artistEntity = artistEntity;
    }





}
