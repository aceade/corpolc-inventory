/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.model.response;

import com.aceade.corpolc.inventory.model.base.SecurityRating;
import java.util.Date;

/**
 *
 * @author philip
 */
public class EmployeeAuditEntry extends AuditEntry {
    
  private String previousName;
  private Date previousBirthday;
  private long previousWorkplace;
  private double previousSalary;
  private SecurityRating previousSecurityLevel;
  private boolean previousState;

    /**
     * @return the previousName
     */
    public String getPreviousName() {
        return previousName;
    }

    /**
     * @param previousName the previousName to set
     */
    public void setPreviousName(String previousName) {
        this.previousName = previousName;
    }

    /**
     * @return the previousBirthday
     */
    public Date getPreviousBirthday() {
        return previousBirthday;
    }

    /**
     * @param previousBirthday the previousBirthday to set
     */
    public void setPreviousBirthday(Date previousBirthday) {
        this.previousBirthday = previousBirthday;
    }

    /**
     * @return the previousWorkplace
     */
    public long getPreviousWorkplace() {
        return previousWorkplace;
    }

    /**
     * @param previousWorkplace the previousWorkplace to set
     */
    public void setPreviousWorkplace(long previousWorkplace) {
        this.previousWorkplace = previousWorkplace;
    }

    /**
     * @return the previousSalary
     */
    public double getPreviousSalary() {
        return previousSalary;
    }

    /**
     * @param previousSalary the previousSalary to set
     */
    public void setPreviousSalary(double previousSalary) {
        this.previousSalary = previousSalary;
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

    /**
     * @return the previousState
     */
    public boolean isPreviousState() {
        return previousState;
    }

    /**
     * @param previousState the previousState to set
     */
    public void setPreviousState(boolean previousState) {
        this.previousState = previousState;
    }
  
  
}
