package com.bonia.BParser.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "employee")
public class Employee {

    @JsonProperty("id")
    private long id_employee;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("age")
    private int age;
    @JsonProperty("position")
    private String position;
    @JsonProperty("address")
    private Address address;

    public Employee() {
        this.address = new Address();
    }

    @XmlAttribute(name = "id")
    //@JsonGetter("id")
    public long getId_employee() {
        return id_employee;
    }

    //@JsonSetter("id")
    public void setId_employee(long id_employee) {
        this.id_employee = id_employee;
    }

    @XmlElement(name = "firstName")
    //@JsonGetter("firstName")
    public String getFirstName() {
        return firstName;
    }

    //@JsonSetter("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @XmlElement(name = "lastName")
    //@JsonGetter("lastName")
    public String getLastName() {
        return lastName;
    }

    //@JsonSetter("lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @XmlElement(name = "age")
    //@JsonGetter("age")
    public int getAge() {
        return age;
    }

    //@JsonSetter("age")
    public void setAge(int age) {
        this.age = age;
    }

    @XmlElement(name = "position")
    //@JsonGetter("position")
    public String getPosition() {
        return position;
    }

    //@JsonSetter("position")
    public void setPosition(String position) {
        this.position = position;
    }

    @XmlElement(name = "address")
    //@JsonGetter("address")
    public Address getAddress() {
        return address;
    }

    //@JsonSetter("address")
    public void setAddress(Address address) {
        this.address = address;
    }
}
