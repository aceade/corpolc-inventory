/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.model.request;

import java.util.Date;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author philip
 */
public class NewEmployeeRequest {
    
    @NotBlank
    private String name;
    
    @NotNull
    private int departmentId;

    @NotNull
    private Date birthday;
    
    @NotNull
    private int siteId;
    
    @NotNull
    @Min(1)
    @Max(7)
    private int securityLevel;
    
    @NotNull
    private double salary;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the departmentId
     */
    public int getDepartmentId() {
        return departmentId;
    }

    /**
     * @param departmentId the departmentId to set
     */
    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
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
     * @return the siteId
     */
    public int getSiteId() {
        return siteId;
    }

    /**
     * @param siteId the siteId to set
     */
    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    @Override
    public String toString() {
        return "NewEmployeeRequest{" + "name=" + name + ", departmentId=" + departmentId + ", birthday=" + birthday + ", siteId=" + siteId + ", securityLevel=" + securityLevel + ", salary=" + salary + '}';
    }

    
}
