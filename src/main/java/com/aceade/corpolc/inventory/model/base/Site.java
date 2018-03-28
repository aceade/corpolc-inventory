/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.model.base;

/**
 *
 * @author philip
 */
public class Site {
    
    private long id;
    
    private String country;
    
    private String region;
    
    private String postalAddress;
    
    private SecurityRating minimumSecurityLevel;
    
    public Site(long id){
        this.id = id;
    }
    
    public Site() {
        // default constructor
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }
    
    public void setId(long newId) {
        this.id = newId;
    }

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
     * @return the minimumSecurityLevel
     */
    public SecurityRating getMinimumSecurityLevel() {
        return minimumSecurityLevel;
    }

    /**
     * @param minimumSecurityLevel the minimumSecurityLevel to set
     */
    public void setMinimumSecurityLevel(SecurityRating minimumSecurityLevel) {
        this.minimumSecurityLevel = minimumSecurityLevel;
    }
    
    @Override
    public String toString(){
        return String.format("Site [country=%s,region=%s,postalAddress=%s,securityLevel=%s]", country, region, postalAddress, minimumSecurityLevel);
    }
    
}
