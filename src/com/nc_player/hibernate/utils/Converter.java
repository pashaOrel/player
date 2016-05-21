package com.nc_player.hibernate.utils;

import com.nc_player.hibernate.POJOs.*;
import com.nc_player.hibernate.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by beno on 04.05.2016.
 */

public class Converter {
    private static class Holder{
        final static Converter converter = new Converter();
    }

    private Converter(){
    }

    public static Converter getConverter(){
        return Holder.converter;
    }

    public List<Event> convertEventsList (List<EventEntity> list){
        List<Event> newlist = new ArrayList<>();
        for (EventEntity e : list) {
            newlist.add(new Event(e.getId(),e.getDescription(),e.getPicture(),e.getTitle(),e.getLink(),e.getHash(),e.getDate()));
        }
        return newlist;
    }

    public List<Track> convertTracksList (List<TrackEntity> list){
        List<Track> newlist = new ArrayList<>();
        for (TrackEntity track : list) {
            newlist.add(new Track(track.getId(),track.getName(),track.getFile(),track.getArtistName(),track.getGenre()));
        }
        return newlist;
    }

    public List<Album> convertAlbumList (List<AlbumEntity> list){
        List<Album> newlist = new ArrayList<>();
        for (AlbumEntity albumEntity : list) {
            newlist.add(new Album(albumEntity.getId(),albumEntity.getPicture(),albumEntity.getName()));
        }
        return newlist;
    }

    public List<Artist> convertArtisList (List<ArtistEntity> list){
        List<Artist> newlist = new ArrayList<>();
        for (ArtistEntity artistEntity : list) {
            newlist.add(new Artist(artistEntity.getId(),artistEntity.getDescription(),artistEntity.getPicture(),artistEntity.getName()));
        }
        return newlist;
    }

    public List<TrackList> convertTrackListsList(List<TrackListEntity> list){
        List<TrackList> newlist = new ArrayList<>();
        for (TrackListEntity trackListEntity : list) {
            newlist.add(new TrackList(trackListEntity.getId(),trackListEntity.getName(),trackListEntity.getOrder()));
        }
        return newlist;
    }
}
