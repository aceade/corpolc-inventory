/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.model.request;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author philip
 */
public class NewUserRequest {
    
    @NotBlank
    @NotNull
    private String username;
    
    @NotBlank
    @NotNull
    private String password;
    
    @NotBlank
    private long employeeId;
    
    @NotBlank
    @NotNull
    private String role;
    
    public NewUserRequest() {
        // required for JSON conversion
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the employeeId
     */
    public long getEmployeeId() {
        return employeeId;
    }

    /**
     * @param employeeId the employeeId to set
     */
    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "NewUserRequest{" + "username=" + username + ", password=" + password + ", employeeId=" + employeeId + ", role=" + role + '}';
    }
    
    
    
}
