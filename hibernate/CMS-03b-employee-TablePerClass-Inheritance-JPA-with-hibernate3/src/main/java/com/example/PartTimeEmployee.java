package com.example;

import javax.persistence.Entity;

@Entity
public class PartTimeEmployee extends Employee {
    private double hourlyWage;

    public PartTimeEmployee() {}

    public PartTimeEmployee(String name, double hourlyWage) {
        super(name);
        this.hourlyWage = hourlyWage;
    }

    public double getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(double hourlyWage) {
        this.hourlyWage = hourlyWage;
    }
}
