package com.denysenko.practice13.model;

import java.time.LocalDate;

public class Employee {
    private int epmNumber;
    private String name;
    private String job;
    private int manager;
    private LocalDate hireDate;
    private double salary;
    private double commission;
    private int deptNumber;

    public Employee() {
    }

    public Employee(int epmNumber, String name, String job, int manager,
                    LocalDate hireDate, double salary, double commission, int deptNumber) {
        this.epmNumber = epmNumber;
        this.name = name;
        this.job = job;
        this.manager = manager;
        this.hireDate = hireDate;
        this.salary = salary;
        this.commission = commission;
        this.deptNumber = deptNumber;
    }

    public int getEpmNumber() {
        return epmNumber;
    }

    public void setEpmNumber(int epmNumber) {
        this.epmNumber = epmNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getManager() {
        return manager;
    }

    public void setManager(int manager) {
        this.manager = manager;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }

    public int getDeptNumber() {
        return deptNumber;
    }

    public void setDeptNumber(int deptNumber) {
        this.deptNumber = deptNumber;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "epmNumber=" + epmNumber +
                ", name='" + name + '\'' +
                ", job='" + job + '\'' +
                ", manager=" + manager +
                ", hireDate=" + hireDate +
                ", salary=" + salary +
                ", commission=" + commission +
                ", deptNumber=" + deptNumber +
                '}';
    }
}
