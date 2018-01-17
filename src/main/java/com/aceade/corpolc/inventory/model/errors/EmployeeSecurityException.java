/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.model.errors;

/**
 *
 * @author philip
 */
public class EmployeeSecurityException extends Exception {
    
    public EmployeeSecurityException() {
        super();
    }
    
    public EmployeeSecurityException(String message){
        super(message);
    }
}
