package com.bonia.BParser.jdbc.controllers.dao;

import com.bonia.BParser.jdbc.controllers.AbstractController;
import com.bonia.BParser.models.Department;
import com.bonia.BParser.models.Employee;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentController extends AbstractController<Department, Long> {

    private static final Logger LOG = Logger.getLogger(DepartmentController.class);

    private static final String GET_ALL = "SELECT d.id, d.departmentName, ed.employee_id, d.address_id FROM department d" +
            "INNER JOIN employee_department ed ON (d.id = ed.department_id)";;
    private static final String GET_BY_ID = "SELECT d.id, d.departmentName, ed.employee_id, d.address_id FROM department d" +
            "INNER JOIN employee_department ed ON (d.id = ed.department_id AND d.id = ?)";
    private static final String INSERT = "INSERT INTO department (departmentName, company_id, address_id) VALUES (?, ?, ?)";
    private static final String INSERT_INTO_ED = "INSERT INTO employee_department VALUES (?, ?, ?)";

    public DepartmentController() {
    }

    @Override
    public List<Department> getAll() {
        List<Department> departmentList = new ArrayList<>();
        PreparedStatement preparedStatement = getPreparedStatement(GET_ALL);
        EmployeeController employeeController = new EmployeeController();
        AddressController addressController = new AddressController();
        List<Employee> employeeList = new ArrayList<>();
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Department department = new Department();
                department.setId(resultSet.getLong(1));
                department.setDepartmentName(resultSet.getString(2));
                employeeList.add(employeeController.getById(resultSet.getLong(4)));
                department.setEmployeeList(employeeList);
                employeeList = new ArrayList<>();
                department.setAddress(addressController.getById(resultSet.getLong(5)));
                departmentList.add(department);
            }
        } catch (SQLException e) {
            LOG.error("SQLException", e);
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return departmentList;
    }

    @Override
    public Department getById(Long id) {
        Department department = new Department();
        PreparedStatement preparedStatement = getPreparedStatement(GET_BY_ID);
        EmployeeController employeeController = new EmployeeController();
        AddressController addressController = new AddressController();
        List<Employee> employeeList = new ArrayList<>();
        try {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            department.setId(resultSet.getLong(1));
            department.setDepartmentName(resultSet.getString(2));
            employeeList.add(employeeController.getById(resultSet.getLong(4)));
            department.setEmployeeList(employeeList);
            department.setAddress(addressController.getById(resultSet.getLong(5)));
        } catch (SQLException e) {
            LOG.error("SQLException", e);
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return department;
    }

    @Override
    public void create(Department department) {
        PreparedStatement preparedStatement = getPreparedStatement(INSERT);
        try {
            preparedStatement.setString(1, department.getDepartmentName());
            preparedStatement.setLong(2, 1); // Bug
            preparedStatement.setLong(3, department.getAddress().getId());
            preparedStatement.execute();
            preparedStatement = getPreparedStatement(INSERT_INTO_ED);
            preparedStatement.setLong(1, department.getEmployeeList().get(0).getId());
            preparedStatement.setLong(2, department.getId());
            preparedStatement.setLong(3, department.getEmployeeList().get(0).getPositionList().get(0).getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOG.error("SQLException", e);
        } finally {
            closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public void update(Department entity) {

    }

    @Override
    public void delete(Long id) {

    }
}