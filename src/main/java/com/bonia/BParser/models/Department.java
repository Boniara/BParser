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
//@JsonIgnoreProperties({"id_department"})
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

    //@JsonIgnore
    public long getId_department() {
        return id_department;
    }

    //@JsonIgnore
    public void setId_department(long id_department) {
        this.id_department = id_department;
    }

    @XmlAttribute(name = "name")
    //@JsonGetter("name")
    public String getDepartmentName() {
        return departmentName;
    }

    //@JsonSetter("name")
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @XmlElement(name = "department_address")
    //@JsonGetter("address")
    public Address getAddress() {
        return address;
    }

    //@JsonSetter("address")
    public void setAddress(Address address) {
        this.address = address;
    }

    @XmlElementWrapper(name = "employees")
    @XmlElement(name = "employee")
    //@JsonGetter("employees")
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    //@JsonSetter("employees")
    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public String toString() {
        return getDepartmentName();
    }
}
