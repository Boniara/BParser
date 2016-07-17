package com.bonia.BParser.jdbc.controllers.dao;

import com.bonia.BParser.jdbc.controllers.AbstractController;
import com.bonia.BParser.models.Company;
import com.bonia.BParser.models.Department;
import org.apache.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyController extends AbstractController<Company, Long> {

    private static final Logger LOG = Logger.getLogger(CompanyController.class);

    private static final String GET_ALL = "SELECT * FROM company";
    private static final String GET_BY_ID = "SELECT c.id, c.companyName, d.id FROM company c INNER JOIN department d" +
            "ON (c.id = d.company_id AND c.id = ?)";
    private static final String INSERT = "INSERT INTO company (companyName) VALUES (?)";

    public CompanyController() {
    }

    @Override
    public List<Company> getAll() {
        List<Company> companyList = new ArrayList<>();
        PreparedStatement preparedStatement = getPreparedStatement(GET_ALL);
        DepartmentController departmentController = new DepartmentController();
        List<Department> departmentList = new ArrayList<>();
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Company company = new Company();
                company.setId(resultSet.getLong(1));
                company.setCompanyName(resultSet.getString(2));
                departmentList.add(departmentController.getById(resultSet.getLong(3)));
                company.setDepartmentList(departmentList);
                departmentList = new ArrayList<>();
                companyList.add(company);
            }
        } catch (SQLException e) {
            LOG.error("SQLException", e);
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return companyList;
    }

    @Override
    public Company getById(Long id) {
        Company company = new Company();
        PreparedStatement preparedStatement = getPreparedStatement(GET_BY_ID);
        DepartmentController departmentController = new DepartmentController();
        List<Department> departmentList = new ArrayList<>();
        try {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            company.setId(resultSet.getLong(1));
            company.setCompanyName(resultSet.getString(2));
            departmentList.add(departmentController.getById(resultSet.getLong(3)));
            company.setDepartmentList(departmentList);
        } catch (SQLException e) {
            LOG.error("SQLException", e);
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return company;
    }

    @Override
    public void create(Company company) {
        PreparedStatement preparedStatement = getPreparedStatement(INSERT);
        try {
            preparedStatement.setString(1, company.getCompanyName());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOG.error("SQLException", e);
        } finally {
            //closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public void update(Company entity) {

    }

    @Override
    public void delete(Long id) {

    }
}
