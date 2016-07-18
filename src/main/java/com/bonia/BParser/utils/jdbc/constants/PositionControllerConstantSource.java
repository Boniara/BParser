package com.bonia.BParser.utils.jdbc.constants;

public interface PositionControllerConstantSource {

    String GET_ALL = "SELECT p.id, p.positionName, ed.department_id, ed.employee_id" +
            "FROM _position p INNER JOIN employee_department ed ON (p.id = ed.position_id)";
    String GET_BY_ID = "SELECT p.id, p.positionName, ed.department_id, ed.employee_id" +
            "FROM _position p INNER JOIN employee_department ed ON (p.id = ed.position_id AND e.id = ?)";
    String INSERT = "INSERT IGNORE INTO _position (positionName) VALUES (?)";
    String INSERT_STRUCTURE = "INSERT IGNORE INTO _position (positionName, id) VALUES (?, ?)";
}
