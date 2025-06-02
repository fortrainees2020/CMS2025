package com.cg.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="OneOneBiRole1")
public class Role {

    @Id
    private int roleId;
    private String roleName;
/*
 * The mappedBy attribute in the @OneToOne annotation on the Role class specifies the inverse side of the relationship.
 * The mappedBy attribute refers to the field role in the User class (this is where the actual relationship is managed).
This means the Role class is the non-owning side of the relationship. The owning side is the User class, and it will 
contain a reference to the Role entity.
The mappedBy = "role" means that in the User entity, there will be a field called role that links back to the Role entity. 
This field on the User class is responsible for managing the relationship.*/
    
    @OneToOne(mappedBy = "role")  // User owns the relationship . Role is property in User entity.
   // @OneToOne(targetEntity=Role.class)  
    private User user;

    // Getters and Setters
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
