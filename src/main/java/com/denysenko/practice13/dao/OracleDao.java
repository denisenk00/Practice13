package com.denysenko.practice13.dao;

import com.denysenko.practice13.model.Department;
import com.denysenko.practice13.model.Employee;
import com.denysenko.practice13.model.Salgrade;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.sql.*;

public class OracleDao implements EmpDAO {
    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;

    @Override
    public void connect() throws Exception {
        String url, driver, username, password;
        File file = new File("dataSource.xml");
        Document document;
        try {
            document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);
            XPath xpath = XPathFactory.newInstance().newXPath();
            url = (String) xpath.compile("//connection-url").evaluate(document, XPathConstants.STRING);
            driver = (String) xpath.compile("//driver-class").evaluate(document, XPathConstants.STRING);
            username = (String) xpath.compile("//user-name").evaluate(document, XPathConstants.STRING);
            password = (String) xpath.compile("//password").evaluate(document, XPathConstants.STRING);
            Class.forName(driver);
            connection = DriverManager.getConnection(url,
                    username, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new Exception("Connection to DataBase was failed");
        } catch (XPathExpressionException | ParserConfigurationException e) {
            throw new Exception("Problems with file dataSource.xml");
        }
    }

    @Override
    public void disconnect() {
        try {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Employee getEmp(int empNumber) throws Exception {
        connect();
        Employee emp = new Employee();
        try {
            statement = connection.prepareStatement("SELECT * FROM EMP WHERE EMPNO = ?");
            statement.setInt(1, empNumber);
            resultSet = statement.executeQuery();
            resultSet.next();
            System.out.println(resultSet.getString("ENAME"));
            emp.setEpmNumber(empNumber);
            emp.setName(resultSet.getString("ENAME"));
            emp.setJob(resultSet.getString("JOB"));
            emp.setManager(resultSet.getInt("MGR"));
            emp.setHireDate(resultSet.getDate("HIREDATE").toLocalDate());
            emp.setSalary(resultSet.getDouble("SAL"));
            emp.setCommission(resultSet.getDouble("COMM"));
            emp.setDeptNumber(resultSet.getInt("DEPTNO"));
        } catch (SQLException throwables) {
            throw new Exception("Employee with number " + empNumber + " was not found");
        }
        disconnect();
        return emp;
    }

    @Override
    public void insertEmp(Employee emp) throws Exception {
        connect();
        try {
            statement = connection.prepareStatement("INSERT INTO EMP(EMPNO, ENAME, JOB, MGR, HIREDATE, SAL, COMM, DEPTNO)" +
                    " VALUES (?,?, ?, ?, ?, ?, ?, ?)");
            statement.setInt(1, emp.getEpmNumber());
            statement.setString(2, emp.getName());
            statement.setString(3, emp.getJob());
            if (emp.getManager() == 0) statement.setNull(4, Types.NULL);
            else statement.setInt(4, emp.getManager());
            statement.setDate(5, java.sql.Date.valueOf(emp.getHireDate()));
            statement.setDouble(6, emp.getSalary());
            statement.setDouble(7, emp.getCommission());
            if (emp.getDeptNumber() == 0) statement.setNull(8, Types.NULL);
            else statement.setInt(8, emp.getDeptNumber());
            statement.execute();
            disconnect();
        } catch (SQLException e) {
            throw new Exception("Entered data is incorrect, check manager, department number and employee number");
        }
    }

    @Override
    public void removeEmp(int empNumber) throws Exception {
        connect();
        try {
            statement = connection.prepareStatement("DELETE FROM EMP WHERE EMPNO = ?");
            statement.setInt(1, empNumber);
            if(statement.executeUpdate() == 0) throw new Exception("Employee with number " + empNumber + " was not found");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        disconnect();
    }

    public Salgrade getSalgrade(double salary) throws Exception {
        connect();
        Salgrade salgrade = new Salgrade();
        try {
            statement = connection.prepareStatement("SELECT * FROM SALGRADE WHERE ? >= MINSAL and ? <= HISAL");
            statement.setDouble(1, salary);
            statement.setDouble(2, salary);
            resultSet = statement.executeQuery();
            resultSet.next();
            salgrade.setGrade(resultSet.getInt("GRADE"));
            salgrade.setMinSalary(resultSet.getDouble("MINSAL"));
            salgrade.setMaxSalary(resultSet.getDouble("HISAL"));
        } catch (SQLException throwables) {
            throw new Exception("Salgrade was not found");
        }
        disconnect();
        return salgrade;
    }

    public Department getDepartment(int deptNumber) throws Exception {
        connect();
        Department department = new Department();
        try {
            statement = connection.prepareStatement("SELECT * FROM DEPT WHERE ? = DEPTNO");
            statement.setDouble(1, deptNumber);
            resultSet = statement.executeQuery();
            resultSet.next();
            department.setDeptNumber(deptNumber);
            department.setDeptName(resultSet.getString("DNAME"));
            department.setLocation(resultSet.getString("LOC"));
        } catch (SQLException throwables) {
            throw new Exception("Department with number " + deptNumber + " was not found");
        }
        disconnect();
        return department;
    }

}
