/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.rest.controllers;

import com.aceade.corpolc.inventory.model.base.Project;
import com.aceade.corpolc.inventory.model.base.Role;
import com.aceade.corpolc.inventory.model.request.ChangeProjectStatusRequest;
import com.aceade.corpolc.inventory.model.request.NewProjectRequest;
import com.aceade.corpolc.inventory.model.response.AddResourceResponse;
import com.aceade.corpolc.inventory.services.EmployeeService;
import com.aceade.corpolc.inventory.services.ProjectService;
import com.aceade.corpolc.inventory.services.SiteService;
import java.util.List;
import javax.inject.Inject;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
@RequestMapping("/projects")
public class ProjectController {
    
    private static final Logger LOGGER = LogManager.getLogger(ProjectController.class);
    
    @Inject
    private ProjectService projectService;
    
    @Inject
    private EmployeeService employeeService;
    
    @Inject
    private SiteService siteService;
    
    @Secured({Role.ROLE_FULL_ADMIN, Role.ROLE_FULL_READONLY})
    @RequestMapping(method = RequestMethod.GET, value="/all")
    public List<Project> getAllProjects(){
        LOGGER.info("Returning all projects");
        return projectService.getAllProjects();
    }
    
    @RequestMapping(method = RequestMethod.GET, value="")
    public Project getProject(@RequestParam(value= "id", required=true) long id) {
        LOGGER.info("Returning project with id ["+id+"]");
        Project project = projectService.getProject(id);  
        
        // TODO: filter based on user roles
        project.setEmployees(projectService.getEmployeesOnProject(id));
        project.setSites(projectService.getSitesForProject(id));     
        return project;
    }
    
    @RequestMapping(method = RequestMethod.POST, value="")
    public ResponseEntity<AddResourceResponse> addProject(@RequestBody(required = true) NewProjectRequest newProjectRequest) {
        AddResourceResponse response = new AddResourceResponse();
        HttpStatus status;
        long id = projectService.addProject(newProjectRequest);
        
        if (id > 0) {
            status = HttpStatus.OK;
            response.setNewResourceId(id);
            response.setSuccess(true);
            response.setResponseText("Successfully added new project");
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            response.setResponseText("Could not add new project");
        }
        
        return new ResponseEntity<>(response, status);
    }
    
    @Secured({Role.ROLE_FULL_ADMIN, Role.ROLE_PROJECT_ADMIN})
    @RequestMapping(method = RequestMethod.POST, value="/status")
    public ResponseEntity<Boolean> changeProjectStatus(@RequestBody ChangeProjectStatusRequest request) {
        
        // TODO: check if the user is the admin of the project
        boolean success = projectService.changeProjectStatus(request);
        
        return new ResponseEntity(success, HttpStatus.OK);
    }
}
