package com.denysenko.practice13.dao;

import com.denysenko.practice13.model.Department;
import com.denysenko.practice13.model.Employee;
import com.denysenko.practice13.model.Salgrade;

public interface EmpDAO {
    void connect() throws Exception;

    void disconnect();

    Employee getEmp(int empNumber) throws Exception;

    void insertEmp(Employee emp) throws Exception;

    void removeEmp(int empNumber) throws Exception;

    Department getDepartment(int deptNumber) throws Exception;

    Salgrade getSalgrade(double salary) throws Exception;
}
