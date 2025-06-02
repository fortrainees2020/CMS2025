package com.cg.dao;

import java.util.List;

import com.cg.entity.Role;
import com.cg.entity.User;
import com.cg.util.JPAUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class UserRoleCRUD {

    // CREATE operation
    public void createUser(User user) {
    	 EntityManager em =JPAUtil.getEntityManagerFactory().createEntityManager(); 
        EntityTransaction transaction = em.getTransaction();
        
        try {
            transaction.begin();
            em.persist(user);  // Save User and the Role
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

    // READ operation (get user by ID)
    public User getUserById(int userId) {
    	 EntityManager em =JPAUtil.getEntityManagerFactory().createEntityManager(); 
        User user = null;
        try {
            user = em.find(User.class, userId);  // Retrieve user by ID
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return user;
    }

    // READ operation (get all users)
    public List<User> getAllUsers() {
    	 EntityManager em =JPAUtil.getEntityManagerFactory().createEntityManager(); 
        List<User> users = null;
        try {
            Query query = em.createQuery("SELECT u FROM User u");
            users = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return users;
    }

    // UPDATE operation
    public void updateUser(int userId, String newUserName, String newPassword) {
    	 EntityManager em =JPAUtil.getEntityManagerFactory().createEntityManager(); 
        EntityTransaction transaction = em.getTransaction();
        
        try {
            transaction.begin();
            User user = em.find(User.class, userId);  // Find the User
            if (user != null) {
                user.setUserName(newUserName);
                user.setUserPWD(newPassword);
                em.merge(user);  // Merge the updated user
                transaction.commit();
                System.out.println("User updated successfully.");
            } else {
                System.out.println("User not found.");
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // DELETE operation
    public void deleteUser(int userId) {
    	 EntityManager em =JPAUtil.getEntityManagerFactory().createEntityManager(); 
        EntityTransaction transaction = em.getTransaction();
        
        try {
            transaction.begin();
            User user = em.find(User.class, userId);  // Find the User
            if (user != null) {
                em.remove(user);  // Remove the User
                transaction.commit();
                System.out.println("User deleted successfully.");
            } else {
                System.out.println("User not found.");
            }
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // CREATE Role operation
    public void createRole(Role role) {
    	 EntityManager em =JPAUtil.getEntityManagerFactory().createEntityManager(); 
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

    // GET Role by ID operation
    public Role getRoleById(int roleId) {
    	 EntityManager em =JPAUtil.getEntityManagerFactory().createEntityManager(); 
    	 
        Role role = null;
        try {
            role = em.find(Role.class, roleId);  // Retrieve role by ID
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return role;
    }

   
}
