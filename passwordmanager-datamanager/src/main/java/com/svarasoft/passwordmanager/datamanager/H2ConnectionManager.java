package com.svarasoft.passwordmanager.datamanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joey
 */
public class H2ConnectionManager implements ConnectionManager {

    private static final Logger LOG = Logger.getLogger(H2ConnectionManager.class.getName());
    
    private static final String USER_HOME = System.getProperty("user.home");
    private static final String PROPERTIES_LOCATION = USER_HOME + 
            File.separator + "svarasoft" + File.separator + 
            "passwordmanager" + File.separator + "database.properties";

    private static H2ConnectionManager connectionManager;

    private H2ConnectionManager() throws SQLException {
        try {
            // Load the driver
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    public static ConnectionManager getInstance() throws SQLException {
        if (connectionManager == null) {
            connectionManager = new H2ConnectionManager();
        }
        return connectionManager;
    }

    @Override
    public Connection getConnection() throws SQLException {
        Properties properties = loadProperties();

        String url = "jdbc:h2:~/svarasoft/passwordmanager/pwmdb;CIPHER=AES";
        String username = "sa";
        String password = "h2defpass sapass4h2db";
        if (properties != null) {
            username = properties.getProperty("dataSource.user");
            password = properties.getProperty("dataSource.filePass");
            password += " " + properties.getProperty("dataSource.password");
        }
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }

    @Override
    public void cleanupResources() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Loads properties from a file
     *
     * @return the loaded properties or null
     */
    // TODO add encryption to properties file
    private Properties loadProperties() {
        FileInputStream fis = null;
        try {
            Properties properties = new Properties(); 
            fis = new FileInputStream(PROPERTIES_LOCATION);
            properties.load(fis);
            return properties;
        } catch (IOException e) {
            LOG.log(Level.SEVERE, null, e);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException ex) {
                    LOG.log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }
}
