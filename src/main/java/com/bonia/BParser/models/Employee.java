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
    public long getId_employee() {
        return id_employee;
    }

    public void setId_employee(long id_employee) {
        this.id_employee = id_employee;
    }

    @XmlElement(name = "firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @XmlElement(name = "lastName")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @XmlElement(name = "age")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @XmlElement(name = "position")
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @XmlElement(name = "address")
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return getId_employee() + " " + getFirstName() + " " + getLastName() + " " + getAge() + " " + getPosition()
                + getAddress().toString();
    }
}
