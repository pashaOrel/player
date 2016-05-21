package com.nc_player.hibernate.model;

import javax.persistence.*;

/**
 * Created by beno on 10.04.2016.
 */
@Entity
@Table(name = "event_of_user", schema = "public", catalog = "jbossewstestappplaye")
public class EventOfUserEntity {
    private long id;
    private long order;
    private UserEntity user;
    private EventEntity event;

    public EventOfUserEntity() {
    }

    public EventOfUserEntity(UserEntity user, EventEntity event) {
        this.user = user;
        this.event = event;
    }

    @ManyToOne
    @JoinColumn (name = "user_id")
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @ManyToOne
    @JoinColumn (name = "event_id")
    public EventEntity getEvent() {
        return event;
    }

    public void setEvent(EventEntity event) {
        this.event = event;
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

        EventOfUserEntity that = (EventOfUserEntity) o;

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
