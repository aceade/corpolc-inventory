/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.services;

import com.aceade.corpolc.inventory.dao.EmployeeDao;
import com.aceade.corpolc.inventory.model.base.Employee;
import com.aceade.corpolc.inventory.model.request.NewEmployeeRequest;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 *
 * @author philip
 */
@Singleton
public class EmployeeService {
    
    @Inject
    private EmployeeDao employeeDao;
    
    public Employee getEmployee(long id) {
        return employeeDao.getEmployee(id);
    }
    
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }
    
    public long addEmployee(NewEmployeeRequest newEmployee){
        return employeeDao.addEmployee(newEmployee);
    }

    public boolean deleteEmployee(long id) {
        return employeeDao.deleteEmployee(id);
    }

}
