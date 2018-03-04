/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.model.base;

import java.util.List;

/**
 *
 * @author philip
 */
public class Project {
    
    private long id;
    
    private SecurityRating securityLevel;
    
    private String title;
    
    private String summary;
    
    private Double budget;
    
    private ProjectStatus status;
    
    private List<Site> sites;
    
    private List<Employee> employees;
    
    public Project() {
        
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @return the securityLevel
     */
    public SecurityRating getSecurityLevel() {
        return securityLevel;
    }

    /**
     * @param securityLevel the securityLevel to set
     */
    public void setSecurityLevel(SecurityRating securityLevel) {
        this.securityLevel = securityLevel;
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
     * @return the sites
     */
    public List<Site> getSites() {
        return sites;
    }

    /**
     * @param sites the sites to set
     */
    public void setSites(List<Site> sites) {
        this.sites = sites;
    }
    

    /**
     * @return the employees
     */
    public List<Employee> getEmployees() {
        return employees;
    }

    /**
     * @param employees the employees to set
     */
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
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
     * @return the status
     */
    public ProjectStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(ProjectStatus status) {
        this.status = status;
    }
    
}
