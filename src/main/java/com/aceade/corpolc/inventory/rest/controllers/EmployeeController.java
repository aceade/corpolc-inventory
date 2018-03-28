/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.rest.controllers;

import com.aceade.corpolc.inventory.model.base.Employee;
import com.aceade.corpolc.inventory.model.base.Project;
import com.aceade.corpolc.inventory.model.base.Role;
import com.aceade.corpolc.inventory.model.base.Site;
import com.aceade.corpolc.inventory.model.errors.EmployeeSecurityException;
import com.aceade.corpolc.inventory.model.request.NewEmployeeRequest;
import com.aceade.corpolc.inventory.model.response.AddResourceResponse;
import com.aceade.corpolc.inventory.services.EmployeeService;
import com.aceade.corpolc.inventory.services.ProjectService;
import com.aceade.corpolc.inventory.services.SiteService;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author philip
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController {
    
    private static final Logger LOGGER = LogManager.getLogger(EmployeeController.class);
    
    @Inject
    private EmployeeService employeeService;
    
    @Inject
    private SiteService siteService;
    
    @Inject
    private ProjectService projectService;
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public Employee getEmployee(@RequestParam(value="id", required=true) long id, HttpServletRequest req) {
        LOGGER.info("Retrieveing employee with id ["+id+"]");
        Employee employee = employeeService.getEmployee(id);
        
        // sanitise it
        if (!req.isUserInRole(Role.ROLE_FULL_ADMIN) || !req.isUserInRole(Role.ROLE_FULL_READONLY)) {
            employee.setDepartment(null);
            employee.setWorkplace(null);
            employee.setClearanceLevel(null);
            employee.setUsername(null);
            employee.setSalary(0.00);
        }
        
        return employee;
    }
    
    @Secured({Role.ROLE_FULL_ADMIN})
    @RequestMapping(value="/", method=RequestMethod.POST)
    public ResponseEntity<AddResourceResponse> addEmployee(@RequestBody(required = true) NewEmployeeRequest newDrone) throws EmployeeSecurityException {
        LOGGER.info("Adding employee: " + newDrone);
        
        AddResourceResponse response = new AddResourceResponse();
        HttpStatus status;
        
        // check the security level before we go any further
        Site theSite = siteService.getSite(newDrone.getSiteId());
        int siteSecurity = theSite.getMinimumSecurityLevel().toInt();
        LOGGER.debug("Site security level is " + siteSecurity + "; new employee is " + newDrone.getSecurityLevel());
        if (siteSecurity > newDrone.getSecurityLevel()) {
            LOGGER.error("Site security is too high for this worker! Site security: [" + siteSecurity + "]. Drone security: [" + newDrone.getSecurityLevel() +"]");
            response.setResponseText("This employee does not have sufficient clearance for this site!");
            response.setNewResourceId(0);
            response.setSuccess(false);
            status = HttpStatus.BAD_REQUEST;
        } else {
            
            long newId = employeeService.addEmployee(newDrone);
            response.setNewResourceId(newId);
            if (newId == 0) {
                response.setSuccess(false);
                response.setResponseText("Could not add this new employee. Please consult an administrator");
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            } else {
                status = HttpStatus.OK;
                response.setSuccess(true);
                response.setResponseText("Employee has been added");    
            }            
        }
        
        return new ResponseEntity(response, status);
    }
    
    @Secured({Role.ROLE_FULL_ADMIN})
    @RequestMapping(value="/", method=RequestMethod.DELETE)
    public boolean deleteEmployee(@RequestParam(value="id", required=true) long id) {
        LOGGER.info("Terminating employee ["+id+"]");
        return employeeService.deleteEmployee(id);
    }
    
    @Secured({Role.ROLE_FULL_ADMIN, Role.ROLE_FULL_READONLY})
    @RequestMapping(value="/all", method=RequestMethod.GET)
    public List<Employee> getAllEmployees() {
        LOGGER.info("Retrieving all employees");
        return employeeService.getAllEmployees();
    }
    
    @RequestMapping(value="/projects")
    public List<Project> getProjectsForEmployee(@RequestParam(value="id", required=true)long employeeId, HttpServletRequest request){
        LOGGER.info("Retrieving projects for employee ["+employeeId+"]");
        
        if (!request.isUserInRole(Role.ROLE_FULL_ADMIN) || !request.isUserInRole(Role.ROLE_FULL_READONLY)) {
            return projectService.getSanitisedProjectsForEmployee(employeeId);
        } else {
            return projectService.getProjectsForEmployee(employeeId);    
        }
    }

}
