package com.nc_player.hibernate.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by beno on 08.04.2016.
 */
@Entity
@Table(name = "artist", schema = "public", catalog = "jbossewstestappplaye")
public class ArtistEntity {
    private long id;
    private String description;
    private String picture;
    private String name;
    private List<AlbumEntity> albumEntities;

    public ArtistEntity() {
    }

    public ArtistEntity(String name) {
        this.name = name;
    }

    public ArtistEntity(String name, String string, Boolean isDescription) {
        this.name = name;
        if(isDescription){
            this.description = string;
        }
        else this.picture = string;

    }

    public ArtistEntity(String name, String description, String picture) {
        this.name = name;
        this.description = description;
        this.picture = picture;
    }

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "artistEntity")
    public List<AlbumEntity> getAlbumEntities() {
        return albumEntities;
    }

    public void setAlbumEntities(List<AlbumEntity> albumEntities) {
        this.albumEntities = albumEntities;
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
    @Column(name = "description", nullable = true, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

        ArtistEntity that = (ArtistEntity) o;

        if (id != that.id) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (picture != null ? !picture.equals(that.picture) : that.picture != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

}
