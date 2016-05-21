package com.nc_player.hibernate.model;

import javax.persistence.*;

/**
 * Created by beno on 10.04.2016.
 */
@Entity
@Table(name = "track_of_user", schema = "public", catalog = "jbossewstestappplaye")
public class TrackOfUserEntity {
    private long id;
    private long order;
    private UserEntity user;
    private TrackEntity track;

    public TrackOfUserEntity(UserEntity user, TrackEntity track) {
        this.user = user;
        this.track = track;
    }

    public TrackOfUserEntity() {
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

        TrackOfUserEntity that = (TrackOfUserEntity) o;

        if (id != that.id) return false;
        if (order != that.order) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (order ^ (order >>> 32));
        return result;
    }
    @ManyToOne
    @JoinColumn (name = "track_id")
    public TrackEntity getTrack() {
        return track;
    }

    public void setTrack(TrackEntity track) {
        this.track = track;
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
