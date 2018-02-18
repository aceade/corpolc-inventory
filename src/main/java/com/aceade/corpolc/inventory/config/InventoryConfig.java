/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.config;

import com.aceade.corpolc.inventory.dao.EmployeeDao;
import com.aceade.corpolc.inventory.dao.ProjectDao;
import com.aceade.corpolc.inventory.dao.SiteDao;
import com.aceade.corpolc.inventory.database.ConnectionFactory;
import com.aceade.corpolc.inventory.services.EmployeeService;
import com.aceade.corpolc.inventory.services.ProjectService;
import com.aceade.corpolc.inventory.services.SiteService;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author philip
 */
@Configuration
public class InventoryConfig {
    
    public static final String JDBC_URL = "jdbcUrl";
    public static final String JDBC_USER = "jdbcUser";
    public static final String JDBC_PASSWORD = "jdbcPassword";
    
    @Bean
    public SiteService siteService() {
        return new SiteService();
    }
    
    @Bean
    public ProjectService projectService() {
        return new ProjectService();
    }
    
    @Bean
    public EmployeeService employeeService(){
        return new EmployeeService();
    }
    
    // JDBC
    @Bean
    @Named(JDBC_URL)
    public String jdbcUrl() {
        return System.getProperty("com.aceade.corpolc.jdbcUrl") ;
    }
    
    @Bean
    @Named(JDBC_USER)
    String jdbcUser() {
        return System.getProperty("com.aceade.corpolc.jdbcUser");
    }
    
    @Bean
    @Named(JDBC_PASSWORD)
    String jdbcPassword() {
        return System.getProperty("com.aceade.corpolc.jdbcPassword");
    }
    
    @Bean
    public DataSource dataSource() {
        BasicDataSource newDataSource = new BasicDataSource();
        newDataSource.setDriverClassName("org.postgresql.Driver");
        newDataSource.setUrl(jdbcUrl());
        newDataSource.setUsername(jdbcUser());
        newDataSource.setPassword(jdbcPassword());
        newDataSource.setInitialSize(20);
        
        return newDataSource;
    }
    
    @Bean
    @Singleton
    public ConnectionFactory connectionFactory(){
        return new ConnectionFactory(dataSource());
    }
    
    @Bean
    public EmployeeDao employeeDao() {
        return new EmployeeDao();
    }
    
    @Bean
    public SiteDao siteDao() {
        return new SiteDao();
    }
    
    @Bean
    public ProjectDao projectDao() {
        return new ProjectDao();
    }
}
