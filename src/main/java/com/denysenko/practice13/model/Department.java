package com.denysenko.practice13.model;

public class Department {
    private int deptNumber;
    private String deptName;
    private String location;

    public Department() {
    }

    public Department(int deptNumber, String deptName, String location) {
        this.deptNumber = deptNumber;
        this.deptName = deptName;
        this.location = location;
    }

    public int getDeptNumber() {
        return deptNumber;
    }

    public void setDeptNumber(int deptNumber) {
        this.deptNumber = deptNumber;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Department{" +
                "deptNumber=" + deptNumber +
                ", deptName='" + deptName + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
