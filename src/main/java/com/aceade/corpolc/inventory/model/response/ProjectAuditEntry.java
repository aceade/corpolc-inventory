/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.model.response;

import com.aceade.corpolc.inventory.model.base.ProjectStatus;
import com.aceade.corpolc.inventory.model.base.SecurityRating;

/**
 *
 * @author philip
 */
public class ProjectAuditEntry extends AuditEntry {
    
  private ProjectStatus previousState;
  private String previousSummary;
  private String previousTitle;
  private double previousBudget;
  private SecurityRating previousSecurityLevel;

    /**
     * @return the previousState
     */
    public ProjectStatus getPreviousState() {
        return previousState;
    }

    /**
     * @param previousState the previousState to set
     */
    public void setPreviousState(ProjectStatus previousState) {
        this.previousState = previousState;
    }

    /**
     * @return the previousSummary
     */
    public String getPreviousSummary() {
        return previousSummary;
    }

    /**
     * @param previousSummary the previousSummary to set
     */
    public void setPreviousSummary(String previousSummary) {
        this.previousSummary = previousSummary;
    }

    /**
     * @return the previousTitle
     */
    public String getPreviousTitle() {
        return previousTitle;
    }

    /**
     * @param previousTitle the previousTitle to set
     */
    public void setPreviousTitle(String previousTitle) {
        this.previousTitle = previousTitle;
    }

    /**
     * @return the previousBudget
     */
    public double getPreviousBudget() {
        return previousBudget;
    }

    /**
     * @param previousBudget the previousBudget to set
     */
    public void setPreviousBudget(double previousBudget) {
        this.previousBudget = previousBudget;
    }

    /**
     * @return the previousSecurityLevel
     */
    public SecurityRating getPreviousSecurityLevel() {
        return previousSecurityLevel;
    }

    /**
     * @param previousSecurityLevel the previousSecurityLevel to set
     */
    public void setPreviousSecurityLevel(SecurityRating previousSecurityLevel) {
        this.previousSecurityLevel = previousSecurityLevel;
    }
  
  
}
