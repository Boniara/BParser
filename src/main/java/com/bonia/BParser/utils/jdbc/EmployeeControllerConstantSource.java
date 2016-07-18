package com.bonia.BParser.utils.jdbc;

public interface EmployeeControllerConstantSource {

    String GET_ALL = "SELECT e.id, e.firstName, e.lastName, e.age, e.address_id, ed.position_id," +
            "ed.department_id FROM employee e INNER JOIN employee_department ed ON (e.id = ed.employee_id)" +
            "INNER JOIN position p ON (ed.position_id = p.id)";
    String GET_BY_ID = "SELECT e.id, e.firstName, e.lastName, e.age, e.address_id, ed.position_id," +
            "ed.department_id FROM employee e INNER JOIN employee_department ed ON (e.id = ed.employee_id AND e.id = ?)" +
            "INNER JOIN position p ON (ed.position_id = p.id)";
    String INSERT = "INSERT IGNORE INTO employee (firstName, lastName, age, address_id) VALUES (?, ?, ?, ?)";
    String INSERT_STRUCTURE = "INSERT IGNORE INTO employee (firstName, lastName, age, address_id, id) VALUES (?, ?, ?, ?, ?)";
}
