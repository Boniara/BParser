package com.bonia.BParser.jdbc.controllers.dao;

import com.bonia.BParser.jdbc.controllers.AbstractController;
import com.bonia.BParser.models.Address;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressController extends AbstractController<Address, Long> {

    private static final Logger LOG = Logger.getLogger(AddressController.class);

    private static final String GET_ALL = "SELECT * FROM address";
    private static final String GET_BY_ID = "SELECT * FROM address WHERE id = ?";
    private static final String INSERT = "INSERT INTO address (country, city, street, house) VALUES (?, ?, ?, ?)";

    @Override
    public List<Address> getAll() {
        List<Address> addressList = new ArrayList<>();
        PreparedStatement preparedStatement = getPreparedStatement(GET_ALL);
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Address address = new Address();
                address.setId(resultSet.getLong(1));
                address.setCountryName(resultSet.getString(2));
                address.setCityName(resultSet.getString(3));
                address.setStreetName(resultSet.getString(3));
                address.setHouseNumber(resultSet.getInt(4));
                addressList.add(address);
            }
        } catch (SQLException e) {
            LOG.error("SQLException", e);
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return addressList;
    }

    @Override
    public Address getById(Long id) {
        Address address = new Address();
        PreparedStatement preparedStatement = getPreparedStatement(GET_BY_ID);
        try {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            address.setId(resultSet.getLong(1));
            address.setCountryName(resultSet.getString(2));
            address.setCityName(resultSet.getString(3));
            address.setStreetName(resultSet.getString(3));
            address.setHouseNumber(resultSet.getInt(4));
        } catch (SQLException e) {
            LOG.error("SQLException", e);
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return address;
    }

    @Override
    public void create(Address address) {
        PreparedStatement preparedStatement = getPreparedStatement(INSERT);
        try {
            preparedStatement.setString(1, address.getCountryName());
            preparedStatement.setString(2, address.getCityName());
            preparedStatement.setString(3, address.getStreetName());
            preparedStatement.setInt(4, address.getHouseNumber());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOG.error("SQLException", e);
        } finally {
            closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public void update(Address address) {

    }

    @Override
    public void delete(Long id) {

    }
}
