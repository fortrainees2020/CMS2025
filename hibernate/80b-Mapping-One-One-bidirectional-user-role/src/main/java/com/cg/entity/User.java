package com.cg.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name="OneOneBiUser1")
public class User {

    @Id
    private int userId;
    private String userName;
    private String userPWD;

    //@OneToOne(targetEntity=Role.class,cascade=CascadeType.ALL)  
    @OneToOne
   @JoinColumn(name = "role_id")  // Foreign key column in the User table
    private Role role;

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPWD() {
        return userPWD;
    }

    public void setUserPWD(String userPWD) {
        this.userPWD = userPWD;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
