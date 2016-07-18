package com.bonia.BParser.jdbc.controllers.dao;

import com.bonia.BParser.jdbc.controllers.AbstractController;
import com.bonia.BParser.models.Position;
import org.apache.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static com.bonia.BParser.utils.jdbc.constants.PositionControllerConstantSource.*;

public class PositionController extends AbstractController<Position, Long> {

    private static final Logger LOG = Logger.getLogger(PositionController.class);

    private boolean bStructure = false;

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
        LOG.info("Positions returned");
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
        LOG.info("Position returned");
        return position;
    }

    @Override
    public void create(Position position) {
        PreparedStatement preparedStatement = null;
        if(bStructure == true) {
            preparedStatement = getPreparedStatement(INSERT_STRUCTURE);
        } else preparedStatement = getPreparedStatement(INSERT);
        try {
            if(bStructure == true) {
                preparedStatement.setLong(2, position.getId());
            }
            preparedStatement.setString(1, position.getPositionName());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOG.error("SQLException", e);
        } finally {
            closePreparedStatement(preparedStatement);
        }
        LOG.info("Position created");
    }

    @Override
    public void update(Position position) {

    }

    @Override
    public void delete(Long id) {

    }
}
