package com.bonia.BParser.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "address")
//@JsonIgnoreProperties({"id_address"})
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

    //@JsonIgnore
    public long getId_address() {
        return id_address;
    }

    //@JsonIgnore
    public void setId_address(long id_address) {
        this.id_address = id_address;
    }

    @XmlElement(name = "country")
    //@JsonGetter("country")
    public String getCountryName() {
        return countryName;
    }

    //@JsonSetter("country")
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @XmlElement(name = "city")
    //@JsonGetter("city")
    public String getCityName() {
        return cityName;
    }

    //@JsonSetter("city")
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @XmlElement(name = "street")
    //@JsonGetter("street")
    public String getStreetName() {
        return streetName;
    }

    //@JsonSetter("street")
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    @XmlElement(name = "house")
    //@JsonGetter("house")
    public int getHouseNumber() {
        return houseNumber;
    }

    //@JsonSetter("house")
    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString() {
        return getCountryName() + ", " + getCityName() + ", " + getStreetName() + ", " + getHouseNumber();
    }
}
