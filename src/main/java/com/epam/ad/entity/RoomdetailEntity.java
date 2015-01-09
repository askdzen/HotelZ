package com.epam.ad.entity;

import javax.persistence.*;

/**
 * Created by Admin on 1/9/2015.
 */
@Entity
@Table(name = "ROOMDETAIL", schema = "PUBLIC", catalog = "HOTEL")
public class RoomdetailEntity {
    private int id;
    private String roomType;
    private int roomRate;
    private String roomBed;
    private Integer roomNo;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomdetailEntity that = (RoomdetailEntity) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Basic
    @Column(name = "ROOM_TYPE")
    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    @Basic
    @Column(name = "ROOM_RATE")
    public int getRoomRate() {
        return roomRate;
    }

    public void setRoomRate(int roomRate) {
        this.roomRate = roomRate;
    }

    @Basic
    @Column(name = "ROOM_BED")
    public String getRoomBed() {
        return roomBed;
    }

    public void setRoomBed(String roomBed) {
        this.roomBed = roomBed;
    }

    @Basic
    @Column(name = "ROOM_NO")
    public Integer getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(Integer roomNo) {
        this.roomNo = roomNo;
    }
}
