package com.cg.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name="mmUser1")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String userName;
    private String userPWD;

    // Many-to-Many relationship with Role
    @ManyToMany
    @JoinTable(
        name = "User_Role1", // Join table name
        joinColumns = @JoinColumn(name = "user_id"), // Foreign key to User
        inverseJoinColumns = @JoinColumn(name = "role_id") // Foreign key to Role
    )
    private List<Role> roles;

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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
