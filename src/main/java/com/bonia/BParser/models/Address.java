package com.bonia.BParser.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "address")
public class Address {

    @JsonIgnore
    private long id_address;
    @JsonProperty("country")
    private String countryName;
    @JsonProperty("city")
    private String cityName;
    @JsonProperty("street")
    private String streetName;
    @JsonProperty("house")
    private int houseNumber;

    public Address() {
    }

    public long getId_address() {
        return id_address;
    }

    public void setId_address(long id_address) {
        this.id_address = id_address;
    }

    @XmlElement(name = "country")
    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @XmlElement(name = "city")
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @XmlElement(name = "street")
    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    @XmlElement(name = "house")
    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString() {
        return getCountryName() + ", " + getCityName() + ", " + getStreetName() + ", " + getHouseNumber();
    }
}
