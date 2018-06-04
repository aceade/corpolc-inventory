/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.test.config;

import com.aceade.corpolc.inventory.dao.EmployeeDao;
import com.aceade.corpolc.inventory.dao.SiteDao;
import com.aceade.corpolc.inventory.test.dao.MockUserDao;
import com.aceade.corpolc.inventory.dao.UserDao;
import com.aceade.corpolc.inventory.model.base.User;
import com.aceade.corpolc.inventory.services.EmployeeService;
import com.aceade.corpolc.inventory.services.SiteService;
import com.aceade.corpolc.inventory.services.UserService;
import com.aceade.corpolc.inventory.test.dao.MockEmployeeDao;
import com.aceade.corpolc.inventory.test.dao.MockSiteDao;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Configuration for unit tests.
 * @author philip
 */
@Configuration
@Profile("test")
@ComponentScan(basePackages = {"com.aceade.corpolc.inventory.test"})
public class DevConfig {
    
    // details for a mock user. Uses the worst possible password for production
    public static final String MOCK_USERNAME = "jsmith";
    public static final String MOCK_PASSWORD = "password";
    public static final int MOCK_EMPLOYEE_ID = 1;
    public static final String MOCK_ROLE = "ROLE_TEST";
    
    @Bean
    public UserDao userDao() {
        return new MockUserDao();
    }
    
    @Bean
    public UserService userService() {
        return new UserService();
    }
    
    @Bean
    public EmployeeService employeeService() {
        return new EmployeeService();
    }
    
    @Bean
    public EmployeeDao employeeDao() {
        return new MockEmployeeDao();
    }
    
    @Bean
    public SiteService siteService() {
        return new SiteService();
    }
    
    @Bean
    public SiteDao siteDao() {
        return new MockSiteDao();
    }
    
    @Bean
    public Map<String, User> mockUsers() {
        Map<String, User> users = new HashMap<>();
        User firstUser = new User(MOCK_USERNAME, MOCK_PASSWORD, MOCK_EMPLOYEE_ID, true, MOCK_ROLE);
        users.put(MOCK_USERNAME, firstUser);
        return users;
    }
}
