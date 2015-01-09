package com.epam.ad.entity;

import com.epam.ad.dao.Identified;

import javax.inject.Named;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 *
 */
//@Entity
//@Named("user")
//@Table(name = "USER")
//@NamedQuery(name = "User.getAll", query = "select u from User u")
public class User implements Identified {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)


    private Integer id;

//    @Column(name = "LOGIN")
    private String username;

//    @Column(name = "PASSWORD")
    private String password;

//    @Column(name = "ROLE")
    private String role;

//    @Column(name = "ISDELETED")
    private boolean isDeleted;

    public User() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String toString() {
        return "User {"+
                "id="+id+
                ", username="+ username +'\''+
                ", password="+password+
                ", role"+role+'}';
    }
}
