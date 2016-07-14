package com.bonia.BParser.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "department")
public class Department {

    @JsonIgnore
    private long id_department;
    @JsonProperty("name")
    private String departmentName;
    @JsonProperty("employees")
    private List<Employee> employeeList;
    @JsonProperty("address")
    private Address address;

    public Department() {
        this.employeeList = new ArrayList<>();
        this.address = new Address();
    }

    public long getId_department() {
        return id_department;
    }

    public void setId_department(long id_department) {
        this.id_department = id_department;
    }

    @XmlAttribute(name = "name")
    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @XmlElement(name = "department_address")
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @XmlElementWrapper(name = "employees")
    @XmlElement(name = "employee")
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public String toString() {
        return getDepartmentName();
    }
}
