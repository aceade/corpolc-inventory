/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.rest.controllers;

import com.aceade.corpolc.inventory.model.base.Employee;
import com.aceade.corpolc.inventory.model.base.Project;
import com.aceade.corpolc.inventory.model.base.Site;
import com.aceade.corpolc.inventory.model.errors.EmployeeSecurityException;
import com.aceade.corpolc.inventory.model.request.NewEmployeeRequest;
import com.aceade.corpolc.inventory.services.EmployeeService;
import com.aceade.corpolc.inventory.services.ProjectService;
import com.aceade.corpolc.inventory.services.SiteService;
import com.aceade.corpolc.inventory.services.UserService;
import java.security.Principal;
import java.util.List;
import javax.inject.Inject;
import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public Employee getEmployee(@RequestParam(value="id", required=true) long id) {
        LOGGER.info("Retrieveing employee with id ["+id+"]");
        return employeeService.getEmployee(id);
    }
    
    @RequestMapping(value="/", method=RequestMethod.POST)
    public ResponseEntity<Boolean> addEmployee(@RequestBody(required = true) NewEmployeeRequest newDrone) throws EmployeeSecurityException {
        LOGGER.info("Adding employee");
        
        // check the security level before we go any further
        Site theSite = siteService.getSite(newDrone.getSiteId());
        int siteSecurity = theSite.getMinimumSecurityLevel().toInt();
        LOGGER.debug("Site security level is " + siteSecurity + "; new employee is " + newDrone.getSecurityLevel());
        if (siteSecurity > newDrone.getSecurityLevel()) {
            
            return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(employeeService.addEmployee(newDrone), HttpStatus.OK);
    }
    
    @RequestMapping(value="/", method=RequestMethod.DELETE)
    public boolean deleteEmployee(@RequestParam(value="id", required=true) long id) {
        LOGGER.info("Terminating employee ["+id+"]");
        return employeeService.deleteEmployee(id);
    }
    
    @RequestMapping(value="/all", method=RequestMethod.GET)
    public List<Employee> getAllEmployees() {
        LOGGER.info("Retrieving all employees");
        return employeeService.getAllEmployees();
    }
    
    @RequestMapping(value="/projects")
    public List<Project> getProjectsForEmployee(@RequestParam(value="id", required=true)long id, HttpServletRequest request){
        LOGGER.info("Retrieving projects for employee ["+id+"]");
        return projectService.getProjectsForEmployee(id);
    }
    
    @RequestMapping(value="/isAdmin")
    public boolean isUserAdmin(UsernamePasswordAuthenticationToken userDetails) {
        LOGGER.info(userDetails.getPrincipal());
        LOGGER.info(userDetails.getAuthorities());
        
        return userDetails.getAuthorities().toString().contains("ROLE_FULL_ADMIN");
    }
}
