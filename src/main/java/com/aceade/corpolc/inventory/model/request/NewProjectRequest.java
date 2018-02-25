/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.model.request;

import javax.validation.constraints.NotNull;

/**
 *
 * @author philip
 */
public class NewProjectRequest {
    
    @NotNull
    private String title;
    
    @NotNull
    private String summary;
    
    @NotNull
    private Double budget;
    
    @NotNull
    private int securityLevel;
    
    public NewProjectRequest(){
        // required for JSON conversion
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * @param summary the summary to set
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * @return the budget
     */
    public Double getBudget() {
        return budget;
    }

    /**
     * @param budget the budget to set
     */
    public void setBudget(Double budget) {
        this.budget = budget;
    }

    /**
     * @return the securityLevel
     */
    public int getSecurityLevel() {
        return securityLevel;
    }

    /**
     * @param securityLevel the securityLevel to set
     */
    public void setSecurityLevel(int securityLevel) {
        this.securityLevel = securityLevel;
    }

    @Override
    public String toString() {
        return "NewProjectRequest{" + "title=" + title + ", summary=" + summary + ", budget=" + budget + ", securityLevel=" + securityLevel + '}';
    }
    
    
}
