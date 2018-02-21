/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.services;

import com.aceade.corpolc.inventory.dao.UserDao;
import com.aceade.corpolc.inventory.model.request.NewUserRequest;
import javax.inject.Inject;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 * @author philip
 */
public class UserService {
    
    private static final Logger LOGGER = LogManager.getLogger(UserService.class);
    
    @Inject
    private UserDao userDao;
        
    public boolean addUser(NewUserRequest newDrone) {
        LOGGER.info("Adding new drone: " + newDrone.toString());
        return userDao.addUser(newDrone);
    }
}
