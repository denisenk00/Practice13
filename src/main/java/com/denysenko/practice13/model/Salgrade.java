package com.denysenko.practice13.model;

public class Salgrade {
    private double minSalary;
    private double maxSalary;
    private int grade;

    public Salgrade() {
    }

    public Salgrade(double minSalary, double maxSalary, int grade) {
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.grade = grade;
    }

    public double getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(double minSalary) {
        this.minSalary = minSalary;
    }

    public double getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(double maxSalary) {
        this.maxSalary = maxSalary;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Salgrade{" +
                "minSalary=" + minSalary +
                ", maxSalary=" + maxSalary +
                ", grade=" + grade +
                '}';
    }
}
