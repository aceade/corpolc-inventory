/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.services;

import java.security.Principal;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 *
 * @author philip
 */
public class UserService {
    
    public static UsernamePasswordAuthenticationToken getUser(Principal user) {
        return (UsernamePasswordAuthenticationToken) user;
    }
}
