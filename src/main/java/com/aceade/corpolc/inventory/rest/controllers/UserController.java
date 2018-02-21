/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.rest.controllers;

import com.aceade.corpolc.inventory.model.base.Role;
import com.aceade.corpolc.inventory.model.request.NewUserRequest;
import com.aceade.corpolc.inventory.services.UserService;
import javax.inject.Inject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author philip
 */
@Secured(Role.ROLE_FULL_ADMIN)
@Controller
@RequestMapping(value="/users")
public class UserController {
    
    @Inject
    private UserService userService;
    
    
    @RequestMapping(value="", method=RequestMethod.POST)
    public ResponseEntity<Boolean> addUser(@RequestBody(required = true) NewUserRequest newUser) {
        return new ResponseEntity<>(userService.addUser(newUser), HttpStatus.OK);
    }
    
    
}
