/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.dao;

import com.aceade.corpolc.inventory.model.base.Employee;
import com.aceade.corpolc.inventory.model.base.Site;
import com.aceade.corpolc.inventory.model.request.NewEmployeeRequest;
import java.util.List;

/**
 *
 * @author philip
 */
public interface EmployeeDao {

    long addEmployee(NewEmployeeRequest newEmployee);

    boolean deleteEmployee(long id);

    List<Employee> getAllEmployees();

    Employee getEmployee(long id);

    long getEmployeeId(NewEmployeeRequest newEmployeeRequest);

    Site getSite(long employeeId);

    int getTotalEmployeeCount();
    
}
