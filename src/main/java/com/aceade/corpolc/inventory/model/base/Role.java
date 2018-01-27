/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.model.base;

/**
 * Constants to represent a user's role.
 * @author philip
 */
public class Role {
    
    public static final String ROLE_FULL_ADMIN = "ROLE_FULL_ADMIN";
    public static final String ROLE_FULL_READ_ONLY = "ROLE_FULL_READ_ONLY";
    public static final String ROLE_VIEW_ALL_ADDRESSES = "ROLE_VIEW_ALL_ADDRESSES";
    public static final String ROLE_PROJECT_ADMIN = "ROLE_PROJECT_ADMIN";
    public static final String ROLE_SITE_ADMIN = "ROLE_SITE_ADMIN";
    public static final String ROLE_VIEW_OWN_DETAILS = "ROLE_VIEW_OWN_DETAILS";
    public static final String ROLE_EDIT_OWN_DETAILS = "ROLE_EDIT_OWN_DETAILS";
    
    private Role() {
        // Utility classes should not have an implicit public constructor
    }
}
