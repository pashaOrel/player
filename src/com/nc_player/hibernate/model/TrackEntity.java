package com.nc_player.hibernate.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by beno on 08.04.2016.
 */
@Entity
@Table(name = "track", schema = "public", catalog = "jbossewstestappplaye")
public class TrackEntity {
    private long id;
    private String name;
    private String file;
    private String artistName;
    private String genre;
    private List<TrackOfUserEntity> trackOfUserEntities;
    private List<TrackToListEntity> trackToListEntities;
    private AlbumEntity album;

    public TrackEntity(String name, String file) {
        this.name = name;
        this.file = file;
    }

    public TrackEntity(String name, String file, String string, boolean isArtistName) {
        this.name = name;
        this.file = file;
        if (isArtistName) this.artistName = string;
        else this.genre = string;
    }

    @ManyToOne
    @JoinColumn (name = "album_id")
    public AlbumEntity getAlbum() {
        return album;
    }

    public void setAlbum(AlbumEntity album) {
        this.album = album;
    }


    public TrackEntity(String name, String file, String artistName, String genre) {
        this.name = name;
        this.file = file;
        this.artistName = artistName;
        this.genre = genre;
    }

    public TrackEntity() {
    }

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "track")
    public List<TrackOfUserEntity> getTrackOfUserEntities() {
        return trackOfUserEntities;
    }

    public void setTrackOfUserEntities(List<TrackOfUserEntity> trackOfUserEntities) {
        this.trackOfUserEntities = trackOfUserEntities;
    }

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "track")
    public List<TrackToListEntity> getTrackToListEntities() {
        return trackToListEntities;
    }

    public void setTrackToListEntities(List<TrackToListEntity> trackToListEntities) {
        this.trackToListEntities = trackToListEntities;
    }


    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "file", nullable = false, length = -1)
    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Basic
    @Column(name = "artist_name", nullable = false, length = 255)
    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrackEntity that = (TrackEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (file != null ? !file.equals(that.file) : that.file != null) return false;
        if (artistName != null ? !artistName.equals(that.artistName) : that.artistName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (file != null ? file.hashCode() : 0);
        result = 31 * result + (artistName != null ? artistName.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "genre", length = -1)
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
