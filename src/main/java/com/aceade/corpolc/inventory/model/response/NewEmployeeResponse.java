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
public class NewEmployeeResponse {
    
    private boolean success;
    
    private long newDroneId;
    
    private String responseText;
    
    public NewEmployeeResponse() {
        // default
    }

    /**
     * @return the success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * @return the newDroneId
     */
    public long getNewDroneId() {
        return newDroneId;
    }

    /**
     * @param newDroneId the newDroneId to set
     */
    public void setNewDroneId(long newDroneId) {
        this.newDroneId = newDroneId;
    }

    /**
     * @return the responseText
     */
    public String getResponseText() {
        return responseText;
    }

    /**
     * @param responseText the responseText to set
     */
    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }
}
