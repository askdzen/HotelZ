package com.epam.ad.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Admin on 1/9/2015.
 */
@Entity
@Table(name = "BOOKINGTABLE", schema = "PUBLIC", catalog = "HOTEL")
public class BookingtableEntity {
    private int id;
    private Integer roomNo;
    private Integer userId;
    private Timestamp dateFro;
    private Timestamp dateTo;
    private Boolean confirmed;
    private Boolean isdeleted;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "ROOM_NO")
    public Integer getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(Integer roomNo) {
        this.roomNo = roomNo;
    }

    @Basic
    @Column(name = "USER_ID")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookingtableEntity that = (BookingtableEntity) o;

        if (id != that.id) return false;
        if (roomNo != null ? !roomNo.equals(that.roomNo) : that.roomNo != null) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (roomNo != null ? roomNo.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "DATE_FRO")
    public Timestamp getDateFro() {
        return dateFro;
    }

    public void setDateFro(Timestamp dateFro) {
        this.dateFro = dateFro;
    }

    @Basic
    @Column(name = "DATE_TO")
    public Timestamp getDateTo() {
        return dateTo;
    }

    public void setDateTo(Timestamp dateTo) {
        this.dateTo = dateTo;
    }

    @Basic
    @Column(name = "CONFIRMED")
    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    @Basic
    @Column(name = "ISDELETED")
    public Boolean getIsdeleted() {
        return isdeleted;
    }

    public void setIsdeleted(Boolean isdeleted) {
        this.isdeleted = isdeleted;
    }
}
