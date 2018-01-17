/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.model.request;

/**
 *
 * @author philip
 */
public class NewSiteRequest {
    
    private String country;
    
    private String region;
    
    private String postalAddress;
    
    private int securityLevel;

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the region
     */
    public String getRegion() {
        return region;
    }

    /**
     * @param region the region to set
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * @return the postalAddress
     */
    public String getPostalAddress() {
        return postalAddress;
    }

    /**
     * @param postalAddress the postalAddress to set
     */
    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
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
        return String.format("NewSiteRequest [country=%s,region=%s,postalAddress=%s]", country, region, postalAddress);
    }
}
