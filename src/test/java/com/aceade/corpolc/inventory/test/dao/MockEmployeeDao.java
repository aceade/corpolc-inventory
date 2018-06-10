/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.test.dao;

import com.aceade.corpolc.inventory.dao.EmployeeDao;
import com.aceade.corpolc.inventory.model.base.Employee;
import com.aceade.corpolc.inventory.model.base.SecurityRating;
import com.aceade.corpolc.inventory.model.base.Site;
import com.aceade.corpolc.inventory.model.request.NewEmployeeRequest;
import com.aceade.corpolc.inventory.services.ServiceLibrary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 * @author philip
 */
public class MockEmployeeDao implements EmployeeDao {
    
    private static final Logger LOGGER = LogManager.getLogger(MockEmployeeDao.class);
    
    private final Map<Long, Employee> employees = new HashMap<>();
    
    public static final long MOCK_SITE_ID = 1;
    public static final String MOCK_SITE_COUNTRY = "Ireland";
    public static final String MOCK_SITE_ADDRESS = "Tom Sheridan's Bar and Restaurant, Galway";
    public static final String MOCK_SITE_REGION = "Connacht";
    
    private final Site MOCK_SITE = new Site(MOCK_SITE_ID, MOCK_SITE_COUNTRY, MOCK_SITE_REGION, MOCK_SITE_ADDRESS, SecurityRating.MINIMUM);

    @Override
    public long addEmployee(NewEmployeeRequest newEmployee) {
        long newId = employees.size() + 1;
        Employee employee = new Employee(newId, newEmployee.getName());
        employee.setName(newEmployee.getName());
        employee.setBirthday(newEmployee.getBirthday());
        employee.setCurrentlyEmployed(true);
        employee.setSalary(newEmployee.getSalary());
        employee.setClearanceLevel(ServiceLibrary.getSecurityRating(newEmployee.getSecurityLevel()));
        employee.setWorkplace(MOCK_SITE);
        employees.put(newId, employee);
        LOGGER.info("Drones are now: " + employees);
        return newId;
    }

    @Override
    public boolean deleteEmployee(long id) {
        if (!employees.containsKey(id)) {
            return false;
        } else {
            employees.get(id).setCurrentlyEmployed(false);
            return true;
        }
        
    }

    @Override
    public List<Employee> getAllEmployees() {
        return (List<Employee>) employees.values();
    }

    @Override
    public Employee getEmployee(long id) {
        return employees.get(id);
    }

    @Override
    public long getEmployeeId(NewEmployeeRequest newEmployeeRequest) {
        throw new UnsupportedOperationException("Not sure how to test this."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Site getSite(long employeeId) {
        Employee drone = getEmployee(employeeId);
        return drone.getWorkplace();
    }

    @Override
    public int getTotalEmployeeCount() {
        return employees.size();
    }
    
}
