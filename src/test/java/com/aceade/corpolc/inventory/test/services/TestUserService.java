/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.test.services;

import com.aceade.corpolc.inventory.test.config.DevConfig;
import com.aceade.corpolc.inventory.model.request.NewUserRequest;
import com.aceade.corpolc.inventory.services.UserService;
import javax.inject.Inject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author philip
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={DevConfig.class})
@ActiveProfiles("test")
public class TestUserService {
    
    @Inject
    private UserService userService;
    
    @Test
    public void testAddExistingUser(){
        NewUserRequest req = new NewUserRequest(DevConfig.MOCK_USERNAME, DevConfig.MOCK_PASSWORD, DevConfig.MOCK_EMPLOYEE_ID, DevConfig.MOCK_ROLE);
        Assert.assertFalse("Cannot duplicate users!", userService.addUser(req));
    }
    
    @Test
    public void testAddNewUser(){
        NewUserRequest req = new NewUserRequest(DevConfig.MOCK_USERNAME + "2", DevConfig.MOCK_PASSWORD, DevConfig.MOCK_EMPLOYEE_ID, DevConfig.MOCK_ROLE);
        Assert.assertTrue("Cannot duplicate users!", userService.addUser(req));
    }
    
    @Test
    public void testDisableUser() {
        Assert.assertTrue(userService.disableUser(DevConfig.MOCK_USERNAME));
    }
    
    @Test
    public void testDisableFakeUser(){
        // this user shouldn't exist, so we shouldn't be able to delete it.
        Assert.assertFalse(userService.disableUser("Darth Bean"));
    }
}
