package com.nc_player.hibernate.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by beno on 08.04.2016.
 */
@Entity
@Table(name = "track_list", schema = "public", catalog = "jbossewstestappplaye")
public class TrackListEntity {
    private long id;
    private String name;
    private long order;
    private UserEntity user;
    private List<TrackToListEntity> trackToListEntities;

    public TrackListEntity(String name, UserEntity user) {
        this.name = name;
        this.user = user;
    }

    public TrackListEntity() {
    }

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "list")
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
    @Column(name = "order1", nullable = false)
    public long getOrder() {
        return order;
    }

    public void setOrder(long order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrackListEntity that = (TrackListEntity) o;

        if (id != that.id) return false;
        if (order != that.order) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (int) (order ^ (order >>> 32));
        return result;
    }

    @ManyToOne
    @JoinColumn (name = "user_id")
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

}
