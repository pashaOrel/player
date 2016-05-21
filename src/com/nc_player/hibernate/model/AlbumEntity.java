package com.nc_player.hibernate.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by beno on 08.04.2016.
 */
@Entity
@Table(name = "album", schema = "public", catalog = "jbossewstestappplaye")
public class AlbumEntity {
    private long id;
    private String picture;
    private String name;
    private List<TrackEntity> trackEntities;
    private ArtistEntity artistEntity;

    public AlbumEntity() {
    }

    public AlbumEntity(String picture, String name) {
        this.picture = picture;
        this.name = name;
    }

    public AlbumEntity(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn (name = "artist_id")
    public ArtistEntity getArtistEntity() {
        return artistEntity;
    }

    public void setArtistEntity(ArtistEntity artistEntity) {
        this.artistEntity = artistEntity;
    }

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "album")
    public List<TrackEntity> getTrackEntities() {
        return trackEntities;
    }

    public void setTrackEntities(List<TrackEntity> trackEntities) {
        this.trackEntities = trackEntities;
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
    @Column(name = "picture", nullable = true, length = -1)
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AlbumEntity that = (AlbumEntity) o;

        if (id != that.id) return false;

        if (picture != null ? !picture.equals(that.picture) : that.picture != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));

        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

}
