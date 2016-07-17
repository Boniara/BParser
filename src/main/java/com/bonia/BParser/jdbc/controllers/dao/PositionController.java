package com.bonia.BParser.jdbc.controllers.dao;

import com.bonia.BParser.jdbc.controllers.AbstractController;
import com.bonia.BParser.models.Position;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PositionController extends AbstractController<Position, Long> {

    private static final Logger LOG = Logger.getLogger(PositionController.class);

    private static final String GET_ALL = "SELECT p.id, p.positionName, ed.department_id, ed.employee_id" +
            "FROM position p INNER JOIN employee_department ed ON (p.id = ed.position_id)";
    private static final String GET_BY_ID = "SELECT p.id, p.positionName, ed.department_id, ed.employee_id" +
            "FROM position p INNER JOIN employee_department ed ON (p.id = ed.position_id AND e.id = ?)";
    private static final String INSERT = "INSERT INTO position (positionName, lastName) VALUES (?)";

    public PositionController() {
    }

    @Override
    public List<Position> getAll() {
        List<Position> positionList = new ArrayList<>();
        PreparedStatement preparedStatement = getPreparedStatement(GET_ALL);
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Position position = new Position();
                position.setId(resultSet.getLong(1));
                position.setPositionName(resultSet.getString(2));
                position.setIdDepartment(resultSet.getLong(3));
                position.setIdEmployee(resultSet.getLong(4));
                positionList.add(position);
            }
        } catch (SQLException e) {
            LOG.error("SQLException", e);
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return positionList;
    }

    @Override
    public Position getById(Long id) {
        Position position = new Position();
        PreparedStatement preparedStatement = getPreparedStatement(GET_BY_ID);
        try {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            position.setId(resultSet.getLong(1));
            position.setPositionName(resultSet.getString(2));
            position.setIdDepartment(resultSet.getLong(3));
            position.setIdEmployee(resultSet.getLong(4));
        } catch (SQLException e) {
            LOG.error("SQLException", e);
        } finally {
            closePreparedStatement(preparedStatement);
        }
        return position;
    }

    @Override
    public void create(Position position) {
        PreparedStatement preparedStatement = getPreparedStatement(INSERT);
        try {
            preparedStatement.setString(1, position.getPositionName());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOG.error("SQLException", e);
        } finally {
            closePreparedStatement(preparedStatement);
        }
    }

    @Override
    public void update(Position position) {

    }

    @Override
    public void delete(Long id) {

    }
}
