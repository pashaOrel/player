package com.nc_player.hibernate.model;

import javax.persistence.*;

/**
 * Created by beno on 10.04.2016.
 */
@Entity
@Table(name = "track_to_list", schema = "public", catalog = "jbossewstestappplaye")
public class TrackToListEntity {
    private long id;
    private long order;
    private TrackEntity track;
    private TrackListEntity list;

    public TrackToListEntity(TrackEntity track, TrackListEntity list) {
        this.track = track;
        this.list = list;
    }

    public TrackToListEntity() {
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
    @JoinColumn (name = "track_list_id")
    public TrackListEntity getList() {
        return list;
    }

    public void setList(TrackListEntity list) {
        this.list = list;
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

        TrackToListEntity that = (TrackToListEntity) o;

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
}
