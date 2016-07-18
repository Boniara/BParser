package com.bonia.BParser.utils.jdbc;

public interface AddressControllerConstantSource {

    String GET_ALL = "SELECT * FROM address";
    String GET_BY_ID = "SELECT * FROM address WHERE id = ?";
    String INSERT = "INSERT IGNORE INTO address (country, city, street, house) VALUES (?, ?, ?, ?)";
    String INSERT_STRUCTURE = "INSERT IGNORE INTO address (country, city, street, house, id) VALUES (?, ?, ?, ?, ?)";
}
