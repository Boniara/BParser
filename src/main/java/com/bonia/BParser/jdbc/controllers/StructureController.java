package com.bonia.BParser.jdbc.controllers;

import com.bonia.BParser.jdbc.controllers.dao.*;
import com.bonia.BParser.models.Company;
import com.bonia.BParser.models.Department;
import com.bonia.BParser.models.Employee;
import com.bonia.BParser.models.Position;
import org.apache.log4j.Logger;

import java.util.List;

public class StructureController {

    private static final Logger LOG = Logger.getLogger(StructureController.class);

    public StructureController() {
    }

    public void createStructureByCompany(Company company) {
        LOG.info("Company`s structure prapare to create");
        AddressController addressController = new AddressController();
        EmployeeController employeeController = new EmployeeController();
        PositionController positionController = new PositionController();
        DepartmentController departmentController = new DepartmentController();
        CompanyController companyController = new CompanyController();
        companyController.createStructure(company, true);
        List<Department> departmentList = company.getDepartmentList();
        List<Employee> employeeList;
        List<Position> positionList;
        for(Department departmant: departmentList) {
            employeeList = departmant.getEmployeeList();
            for(Employee employee: employeeList) {
                addressController.createStructure(employee.getAddress(), true);
                employeeController.createStructure(employee, true);
                positionList = employee.getPositionList();
                for(Position position: positionList) {
                    positionController.createStructure(position, true);
                }
            }
            addressController.createStructure(departmant.getAddress(), true);
            departmentController.createStructure(departmant, true);
        }
        LOG.info("Company`s structure created");
    }
}
