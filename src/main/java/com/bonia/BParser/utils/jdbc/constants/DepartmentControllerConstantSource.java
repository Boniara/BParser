package com.bonia.BParser.utils.jdbc.constants;

public interface DepartmentControllerConstantSource {

    String GET_ALL = "SELECT d.id, d.departmentName, ed.employee_id, d.address_id FROM department d" +
            "INNER JOIN employee_department ed ON (d.id = ed.department_id)";;
    String GET_BY_ID = "SELECT d.id, d.departmentName, ed.employee_id, d.address_id FROM department d" +
            "INNER JOIN employee_department ed ON (d.id = ed.department_id AND d.id = ?)";
    String INSERT = "INSERT IGNORE INTO department (departmentName, company_id, address_id) VALUES (?, ?, ?)";
    String INSERT_STRUCTURE = "INSERT IGNORE INTO department (departmentName, company_id, address_id, id) VALUES (?, ?, ?, ?)";
    String INSERT_INTO_ED = "INSERT IGNORE INTO employee_department VALUES (?, ?, ?)";
    String DELETE = "DELETE FROM department WHERE department.id = ?";
}
