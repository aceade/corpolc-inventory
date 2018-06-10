/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.test.services;

import com.aceade.corpolc.inventory.model.base.Department;
import com.aceade.corpolc.inventory.model.base.Employee;
import com.aceade.corpolc.inventory.model.base.SecurityRating;
import com.aceade.corpolc.inventory.model.base.Site;
import com.aceade.corpolc.inventory.model.request.NewEmployeeRequest;
import com.aceade.corpolc.inventory.services.EmployeeService;
import com.aceade.corpolc.inventory.test.config.DevConfig;
import com.aceade.corpolc.inventory.test.dao.MockEmployeeDao;
import java.util.Date;
import javax.inject.Inject;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author philip
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={DevConfig.class})
@ActiveProfiles("test")
public class TestEmployeeService {
    
    private static final Logger LOGGER = LogManager.getLogger(TestEmployeeService.class);
    
    private static final String MOCK_DRONE_NAME = "Mr Bean";
    private static final double MOCK_SALARY = 100;
    private static final int MOCK_DEPARTMENT = Department.Research.ordinal();
    private static final Date MOCK_BIRTHDAY = new Date(1000);
    private static final int MOCK_SECURITY_LEVEL = 0;
    
    @Inject
    private EmployeeService employeeService;
    
    public void addMockEmployee() {
        NewEmployeeRequest req = new NewEmployeeRequest();
        req.setName(MOCK_DRONE_NAME);
        req.setBirthday(MOCK_BIRTHDAY);
        req.setSalary(MOCK_SALARY);
        req.setDepartmentId(MOCK_DEPARTMENT);
        req.setSiteId((int) MockEmployeeDao.MOCK_SITE_ID);
        req.setSecurityLevel(MOCK_SECURITY_LEVEL);
        employeeService.addEmployee(req);
    }
    
    @Test
    public void testAddEmployee() {
        NewEmployeeRequest req = new NewEmployeeRequest();
        req.setName("M. Bison");
        req.setBirthday(MOCK_BIRTHDAY);
        req.setSalary(MOCK_SALARY);
        req.setDepartmentId(Department.Security.ordinal());
        req.setSiteId((int) MockEmployeeDao.MOCK_SITE_ID);
        req.setSecurityLevel(MOCK_SECURITY_LEVEL);
        long id = employeeService.addEmployee(req);
        Assert.assertTrue(id == employeeService.getEmployeeCount());
    }
    
    @Test
    public void testGetEmployee() {
        addMockEmployee();
        Employee drone = employeeService.getEmployee(1);
        LOGGER.debug("Drone returned: " + drone);
        Assert.assertTrue(MOCK_DRONE_NAME.equals(drone.getName()));
    }
    
    @Test
    public void testGetEmployeeWorkplace() {
        addMockEmployee();
        Site theSite = employeeService.getEmployeeWorksite(1);
        Assert.assertTrue(theSite.getId() == MockEmployeeDao.MOCK_SITE_ID);
        Assert.assertTrue(theSite.getCountry().equals(MockEmployeeDao.MOCK_SITE_COUNTRY));
        Assert.assertTrue(theSite.getRegion().equals(MockEmployeeDao.MOCK_SITE_REGION));
        Assert.assertTrue(theSite.getPostalAddress().equals(MockEmployeeDao.MOCK_SITE_ADDRESS));
        Assert.assertTrue(theSite.getMinimumSecurityLevel() == SecurityRating.MINIMUM);
    }
}
