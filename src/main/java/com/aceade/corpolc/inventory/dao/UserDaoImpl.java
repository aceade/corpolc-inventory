/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.dao;

import com.aceade.corpolc.inventory.model.request.NewUserRequest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.inject.Inject;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.aceade.corpolc.inventory.database.Queries;

/**
 *
 * @author philip
 */
public class UserDaoImpl extends BaseDao implements UserDao {
    
    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);
    
    @Inject
    private PasswordEncoder encoder;
    
    @Override
    public boolean addUser(NewUserRequest newUser) {
        LOGGER.info("Adding new user");
        String sql = Queries.ADD_USER;
        
        boolean success = false;
        Connection dbConnection = connectionFactory.getConnection();
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            stmt.setString(1, newUser.getUsername());
            String password = newUser.getPassword();
            stmt.setString(2, encoder.encode(password));
            stmt.setLong(3, newUser.getEmployeeId());
            stmt.setString(4, newUser.getUsername());
            stmt.setString(5, newUser.getRole());
            success = stmt.execute();
        } catch (SQLException e) {
            LOGGER.error("Could not add new user", e);
        }
        
        return success;
    }
    
    @Override
    public boolean disableUser(String username) {
        String sql = Queries.DISABLE_USER;
        Connection dbConnection = connectionFactory.getConnection();
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            stmt.setString(1, username);
            return stmt.execute();
        } catch (SQLException e) {
            LOGGER.error("Unable to disable user " + username, e);
            return false;
        }
    
    }
}
