package com.nc_player.hibernate.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by beno on 08.04.2016.
 */
@Entity
@Table(name = "user", schema = "public", catalog = "jbossewstestappplaye")
public class UserEntity {
    private long id;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private UserTypeEntity userType;
    private List<TrackListEntity> lists;
    private List<TrackOfUserEntity> trackOfUserEntities;
    private List<EventOfUserEntity> eventOfUserEntities;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "user")
    public List<TrackOfUserEntity> getTrackOfUserEntities() {
        return trackOfUserEntities;
    }

    public void setTrackOfUserEntities(List<TrackOfUserEntity> trackOfUserEntities) {
        this.trackOfUserEntities = trackOfUserEntities;
    }

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "user")
    public List<EventOfUserEntity> getEventOfUserEntities() {
        return eventOfUserEntities;
    }

    public void setEventOfUserEntities(List<EventOfUserEntity> eventOfUserEntities) {
        this.eventOfUserEntities = eventOfUserEntities;
    }



    public UserEntity(String email, String firstName, String lastName, String password, UserTypeEntity userType) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.userType = userType;
    }

    public UserEntity() {
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
    @Column(name = "email", nullable = false, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "first_name", nullable = false, length = 255)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = true, length = 255)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "password", nullable = false, length = -1)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != that.id) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn (name = "type")
    public UserTypeEntity getUserType() {
        return userType;
    }

    public void setUserType(UserTypeEntity userType) {
        this.userType = userType;
    }

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "user")
    public List<TrackListEntity> getLists() {
        return lists;
    }

    public void setLists(List<TrackListEntity> lists) {
        this.lists = lists;
    }

}
