package com.example;

import javax.persistence.Entity;

@Entity
public class FullTimeEmployee extends Employee {
    private double salary;

    public FullTimeEmployee() {}

    public FullTimeEmployee(String name, double salary) {
        super(name);
        this.salary = salary;
    }

    // Getter and Setter
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
