package com.bonia.BParser.jdbc.controllers;

import com.bonia.BParser.utils.jdbc.DBConnection;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractController<E, K> {

    private static final Logger LOG = Logger.getLogger(AbstractController.class);

    private boolean bStructure = false;

    public AbstractController() {
    }

    public abstract List<E> getAll();
    public abstract E getById(K id);
    public abstract void create(E entity);
    public abstract void update(E entity);
    public abstract void delete(K id);

    public PreparedStatement getPreparedStatement(String query) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
        } catch (SQLException e) {
            LOG.error("SQLException", e);
        }
        return preparedStatement;
    }

    public void createStructure(E entity, boolean bStructure) {
        this.bStructure = bStructure;
        create(entity);
    }

    protected void delete(String deleteQuery, Long id) {
        PreparedStatement preparedStatement = null;
        preparedStatement = getPreparedStatement(deleteQuery);
        try {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            LOG.error("SQLException", e);
        } finally {
            closePreparedStatement(preparedStatement);
        }
    }

    public void closePreparedStatement(PreparedStatement preparedStatement) {
        if(preparedStatement != null) {
            try {
                preparedStatement.close();
                LOG.info("PreparedStatement " + preparedStatement + " closed");
            } catch (SQLException e) {
                LOG.error("SQLException", e);
            }
        }
    }
}
