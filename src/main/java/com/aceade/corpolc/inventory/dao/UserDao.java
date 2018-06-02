/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.dao;

import com.aceade.corpolc.inventory.model.request.NewUserRequest;

/**
 *
 * @author philip
 */
public interface UserDao {

    boolean addUser(NewUserRequest newUser);

    boolean disableUser(String username);
    
}
