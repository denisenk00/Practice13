package com.denysenko.practice13.views;

import com.denysenko.practice13.model.Department;
import com.denysenko.practice13.model.Employee;
import com.denysenko.practice13.model.Salgrade;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ConsoleView {
    public static void clearScreen() {
        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    }

    public int getUserInput() {
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()) {
            scanner.next();
        }
        return scanner.nextInt();
    }

    public void printMainMenu() {
        clearScreen();
        System.out.println("0 get info about employee\n1 add employee \n2 delete employee\n3 exit");
    }

    public Employee inputEmployee() {
        clearScreen();
        Scanner scanner = new Scanner(System.in);
        Employee employee = new Employee();
        int empno = 0;
        System.out.println("Enter employee number: ");
        while(empno <= 0) {
            while (!scanner.hasNextInt()) {
                scanner.next();
            }
            empno = scanner.nextInt();
            if(empno <= 0) System.out.println("Employee number must be more than 0");
        }
        employee.setEpmNumber(empno);
        scanner.nextLine();
        System.out.println("Enter name: ");
        String name = scanner.nextLine();
        employee.setName(name.toUpperCase());
        System.out.println("Enter job: ");
        String job = scanner.nextLine();
        employee.setJob(job.toUpperCase());
        int mgr = -1;
        System.out.println("Enter manager id or \"0\" if no manager: ");
        while (mgr < 0) {
            while (!scanner.hasNextInt()) {
                scanner.next();
            }
            mgr = scanner.nextInt();
            if(mgr < 0) System.out.println("Manager id cannot be negative");
            if(mgr == empno){
                System.out.println("Manager id cannot be the same as employee number");
                mgr = -1;
            }
        }
        employee.setManager(mgr);
        scanner.nextLine();
        boolean ok = false;
        while (!ok) {
            try {
                System.out.println("Enter hire-date in format \"dd.MM.yyyy\": ");
                String s = scanner.nextLine();
                LocalDate date = LocalDate.parse(s, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                if (date.isAfter(LocalDate.now())) throw new Exception();
                employee.setHireDate(date);
                ok = true;
            } catch (Exception e) {
                System.out.println("Hire date was entered incorrectly or in future");
            }
        }
        System.out.println("Enter salary: ");
        double salary = -1;
        while (salary < 0) {
            while (!scanner.hasNextDouble()) {
                scanner.next();
            }
            salary = scanner.nextDouble();
            if(salary < 0) System.out.println("Salary cannot be negative");
        }
        employee.setSalary(salary);
        scanner.nextLine();
        System.out.println("Enter commission: ");
        double commission = -1;
        while (commission < 0) {
            while (!scanner.hasNextDouble()) {
                scanner.next();
            }
            commission = scanner.nextDouble();
            if (commission < 0) System.out.println("Commission cannot be negative");
        }
        employee.setCommission(commission);
        scanner.nextLine();
        System.out.println("Enter department number or \"0\" if no department: ");
        int deptno = -1;
        while (deptno < 0) {
            while (!scanner.hasNextInt()) {
                scanner.next();
            }
            deptno = scanner.nextInt();
            if (deptno < 0) System.out.println("Department number cannot be negative");
        }
        employee.setDeptNumber(deptno);
        return employee;
    }

    public int deleteMenu() {
        clearScreen();
        System.out.println("Enter employee number to delete: ");
        return getUserInput();
    }

    public int printMenu() {
        clearScreen();
        int id = 0;
        System.out.println("Enter employee number to print: ");
        while (id == 0) {
            id = getUserInput();
            if (id <= 0) {
                System.out.println("Employee id must be more than 0");
                id = 0;
            }
        }
        return id;
    }

    public void printEmployeeInfo(Employee empl, Department dep, Salgrade salgr) {
        StringBuilder builder = new StringBuilder();
        builder.append("Empno: ").append(empl.getEpmNumber()).append("\nName: ").append(empl.getName())
                .append("\nJob: ").append(empl.getJob()).append("\nManager: ").append(empl.getManager())
                .append("\nHire date: ").append(empl.getHireDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))
                .append("\nSalary: ").append(empl.getSalary()).append("\nCommission: ").append(empl.getCommission())
                .append("\nDepartment name: ").append(dep.getDeptName()).append("\nLocation: ").append(dep.getLocation())
                .append("\nSalgrade: ").append(salgr.getGrade());
        System.out.println(builder.toString());
        printWaitingMessage();
    }

    public void printWaitingMessage() {
        System.out.println("Press any key to continue...");
        new Scanner(System.in).nextLine();
    }
}
