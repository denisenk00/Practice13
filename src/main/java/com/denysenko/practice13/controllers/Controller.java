package com.denysenko.practice13.controllers;

import com.denysenko.practice13.dao.EmpDAO;
import com.denysenko.practice13.model.Department;
import com.denysenko.practice13.model.Employee;
import com.denysenko.practice13.model.Salgrade;
import com.denysenko.practice13.views.ConsoleView;

public class Controller {
    private ConsoleView view;
    private EmpDAO dao;

    public Controller(ConsoleView view, EmpDAO dao) {
        this.view = view;
        this.dao = dao;
    }

    public void printEmployee(int id) {
        try {
            Employee emp = dao.getEmp(id);
            Department dep = dao.getDepartment(emp.getDeptNumber());
            Salgrade sal = dao.getSalgrade(emp.getSalary());
            view.printEmployeeInfo(emp, dep, sal);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            view.printWaitingMessage();
        }
    }

    public void deleteEmployee(int id) {
        try {
            dao.removeEmp(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            view.printWaitingMessage();
        }
    }

    public void addEmployee(Employee emp) {
        try {
            dao.insertEmp(emp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            view.printWaitingMessage();
        }
    }


    public void mainMenu() {
        int input = 0;
        int id;
        while (input != 3) {
            view.printMainMenu();
            input = view.getUserInput();
            switch (input) {
                case 0:
                    id = view.printMenu();
                    printEmployee(id);
                    break;
                case 1:
                    Employee emp = view.inputEmployee();
                    addEmployee(emp);
                    break;
                case 2:
                    id = view.deleteMenu();
                    deleteEmployee(id);
                    break;
                case 3:
                    System.exit(0);
                    break;
            }
        }
    }
}
