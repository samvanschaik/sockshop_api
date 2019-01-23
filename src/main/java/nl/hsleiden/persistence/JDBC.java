/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.hsleiden.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.postgresql.ds.PGPoolingDataSource;

/**
 *
 * @author Sam
 */

public class JDBC {
    public static Connection getConnection(){
        PGPoolingDataSource source = new PGPoolingDataSource();
//        source.setDataSourceName(connectionName);
        source.setServerName("localhost");
        source.setDatabaseName("sockshop");
        source.setUser("postgres");
        source.setPassword("123");
        source.setMaxConnections(100);
        
        try {
            return source.getConnection();
        }
        catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
