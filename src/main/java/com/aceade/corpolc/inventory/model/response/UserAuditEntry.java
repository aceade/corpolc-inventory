/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.model.response;

/**
 *
 * @author philip
 */
public class UserAuditEntry extends AuditEntry {
    
    private boolean previousState;
    
    public UserAuditEntry(){
        // JSON conversion
    }
    
    public void setPreviousState(boolean previousState) {
        this.previousState = previousState;
    }
    
    public boolean getPreviousState() {
        return this.previousState;
    }
}
