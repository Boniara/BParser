package com.bonia.BParser.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "company")
public class Company {

    @JsonIgnore
    private long id_company;
    @JsonProperty("name")
    private String companyName;
    @JsonProperty("departments")
    private List<Department> departmentList;

    public Company() {
        this.departmentList = new ArrayList<>();
    }

    public long getId_company() {
        return id_company;
    }

    public void setId_company(long id_company) {
        this.id_company = id_company;
    }

    @XmlAttribute(name = "name")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @XmlElement(name = "department")
    @XmlElementWrapper(name = "departments")
    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    @Override
    public String toString() {
        return getCompanyName();
    }
}
