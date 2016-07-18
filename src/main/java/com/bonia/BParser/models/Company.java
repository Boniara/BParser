package com.bonia.BParser.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "company")
public class Company extends AbstractModel {

    @JsonProperty("name")
    private String companyName;
    @JsonProperty("departments")
    private List<Department> departmentList;

    public Company() {
        this.departmentList = new ArrayList<>();
    }

    @Override
    @XmlAttribute(name = "id")
    public long getId() {
        return super.getId();
    }

    @Override
    public void setId(long id) {
        super.setId(id);
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
        return getCompanyName() + getDepartmentList().toString();
    }
}
