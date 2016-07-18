package com.bonia.BParser.utils.jdbc;

import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static com.bonia.BParser.utils.jdbc.DBConnectionConstantSource.*;

public class DBConnection {

    private static final Logger LOG = Logger.getLogger(DBConnection.class);

    private static Connection connection;

    public DBConnection() {
    }

    public static Connection getConnection() {
        connection = null;
        try {
            Class.forName(DRIVER_NAME).newInstance();
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            if(!connection.isClosed()) {
                LOG.info("Connection be registered");
            } else LOG.info("Connection be closed");
        } catch (InstantiationException e) {
            LOG.error("InstantiationException", e);
        } catch (IllegalAccessException e) {
            LOG.error("IllegalAccessException", e);
        } catch (ClassNotFoundException e) {
            LOG.error("ClassNotFoundException", e);
        } catch (SQLException e) {
            LOG.error("SQLException", e);
        }
        return connection;
    }
}
