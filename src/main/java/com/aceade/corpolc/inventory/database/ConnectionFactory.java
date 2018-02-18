/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.database;

import java.sql.*;
import javax.sql.DataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


/**
 *
 * @author philip
 */
public class ConnectionFactory {
    
    private static final Logger LOGGER = LogManager.getLogger(ConnectionFactory.class);
    
    private final DataSource dataSource;
    
    public ConnectionFactory(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            LOGGER.error("Unable to retrieve connection! ", e);
            return null;   
        }
        
    }
}
