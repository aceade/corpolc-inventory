/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.database;

import java.sql.*;
import java.util.Properties;
import javax.sql.DataSource;

/**
 *
 * @author philip
 */
public class ConnectionFactory {
    
    private DataSource dataSource;
    
    private final String url;
    
    private final Properties properties;
    
    public ConnectionFactory(String url, String user, String password, Integer poolSize) {
        this.url = url;
        this.dataSource = makeDataSource(url, poolSize);
        this.properties = new Properties();
        this.properties.put("user", user);
        this.properties.put("password", password);
        System.out.println("Username is [" + user + "], password is [" + password + "]. URL is " + url);
    }
    
    
    private DataSource makeDataSource(String url, int poolSize) {
        
        try {
            Class.forName("org.postgresql.Driver");    
        } catch (ClassNotFoundException e) {
            // TODO: get a proper logger!
            System.err.println(e);
        }
        
        DataSource dataSource = null;
        return dataSource;
    }
    
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, properties);    
        } catch (SQLException e) {
            System.err.println(e);
            return null;   
        }
        
    }
}
