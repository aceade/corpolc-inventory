/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.rest.controllers;

import com.aceade.corpolc.inventory.model.base.Project;
import com.aceade.corpolc.inventory.services.EmployeeService;
import com.aceade.corpolc.inventory.services.ProjectService;
import com.aceade.corpolc.inventory.services.SiteService;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.PathParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author philip
 */
@RestController
@RequestMapping("/projects")
public class ProjectController {
    
    @Inject
    private ProjectService projectService;
    
    @Inject
    private EmployeeService employeeService;
    
    @Inject
    private SiteService siteService;
    
    @RequestMapping(method = RequestMethod.GET, value="/all")
    public List<Project> getAllProjects(){
        return projectService.getAllProjects();
    }
    
    @RequestMapping(method = RequestMethod.GET, value="/{id}")
    public Project getProject(@PathParam("id") long id) {
        
        Project project = projectService.getProject(id);      
        project.setEmployees(projectService.getEmployeesOnProject(id));
        project.setSites(projectService.getSitesForProject(id));     
        return project;
    }
}
