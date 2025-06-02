package com.cg.dao;

import java.util.List;
import com.cg.entity.Role;
import com.cg.entity.User;
import com.cg.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class UserRoleCRUD {

    // CREATE operation for User (many-to-one with Role)
    public void createUser(User user) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.merge(user);  // merge() copies the state of your detached entity into a managed instance.
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

    // CREATE operation for Role (one-to-many with User)
    public void createRole(Role role) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.merge(role);  // Save Role
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

    // READ operation (get users by roleId)
    public List<User> getUsersByRoleId(int roleId) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        List<User> users = null;
        try {
            Role role = em.find(Role.class, roleId);  // Retrieve role by ID
            if (role != null) {
                users = role.getUsers();  // Get the list of users associated with the role
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return users;
    }

	public Role findRoleById(int rid) {
	    EntityManager em = null;
	    try {
	        em = JPAUtil.getEntityManagerFactory().createEntityManager();
	        return em.find(Role.class, rid);
	    } finally {
	        if (em != null) {
	            em.close();
	        }
	    }
	}
    
  /*  // To Overcome Lazy initialization exception allocate the size
   * public List<User> getUsersByRoleId(int roleId) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        List<User> users = null;
        try {
            Role role = em.find(Role.class, roleId);  // Retrieve role by ID
            if (role != null) {
                // Initialize the 'users' collection while the session is still open
                role.getUsers().size();  // --------This forces the lazy loading of the users collection
                users = role.getUsers();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return users;
    }*/

    // Other CRUD operations...
}
