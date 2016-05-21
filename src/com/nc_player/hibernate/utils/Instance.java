package com.nc_player.hibernate.utils;

import com.nc_player.RSS.RSS;
import com.nc_player.hibernate.POJOs.*;
import com.nc_player.hibernate.model.*;
import org.hibernate.Query;
import org.hibernate.Session;
import com.nc_player.Constant;


import java.util.*;


/**
 * Created by beno on 09.04.2016.
 */

public class Instance {

    private static class Holder{
        final static Instance instance = new Instance();
    }

    public static Session getSession() {
        return session;
    }
    private static Converter converter = Converter.getConverter();

    private static Session session = HibernateSessionFactory.getSessionFactory().openSession();


    private Instance(){
    }

    public static Instance getInstance(){
        return Holder.instance;
    }

    // -1 если неверный пароль, -2 если нет пользователя, id если залогинились
    public long validate(String log, String pas){
        if (isExist(log)){
            Query query = session.createQuery("from UserEntity where email = :a and password = :b");
            query.setParameter("a",log);
            query.setParameter("b",pas);
            List<UserEntity> list = query.list();
            if (list.size() == 0) return -1;
            else return list.get(0).getId();
        }
        else return -2;
    }

    private boolean isExist (String log){
        Query query = session.createQuery("from UserEntity where email = :a");
        query.setParameter("a", log);
        List<UserEntity> list = query.list();
        if (list.size() != 0) return true;
        else return false;
    }

    public long insertUser (String email, String firstname, String lastname, String pas ){
        if (!isExist(email)){
            session.beginTransaction();
            Query query = session.createQuery("from UserTypeEntity where name = 'user'");
            List<UserTypeEntity> list1 = query.list();
            UserTypeEntity type = list1.get(0);
            UserEntity user = new UserEntity(email,firstname,lastname,pas, type);
            session.save(user);
            session.getTransaction().commit();
            return user.getId();
        }
        else return -1;
    }

    public List<Track> getSongs (long id, boolean isGlobal){
        if (!isGlobal){
            Query query1 = session.createQuery("select t.track from TrackOfUserEntity as t inner join t.user as tu where tu.id =:a order by t.order");
            query1.setParameter("a", id);
            return converter.convertTracksList(query1.list());
        }
        else {
            Query query1 = session.createQuery("select t from TrackEntity as t order by t.id desc");
            query1.setMaxResults(Constant.NUMBER_OF_ROWS);
            List<TrackEntity> trackEntities = query1.list();
            Collections.shuffle(trackEntities);
            return converter.convertTracksList(trackEntities);
        }
    }

    public List<TrackList> getLists (long id){
        Query query1 = session.createQuery("select tl from TrackListEntity as tl inner join tl.user as tu where tu.id =:a order by tl.order");
        query1.setParameter("a", id);
        return converter.convertTrackListsList(query1.list());
    }

    public List<Artist> getArtists (long id, boolean isGlobal){
        if (!isGlobal) {
            Query query1 = session.createQuery("select t.track.artistName from TrackOfUserEntity as t inner join t.user as tu where tu.id =:a group by t.track.artistName");
            query1.setParameter("a", id);
            List<String> name = query1.list();
            List<ArtistEntity> artistEntities = new ArrayList<ArtistEntity>();
            for ( String t: name) {
                Query query = session.createQuery("select a from ArtistEntity as a where a.name = :b");
                query.setParameter("b", t);
                List<ArtistEntity> tmp = query.list();
                if (tmp.size()!=0) {
                    artistEntities.add(tmp.get(0));
                }
            }
            return converter.convertArtisList(artistEntities);
        }
        else {
            Query query1 = session.createQuery("select t from ArtistEntity as t order by t.id desc");
            query1.setMaxResults(Constant.NUMBER_OF_ROWS);
            List<ArtistEntity> artistEntities = query1.list();
            Collections.shuffle(artistEntities);
            return converter.convertArtisList(artistEntities);
        }
    }

    public List<Album> getAlbums(long id){
        Query query1 = session.createQuery("select t from AlbumEntity as t order by t.id desc");
        query1.setMaxResults(Constant.NUMBER_OF_ROWS);
        List<AlbumEntity> albumEntities = query1.list();
        Collections.shuffle(albumEntities);
        return converter.convertAlbumList(albumEntities);
    }

    public List<Event> getEvents (long id, boolean isGlobal){
        if (!isGlobal){
            Query query1 = session.createQuery("select t.event from EventOfUserEntity as t inner join t.user as tu where tu.id =:a order by t.order");
            query1.setParameter("a", id);
            return converter.convertEventsList(query1.list());
        }
        else {
            Query query1 = session.createQuery("select t from EventEntity as t order by t.date desc");
            query1.setMaxResults(Constant.NUMBER_OF_ROWS);
            return converter.convertEventsList(query1.list());
        }
    }

    public List<Track> getTracksFromList(long listId){
        Query query1 = session.createQuery("select t.track from TrackToListEntity as t inner join t.list as tu where tu.id =:a order by t.order");
        query1.setParameter("a", listId);
        return converter.convertTracksList(query1.list());
    }

    public List<Album> getAlbumsOfArtist(long artistId){
        Query query1 = session.createQuery("select t from AlbumEntity as t where t.artistEntity.id =:a order by t.name");
        query1.setParameter("a", artistId);
        return converter.convertAlbumList(query1.list());
    }

    public List<Track> getTracksOfAlbum(long albumId){
        Query query1 = session.createQuery("select t from TrackEntity as t where t.album.id =:a");
        query1.setParameter("a", albumId);
        return converter.convertTracksList(query1.list());
    }

    public void userLoadTrack(long id, String name, String file, String artistName){
        session.beginTransaction();
        UserEntity user = session.get(UserEntity.class, id);
        TrackEntity track = new TrackEntity(name,file,artistName, true);
        TrackOfUserEntity tu = new TrackOfUserEntity(user, track);
        Query query1 = session.createQuery("select max(t.order) from TrackOfUserEntity as t inner join t.user as tu where tu.id =:a");
        query1.setParameter("a", id);
        if (query1.list().get(0) == null){
            tu.setOrder(1);
            session.save(track);
            session.save(tu);
            session.getTransaction().commit();
        }
        else {
            long order = (Long) query1.list().get(0);
            tu.setOrder(order+1);
            session.save(track);
            session.save(tu);
            session.getTransaction().commit();
        }
    }

    public void userDeleteTrackFromTrackList(long trackId, long trackListId){
        session.beginTransaction();
        Query query1 = session.createQuery("select t from TrackToListEntity as t where t.track.id=:b and t.list.id =:a");
        query1.setParameter("a", trackListId);
        query1.setParameter("b", trackId);
        if (query1.list().get(0) == null){
            session.getTransaction().commit();
        }
        else {
            TrackToListEntity trackToListEntity = (TrackToListEntity) query1.list().get(0);
            session.delete(trackToListEntity);
            session.getTransaction().commit();
        }
    }

    //возвращает -1, если такой трек уже добавляли
    public int userAddTrack(long id, long trackId){
        Query query = session.createQuery("select t from TrackOfUserEntity as t where t.user.id = :a and t.track.id = :b");
        query.setParameter("a",id);
        query.setParameter("b",trackId);
        if(query.list().size() == 0) {
            session.beginTransaction();
            TrackOfUserEntity trackOfUserEntity = new TrackOfUserEntity(session.get(UserEntity.class, id), session.get(TrackEntity.class, trackId));
            Query query1 = session.createQuery("select max(t.order) from TrackOfUserEntity as t inner join t.user as tu where tu.id =:a");
            query1.setParameter("a", id);
            if (query1.list().get(0) == null) {
                trackOfUserEntity.setOrder(1);
                session.save(trackOfUserEntity);
                session.getTransaction().commit();
                return 0;
            } else {
                long order = (Long) query1.list().get(0);
                trackOfUserEntity.setOrder(order + 1);
                session.save(trackOfUserEntity);
                session.getTransaction().commit();
                return 0;
            }
        }
        else return -1;
    }

    public void userAddTrackToList(long trackId, long listId){
        session.beginTransaction();
        TrackToListEntity trackToListEntity = new TrackToListEntity(session.get(TrackEntity.class, trackId), session.get(TrackListEntity.class, listId));
        Query query1 = session.createQuery("select max(t.order) from TrackToListEntity as t inner join t.list as tu where tu.id =:a");
        query1.setParameter("a", listId);
        if (query1.list().get(0) == null){
            trackToListEntity.setOrder(1);
            session.save(trackToListEntity);
            session.getTransaction().commit();
        }
        else {
            long order = (Long) query1.list().get(0);
            trackToListEntity.setOrder(order+1);
            session.save(trackToListEntity);
            session.getTransaction().commit();
        }
    }

    //возвращает -1, если трек-лист с таким именем уже существует
    public long userCreateList(long id, String name){
        Query query = session.createQuery("select t from TrackListEntity as t where t.user.id = :a and t.name like :b");
        query.setParameter("a", id);
        query.setParameter("b",name);
        if(query.list().size() == 0) {
            session.beginTransaction();
            TrackListEntity trackListEntity = new TrackListEntity(name, session.get(UserEntity.class, id));
            Query query1 = session.createQuery("select max(t.order) from TrackListEntity as t inner join t.user as tu where tu.id =:a");
            query1.setParameter("a", id);
            if (query1.list().get(0) == null) {
                trackListEntity.setOrder(1);
                session.save(trackListEntity);
                session.getTransaction().commit();
            } else {
                long order = (Long) query1.list().get(0);
                trackListEntity.setOrder(order + 1);
                session.save(trackListEntity);
                session.getTransaction().commit();
            }
            return trackListEntity.getId();
        }
        else return -1;
    }

    public void insertMetadata (List<Metadata> metadatalist){
        for (Metadata metadata : metadatalist) {
            session.beginTransaction();
            if (metadata.getArtistEntity() == null) {
                metadata.getTrackEntity().setArtistName("Unnamed");
                session.save(metadata.getTrackEntity());
                session.getTransaction().commit();
            } else {
                Query query1 = session.createQuery("from ArtistEntity as t where t.name =:a");
                query1.setParameter("a", metadata.getArtistEntity().getName());
                List<ArtistEntity> list = query1.list();
                if (list.size() == 0) {
                    session.save(metadata.getArtistEntity());
                    if (metadata.getAlbumEntity() == null) {
                        AlbumEntity albumEntity = new AlbumEntity();
                        albumEntity.setName("defaultTech");
                        albumEntity.setArtistEntity(metadata.getArtistEntity());
                        session.save(albumEntity);
                        metadata.getTrackEntity().setAlbum(albumEntity);
                        session.save(metadata.getTrackEntity());
                        session.getTransaction().commit();
                    } else {
                        metadata.getAlbumEntity().setArtistEntity(metadata.getArtistEntity());
                        metadata.getTrackEntity().setAlbum(metadata.getAlbumEntity());
                        session.save(metadata.getAlbumEntity());
                        session.save(metadata.getTrackEntity());
                        session.getTransaction().commit();
                    }
                } else {
                    ArtistEntity artistEntity = list.get(0);
                    if (metadata.getAlbumEntity() == null) {
                        Query query2 = session.createQuery("from AlbumEntity as t where t.name =:a and t.artistEntity.id =:b");
                        query2.setParameter("a", "defaultTech");
                        query2.setParameter("b", artistEntity.getId());
                        List<AlbumEntity> list1 = query2.list();
                        if (list1.size() == 0) {
                            AlbumEntity albumEntity = new AlbumEntity();
                            albumEntity.setName("defaultTech");
                            albumEntity.setArtistEntity(artistEntity);
                            metadata.getTrackEntity().setAlbum(albumEntity);
                            session.save(albumEntity);
                            session.save(metadata.getTrackEntity());
                            session.getTransaction().commit();
                        } else {
                            metadata.getTrackEntity().setAlbum(list1.get(0));
                            session.save(metadata.getTrackEntity());
                            session.getTransaction().commit();
                        }
                    } else {
                        Query query3 = session.createQuery("from AlbumEntity as t where t.name =:a and t.artistEntity.id =:b");
                        query3.setParameter("a", metadata.getAlbumEntity().getName());
                        query3.setParameter("b", artistEntity.getId());
                        List<AlbumEntity> list2 = query3.list();
                        if (list2.size() == 0) {
                            metadata.getAlbumEntity().setArtistEntity(artistEntity);
                            metadata.getTrackEntity().setAlbum(metadata.getAlbumEntity());
                            session.save(metadata.getAlbumEntity());
                            session.save(metadata.getTrackEntity());
                            session.getTransaction().commit();
                        } else {
                            metadata.getTrackEntity().setAlbum(list2.get(0));
                            session.save(metadata.getTrackEntity());
                            session.getTransaction().commit();
                        }
                    }
                }
            }
        }
    }

    public List<Track> searchTracks (long id, String search, boolean isGlobal, boolean byName){
        if (isGlobal){
            if (byName) {
                Query query1 = session.createQuery("select t from TrackEntity as t where lower(t.name) like :a order by t.id");
                query1.setMaxResults(Constant.NUMBER_OF_ROWS);
                query1.setParameter("a", "%" + search.toLowerCase() +"%");
                return converter.convertTracksList(query1.list());
            } else {
                Query query1 = session.createQuery("select t from TrackEntity as t where lower(t.artistName) like :a order by t.id");
                query1.setMaxResults(Constant.NUMBER_OF_ROWS);
                query1.setParameter("a", "%" + search.toLowerCase() +"%");
                return converter.convertTracksList(query1.list());
            }
        } else {
            if (byName) {
                Query query1 = session.createQuery("select t.track from TrackOfUserEntity as t inner join t.user as tu where tu.id =:b and lower(t.track.name) like :a order by t.order");
                query1.setParameter("a", "%" + search.toLowerCase() +"%");
                query1.setParameter("b", id);
                return converter.convertTracksList(query1.list());
            } else {
                Query query1 = session.createQuery("select t.track from TrackOfUserEntity as t inner join t.user as tu where tu.id =:b and lower(t.track.artistName) like :a order by t.order");
                query1.setParameter("a", "%" + search.toLowerCase() +"%");
                query1.setParameter("b", id);
                return converter.convertTracksList(query1.list());
            }
        }
    }

    public List<Artist> searchArtists (long id, String search, boolean isGlobal){
        if (isGlobal) {
            Query query1 = session.createQuery("select t from ArtistEntity as t where lower(t.name) like :a order by t.id");
            query1.setMaxResults(Constant.NUMBER_OF_ROWS);
            query1.setParameter("a", "%" + search.toLowerCase() +"%");
            return converter.convertArtisList(query1.list());
        }
        else {
            List<Artist> artist = getArtists(id, false);
            List<Artist> list = new ArrayList<>();
            for (Artist a: artist) {
                String s = a.getName().toLowerCase();
                if (s.contains(search.toLowerCase())){
                    list.add(a);
                }
            }
            return list;
        }
    }

    public List<Event> searchEvents (long id, String search, boolean isGlobal){
        if (isGlobal) {
            Query query1 = session.createQuery("select t from EventEntity as t where lower(t.title) like :a order by t.date desc ");
            query1.setMaxResults(Constant.NUMBER_OF_ROWS);
            query1.setParameter("a", "%" + search.toLowerCase() +"%");
            return converter.convertEventsList(query1.list());
        }
        else {
            Query query1 = session.createQuery("select t.event from EventOfUserEntity as t inner join t.user as tu where tu.id =:b and lower(t.event.title) like :a order by t.order");
            query1.setParameter("a", "%" + search.toLowerCase() +"%");
            query1.setParameter("b", id);
            return converter.convertEventsList(query1.list());
        }
    }

    public List<Album> searchAlbums(long id, String search){
        Query query1 = session.createQuery("select t from AlbumEntity as t where lower(t.name) like :a order by t.id ");
        query1.setMaxResults(Constant.NUMBER_OF_ROWS);
        query1.setParameter("a", "%" + search.toLowerCase() +"%");
        return converter.convertAlbumList(query1.list());
    }

    public List<TrackList> searchTrackLists(long id, String search){
        Query query1 = session.createQuery("select t from TrackListEntity as t where t.user.id=:b and lower(t.name) like :a order by t.order");
        query1.setParameter("a", "%" + search.toLowerCase() +"%");
        query1.setParameter("b", id);
        return converter.convertTrackListsList(query1.list());
    }

    //Добавить лист эвентов
    public void insertEvents (List<RSS> list){
        session.beginTransaction();
        for (RSS rss : list) {
            EventEntity eventEntity = new EventEntity(rss.getDescription(), rss.getPicture(), rss.getTitle(), rss.getLink(), rss.getHash(), rss.getPubDate());
            session.save(eventEntity);
        }
        session.getTransaction().commit();
    }

    //Получить все хэши эвентов
    public List<String> getHashEvent(){
        Query query1 = session.createQuery("select t.hash from EventEntity as t");
        return query1.list();
    }

    //возвращает -1, если такое событие уже добавляли
    public int userAddEvent (long id, long eventId){
        Query query = session.createQuery("select t from EventOfUserEntity as t where t.user.id = :a and t.event.id = :b");
        query.setParameter("a",id);
        query.setParameter("b",eventId);
        if(query.list().size() == 0){
            session.beginTransaction();
            EventOfUserEntity eventOfUserEntity = new EventOfUserEntity(session.get(UserEntity.class, id), session.get(EventEntity.class, eventId));
            Query query1 = session.createQuery("select max(t.order) from EventOfUserEntity as t inner join t.user as tu where tu.id =:a");
            query1.setParameter("a", id);
            if (query1.list().get(0) == null){
                eventOfUserEntity.setOrder(1);
                session.save(eventOfUserEntity);
                session.getTransaction().commit();
                return 0;
            }
            else {
                long order = (Long) query1.list().get(0);
                eventOfUserEntity.setOrder(order+1);
                session.save(eventOfUserEntity);
                session.getTransaction().commit();
                return 0;
            }
        }
        else return -1;
    }

    public void userDeleteEvent(long id, long eventId){
        session.beginTransaction();
        Query query1 = session.createQuery("select t from EventOfUserEntity as t where t.user.id=:b and t.event.id =:a");
        query1.setParameter("a", eventId);
        query1.setParameter("b", id);
        if (query1.list().get(0) == null){
            session.getTransaction().commit();
        }
        else {
            EventOfUserEntity eventOfUserEntity = (EventOfUserEntity) query1.list().get(0);
            session.delete(eventOfUserEntity);
            session.getTransaction().commit();
        }
    }

    public List<Artist> getNullArtist(){
        Query query1 = session.createQuery("select t from ArtistEntity as t where t.description is null and t.picture is null");
        return converter.convertArtisList(query1.list());
    }

    public void setArtistsData(List<Artist> list){
        session.beginTransaction();
        for (Artist artist : list) {
            if (artist.getDescription() !=null || artist.getPicture() != null) {
                ArtistEntity artistEntity = session.get(ArtistEntity.class, artist.getId());
                if(artist.getDescription() != null){
                    artistEntity.setDescription(artist.getDescription());
                }
                if(artist.getPicture() != null){
                    artistEntity.setPicture(artist.getPicture());
                }
                session.update(artistEntity);
            }
        }
        session.getTransaction().commit();
    }

    public void userDeleteTrack(long id, long trackId){
        Query query1 = session.createQuery("select t from TrackOfUserEntity as t where t.track.id =:a and t.user.id = :b");
        query1.setParameter("a", trackId);
        query1.setParameter("b", id);
        if(query1.list().get(0) != null){
            TrackOfUserEntity trackOfUserEntity = (TrackOfUserEntity) query1.list().get(0);
            session.beginTransaction();
            session.delete(trackOfUserEntity);
            session.getTransaction().commit();
        }
    }

    public void userDeleteTrackList (long trackListId){
        TrackListEntity trackListEntity = session.get(TrackListEntity.class, trackListId);
        session.beginTransaction();
        session.delete(trackListEntity);
        session.getTransaction().commit();
    }

    public static void close(){
        HibernateSessionFactory.getSessionFactory().close();
    }

}
