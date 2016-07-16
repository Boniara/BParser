package com.bonia.BParser.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "address")
@JsonIgnoreProperties({"id"})
public class Address extends AbstractModel {

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

    @Override
    public long getId() {
        return super.getId();
    }

    @Override
    public void setId(long id) {
        super.setId(id);
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
