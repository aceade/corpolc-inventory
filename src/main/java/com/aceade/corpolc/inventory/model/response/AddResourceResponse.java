/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.model.response;

/**
 * A general response for adding new resources (employees, projects, sites)
 * @author philip
 */
public class AddResourceResponse {
    
    private boolean success;
    
    private long newResourceId;
    
    private String responseText;
    
    public AddResourceResponse() {
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
     * @return the newResourceId
     */
    public long getNewResourceId() {
        return newResourceId;
    }

    /**
     * @param newResourceId the newResourceId to set
     */
    public void setNewResourceId(long newResourceId) {
        this.newResourceId = newResourceId;
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
