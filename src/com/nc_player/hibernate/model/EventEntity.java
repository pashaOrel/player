package com.nc_player.hibernate.model;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by beno on 08.04.2016.
 */
@Entity
@Table(name = "event", schema = "public", catalog = "jbossewstestappplaye")
public class EventEntity {
    private long id;
    private String description;
    private String picture;
    private String title;
    private String link;
    private String hash;
    private Date date;

    public EventEntity() {
    }

    public EventEntity(String description, String picture, String title, String link, String hash, Date date) {
        this.description = description;
        this.picture = picture;
        this.title = title;
        this.link = link;
        this.hash = hash;
        this.date = date;
    }

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "event")
    public List<EventOfUserEntity> getEventOfUserEntities() {
        return eventOfUserEntities;
    }

    public void setEventOfUserEntities(List<EventOfUserEntity> eventOfUserEntities) {
        this.eventOfUserEntities = eventOfUserEntities;
    }

    private List<EventOfUserEntity> eventOfUserEntities;


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
    @Column(name = "title", nullable = false, length = 255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "link", nullable = true, length = -1)
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Basic
    @Column(name = "date", nullable = true)
    @Temporal(value = TemporalType.DATE)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventEntity that = (EventEntity) o;

        if (id != that.id) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (picture != null ? !picture.equals(that.picture) : that.picture != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (link != null ? !link.equals(that.link) : that.link != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "hash", nullable = true, length = -1)
    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
