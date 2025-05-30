package com.example;


import javax.persistence.*;
import java.util.List;

public class EmpService {
    private EntityManager em;

    public EmpService(EntityManager em) {
        this.em = em;
    }

    public void createEmp(String name, double salary) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Emp emp = new Emp();
        emp.setName(name);
        emp.setSalary(salary);
        em.persist(emp); //SAVE THE RECORD
        tx.commit();
    }

    public Emp getEmp(int id) {
        return em.find(Emp.class, id);
    }

    public List<Emp> getAllEmps() {
        return em.createQuery("SELECT e FROM Emp e", Emp.class).getResultList();
    }

    public void updateEmp(int id, String name, double salary) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Emp emp = em.find(Emp.class, id);
        if (emp != null) {
            emp.setName(name);
            emp.setSalary(salary);
        }
        tx.commit();
    }

    public void deleteEmp(int id) {
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Emp emp = em.find(Emp.class, id);
        if (emp != null) {
            em.remove(emp);
        }
        tx.commit();
    }
}
