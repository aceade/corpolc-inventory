/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.services;

import com.aceade.corpolc.inventory.model.base.Department;
import com.aceade.corpolc.inventory.model.base.SecurityRating;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author philip
 */
public class ServiceLibrary {
    
    public static Date getDate(){
        return new Date();
    }
    
    public static SecurityRating getSecurityRating(int id) {
        return SecurityRating.values()[id - 1];
    }
    
    public static Department getDepartment(int id) {
        return Department.values()[id - 1];
    }
    
    /**
     * Check the user's security rating. 
     * @param req
     * @return 
     */
    public SecurityRating getSecurityRatingFromRole(HttpServletRequest req) {
        
        System.out.println(req.getRemoteUser());
        
        if (req.isUserInRole("ROLE_ADMIN")) {
            return SecurityRating.HIGHEST;
        } else if (req.isUserInRole("ROLE_INTERN")) {
            // don't want interns poking around where they shouldn't be >:)
            return SecurityRating.MINIMUM;
        }
        else {
            return SecurityRating.LOW;    
        }
        
    }
}
