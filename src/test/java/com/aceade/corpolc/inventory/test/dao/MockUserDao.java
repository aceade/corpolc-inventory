/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.test.dao;

import com.aceade.corpolc.inventory.dao.UserDao;
import com.aceade.corpolc.inventory.model.base.User;
import com.aceade.corpolc.inventory.model.request.NewUserRequest;
import java.util.Map;
import javax.inject.Inject;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 * @author philip
 */
public class MockUserDao implements UserDao {
    
    private static final Logger LOGGER = LogManager.getLogger(MockUserDao.class);
    
    @Inject
    private Map<String, User> mockUsers;
   
    @Override
    public boolean addUser(NewUserRequest newUser) {
        String username = newUser.getUsername();
        if (!mockUsers.containsKey(username)) {
            addUserToMap(newUser);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean disableUser(String username) {
        LOGGER.info("Mock-disabling " + username + ". Users: " + mockUsers);
        User user = mockUsers.get(username);
        if (user != null) {
            user.setActive(false);
            return true;
        } else {
            LOGGER.info("No users with username " + username);
            return false;
        }
    }
    
    private void addUserToMap(NewUserRequest newUser) {
        User user = new User();
        user.setUsername(newUser.getUsername());
        user.setPassword(newUser.getPassword());
        user.setEmployeeId(newUser.getEmployeeId());
        user.setActive(true);
        user.setRole(newUser.getRole());
        mockUsers.put(newUser.getUsername(), user);
    }
    
}
