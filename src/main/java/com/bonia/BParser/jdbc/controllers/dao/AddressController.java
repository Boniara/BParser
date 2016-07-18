package com.bonia.BParser.jdbc.controllers.dao;

import com.bonia.BParser.jdbc.controllers.AbstractController;
import com.bonia.BParser.models.Address;
import org.apache.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static com.bonia.BParser.utils.jdbc.constants.AddressControllerConstantSource.*;

public class AddressController extends AbstractController<Address, Long> {

    private static final Logger LOG = Logger.getLogger(AddressController.class);

    private boolean bStructure = false;

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
        LOG.info("Addresses returned");
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
        LOG.info("Address returned");
        return address;
    }

    @Override
    public void create(Address address) {
        PreparedStatement preparedStatement = null;
        if(bStructure == true) {
            preparedStatement = getPreparedStatement(INSERT_STRUCTURE);
        } else preparedStatement = getPreparedStatement(INSERT);
        try {
            if(bStructure == true) {
                preparedStatement.setLong(5, address.getId());
            }
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
        LOG.info("Address created");
    }

    @Override
    public void update(Address address) {

    }

    @Override
    public void delete(Long id) {
        delete(DELETE, id);
    }
}
