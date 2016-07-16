package com.bonia.BParser.io.impl;

import com.bonia.BParser.io.IFileOutput;
import com.bonia.BParser.models.Company;
import com.bonia.BParser.models.Department;
import com.bonia.BParser.models.Employee;
import com.bonia.BParser.models.Position;
import org.apache.log4j.Logger;
import java.util.List;

public class OutputToConsole<T> implements IFileOutput<T> {

    private static final Logger LOG = Logger.getLogger(OutputToConsole.class);

    @Override
    public void write(T t) {
        if(t instanceof Company) {
            Company company = (Company) t;
            companyOutput(company);
        }
    }

    /**This method print all information from List
     *<p>
     *     Has a Company support
     * @param tList
     * @see Company
     */
    @Override
    public void writeList(List<T> tList) {
        if(!tList.isEmpty()) {
            if (tList.get(0) instanceof Company) {
                for (Company company : (List<Company>) tList) {
                    companyOutput(company);
                }
            }
        } else {
            LOG.info("List is empty");
        }
    }

    private void companyOutput(Company company) {
        for(Department department: company.getDepartmentList()) {
            for(Employee employee: department.getEmployeeList()) {
                for(Position position: employee.getPositionList()) {
                    System.out.println(company.getCompanyName() + ": " + department.getDepartmentName() + ": "
                            + department.getAddress().getCountryName() + ", " + department.getAddress().getCityName() + ", "
                            + department.getAddress().getStreetName() + ", " + department.getAddress().getHouseNumber() + ": "
                            + employee.getId() + " " + employee.getFirstName() + " " + employee.getLastName() + " "
                            + employee.getAge() + " - " + position.getId() + " " + position.getPositionName() + " - "
                            + employee.getAddress().getCountryName() + ", " + employee.getAddress().getCityName() + ", "
                            + employee.getAddress().getStreetName() + ", " + employee.getAddress().getHouseNumber());
                }
            }
        }
    }
}
