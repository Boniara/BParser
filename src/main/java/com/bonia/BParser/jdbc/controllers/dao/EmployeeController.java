package com.bonia.BParser.jdbc.controllers.dao;

import com.bonia.BParser.jdbc.controllers.AbstractController;
import com.bonia.BParser.models.Employee;
import com.bonia.BParser.models.Position;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeController extends AbstractController<Employee, Long> {

    private static final Logger LOG = Logger.getLogger(EmployeeController.class);

    private static final String GET_ALL = "SELECT e.id, e.firstName, e.lastName, e.age, e.address_id, ed.position_id," +
            "ed.department_id FROM employee e INNER JOIN employee_department ed ON (e.id = ed.employee_id)" +
            "INNER JOIN position p ON (ed.position_id = p.id)";
    private static final String GET_BY_ID = "SELECT e.id, e.firstName, e.lastName, e.age, e.address_id, ed.position_id," +
            "ed.department_id FROM employee e INNER JOIN employee_department ed ON (e.id = ed.employee_id AND e.id = ?)" +
            "INNER JOIN position p ON (ed.position_id = p.id)";
    private static final String INSERT = "INSERT INTO employee (firstName, lastName, age, address_id) VALUES (?, ?, ?, ?)";

    public EmployeeController() {
    }

    @Override
    public List<Employee> getAll() {
        List<Employee> employeeList = new ArrayList<>();
        AddressController addressController = new AddressController();
        PositionController positionController = new PositionController();
        List<Position> positionList = new ArrayList<>();
        PreparedStatement preparedStatement = getPreparedStatement(GET_ALL);
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getLong(1));
                employee.setFirstName(resultSet.getString(2));
                employee.setLastName(resultSet.getString(3));
                employee.setAge(resultSet.getInt(4));
                employee.setAddress(addressController.getById(resultSet.getLong(5)));
                positionList.add(positionController.getById(resultSet.getLong(6)));
                employee.setPositionList(positionList);
                positionList = new ArrayList<>();
                employeeList.add(employee);
            }
        } catch (SQLException e) {
            LOG.error("SQLException", e);
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return employeeList;
    }

    @Override
    public Employee getById(Long id) {
        Employee employee = new Employee();
        AddressController addressController = new AddressController();
        PositionController positionController = new PositionController();
        List<Position> positionList = new ArrayList<>();
        PreparedStatement preparedStatement = getPreparedStatement(GET_BY_ID);
        try {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            employee.setId(resultSet.getLong(1));
            employee.setFirstName(resultSet.getString(2));
            employee.setLastName(resultSet.getString(3));
            employee.setAge(resultSet.getInt(4));
            employee.setAddress(addressController.getById(resultSet.getLong(5)));
            positionList.add(positionController.getById(resultSet.getLong(6)));
            employee.setPositionList(positionList);
        } catch (SQLException e) {
            LOG.error("SQLException", e);
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return employee;
    }

    @Override
    public void create(Employee employee) {
        PreparedStatement preparedStatement = getPreparedStatement(INSERT);
        try {
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setInt(3, employee.getAge());
            preparedStatement.setLong(4, employee.getAddress().getId());
            preparedStatement.execute();

        } catch (SQLException e) {
            LOG.error("SQLException", e);
        } finally {
            closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public void update(Employee entity) {

    }

    @Override
    public void delete(Long id) {

    }
}
