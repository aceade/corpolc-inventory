/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.model.base;

import java.util.Date;

/**
 *
 * @author philip
 */
public class Employee {
    
    private long id;
    
    private String name;
    
    private String username;
    
    private Date birthday;
    
    private Department department;
    
    private Site workplace;
    
    private SecurityRating clearanceLevel;
    
    private double salary;
    
    private boolean currentlyEmployed;
    
    public Employee(long id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public Employee() {
        // default constructor
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return this.name;
    }
    
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * @param birthday the birthday to set
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * @return the department
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(Department department) {
        this.department = department;
    }
    
    public Site getWorkplace(){
        return workplace;
    }
    
    public void setWorkplace(Site workplace) {
        this.workplace = workplace;
    }

    /**
     * @return the clearanceLevel
     */
    public SecurityRating getClearanceLevel() {
        return clearanceLevel;
    }

    /**
     * @param clearanceLevel the clearanceLevel to set
     */
    public void setClearanceLevel(SecurityRating clearanceLevel) {
        this.clearanceLevel = clearanceLevel;
    }

    /**
     * @return the salary
     */
    public double getSalary() {
        return salary;
    }

    /**
     * @param salary the salary to set
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * @return the currentlyEmployed
     */
    public boolean isCurrentlyEmployed() {
        return currentlyEmployed;
    }

    /**
     * @param currentlyEmployed the currentlyEmployed to set
     */
    public void setCurrentlyEmployed(boolean currentlyEmployed) {
        this.currentlyEmployed = currentlyEmployed;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
