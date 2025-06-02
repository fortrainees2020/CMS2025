package com.cg.dao;

import com.cg.entity.Role;
import com.cg.entity.User;
import com.cg.util.JPAUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class UserRoleCRUD {

    // CREATE User and Role
    public void createUser(User user) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            // Save Role if not already saved
            if (user.getRole() != null) {
                em.persist(user.getRole());  // Save Role first (if necessary)
            }
            em.persist(user);  // Save User
            transaction.commit();
            System.out.println("User and Role created successfully.");
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // CREATE Role
    public void createRole(Role role) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(role);  // Save Role
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

    // READ User by ID
    public User getUserById(int userId) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        User user = null;
        try {
            user = em.find(User.class, userId);  // Retrieve User by ID
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return user;
    }

    // READ Role by ID
    public Role getRoleById(int roleId) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        Role role = null;
        try {
            role = em.find(Role.class, roleId);  // Retrieve Role by ID
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return role;
    }
}
