package com.nc_player.hibernate.utils;

import com.nc_player.RSS.RSS;
import com.nc_player.hibernate.model.ArtistEntity;
import com.nc_player.hibernate.model.EventEntity;
import com.nc_player.hibernate.model.TrackEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by beno on 14.04.2016.
 */
public class Test {
    public static void main(String[] args) {
        Instance instance = Instance.getInstance();
        TrackEntity trackEntity = new TrackEntity();
        trackEntity.setName("grg");
        trackEntity.setArtistName("333fc");
        trackEntity.setFile("vvvg");
        ArtistEntity artistEntity = new ArtistEntity();
        artistEntity.setName("333fc");
//        AlbumEntity albumEntity = new AlbumEntity();
//        albumEntity.setName("rhjhj");
        Metadata metadata = new Metadata();
        metadata.setArtistEntity(artistEntity);
        metadata.setTrackEntity(trackEntity);
//        metadata.setAlbumEntity(albumEntity);
//        instance.insertMetadata(metadata);
//        instance.close();

    }
}
