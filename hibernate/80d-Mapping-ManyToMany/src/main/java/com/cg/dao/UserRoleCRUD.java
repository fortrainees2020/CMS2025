package com.cg.dao;

import java.util.List;
import com.cg.entity.Role;
import com.cg.entity.User;
import com.cg.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class UserRoleCRUD {

    // CREATE operation for User (Many-to-Many relationship with Role)
    public void createUser(User user) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(user);  // Save User and associate it with Roles
            transaction.commit();
            System.out.println("User created successfully.");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // CREATE operation for Role (Many-to-Many relationship with User)
    public void createRole(Role role) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(role);  // Save Role and associate it with Users
            transaction.commit();
            System.out.println("Role created successfully.");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public List<User> getUsersByRoleId(int roleId) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        List<User> users = null;
        try {
            Role role = em.find(Role.class, roleId);
            if (role != null) {
                // Initialize users collection while session is open
                role.getUsers().size();  // This forces the lazy loading
                users = role.getUsers();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return users;
    }


    // GET roles by userId (for Many-to-Many relationship)
    public List<Role> getRolesByUserId(int userId) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        List<Role> roles = null;
        try {
            User user = em.find(User.class, userId);
            if (user != null) {
                // Force initialization of roles while session is open
                user.getRoles().size();
                roles = user.getRoles();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return roles;
    }

    public Role findRoleById(int roleId) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        Role role = null;
        try {
            role = em.find(Role.class, roleId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return role;
    }


    // Other CRUD operations...
}
