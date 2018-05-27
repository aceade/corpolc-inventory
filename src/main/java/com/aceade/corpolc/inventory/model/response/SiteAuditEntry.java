/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.model.response;

import com.aceade.corpolc.inventory.dao.AuditingReason;
import com.aceade.corpolc.inventory.model.base.SecurityRating;

/**
 *
 * @author philip
 */
public class SiteAuditEntry extends AuditEntry {
    
  private String previousCountry;
  private String previousRegion;
  private String previousAddress;
  private SecurityRating previousSecurityRating;
  private AuditingReason auditingReason;

    /**
     * @return the previousCountry
     */
    public String getPreviousCountry() {
        return previousCountry;
    }

    /**
     * @param previousCountry the previousCountry to set
     */
    public void setPreviousCountry(String previousCountry) {
        this.previousCountry = previousCountry;
    }

    /**
     * @return the previousRegion
     */
    public String getPreviousRegion() {
        return previousRegion;
    }

    /**
     * @param previousRegion the previousRegion to set
     */
    public void setPreviousRegion(String previousRegion) {
        this.previousRegion = previousRegion;
    }

    /**
     * @return the previousAddress
     */
    public String getPreviousAddress() {
        return previousAddress;
    }

    /**
     * @param previousAddress the previousAddress to set
     */
    public void setPreviousAddress(String previousAddress) {
        this.previousAddress = previousAddress;
    }

    /**
     * @return the previousSecurityRating
     */
    public SecurityRating getPreviousSecurityRating() {
        return previousSecurityRating;
    }

    /**
     * @param previousSecurityRating the previousSecurityRating to set
     */
    public void setPreviousSecurityRating(SecurityRating previousSecurityRating) {
        this.previousSecurityRating = previousSecurityRating;
    }

    /**
     * @return the auditingReason
     */
    public AuditingReason getAuditingReason() {
        return auditingReason;
    }

    /**
     * @param auditingReason the auditingReason to set
     */
    public void setAuditingReason(AuditingReason auditingReason) {
        this.auditingReason = auditingReason;
    }
  
  
}
