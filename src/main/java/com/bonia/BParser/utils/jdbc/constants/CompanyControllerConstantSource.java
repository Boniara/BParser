package com.bonia.BParser.utils.jdbc.constants;

public interface CompanyControllerConstantSource {

    String GET_ALL = "SELECT * FROM company";
    String GET_BY_ID = "SELECT c.id, c.companyName, d.id FROM company c INNER JOIN department d" +
            "ON (c.id = d.company_id AND c.id = ?)";
    String INSERT = "INSERT IGNORE INTO company (companyName) VALUES (?)";
    String INSERT_STRUCTURE = "INSERT IGNORE INTO company (companyName, id) VALUES (?, ?)";
    String DELETE = "DELETE FROM company WHERE company.id = ?";
}
