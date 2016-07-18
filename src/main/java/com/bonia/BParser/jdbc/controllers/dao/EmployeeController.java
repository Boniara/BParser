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
import static com.bonia.BParser.utils.jdbc.constants.EmployeeControllerConstantSource.*;

public class EmployeeController extends AbstractController<Employee, Long> {

    private static final Logger LOG = Logger.getLogger(EmployeeController.class);

    private boolean bStructure = false;

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
        LOG.info("Employees returned");
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
        LOG.info("Employee returned");
        return employee;
    }

    @Override
    public void create(Employee employee) {
        PreparedStatement preparedStatement = null;
        if(bStructure == true) {
            preparedStatement = getPreparedStatement(INSERT_STRUCTURE);
        } else preparedStatement = getPreparedStatement(INSERT);
        try {
            if(bStructure == true) {
                preparedStatement.setLong(5, employee.getId());
            }
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
        LOG.info("Emplyee created");
    }

    @Override
    public void update(Employee entity) {

    }

    @Override
    public void delete(Long id) {
        delete(DELETE, id);
    }
}
