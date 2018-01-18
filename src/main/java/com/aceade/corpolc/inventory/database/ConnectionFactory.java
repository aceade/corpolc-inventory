/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.database;

import java.sql.*;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;


/**
 *
 * @author philip
 */
public class ConnectionFactory {
    
    private final DataSource dataSource;
    
    public ConnectionFactory(String url, String user, String password, Integer poolSize) {
        this.dataSource = makeDataSource(url, user, password, poolSize);
        System.out.println("Username is [" + user + "], password is [" + password + "]. URL is " + url);
    }
    
    /**
     * Set up the DataSource
     * @param url
     * @param username
     * @param password
     * @param poolsize
     * @return 
     */
    private DataSource makeDataSource(String url, String username, String password, int poolsize) {
        
        BasicDataSource newDataSource = new BasicDataSource();
        newDataSource.setDriverClassName("org.postgresql.Driver");
        newDataSource.setUrl(url);
        newDataSource.setUsername(username);
        newDataSource.setPassword(password);
        newDataSource.setInitialSize(poolsize);
        
        return newDataSource;
    }
    
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            System.err.println(e);
            return null;   
        }
        
    }
}
