/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.services;

import com.aceade.corpolc.inventory.dao.ProjectDao;
import com.aceade.corpolc.inventory.model.base.Employee;
import com.aceade.corpolc.inventory.model.base.Project;
import com.aceade.corpolc.inventory.model.base.Site;
import com.aceade.corpolc.inventory.model.request.ChangeProjectStatusRequest;
import com.aceade.corpolc.inventory.model.request.NewProjectRequest;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 *
 * @author philip
 */
@Singleton
public class ProjectService {
    
    @Inject
    private ProjectDao projectDao;
    
    public List<Project> getAllProjects(){
        return projectDao.getAll();
    }
    
    public Project getProject(long id) {
        return projectDao.getProject(id);
    }
    
    public int getProjectCount() {
        return projectDao.getTotalProjectCount();
    }
    
    public List<Employee> getEmployeesOnProject(long projectId) {
        return projectDao.getEmployeesForProject(projectId);
    }
    
    public List<Site> getSitesForProject(long projectId) {
        return projectDao.getSitesForProject(projectId);
    }
    
    public long addProject(NewProjectRequest newProjectRequest){
        return projectDao.addNewProject(newProjectRequest);
    }

    public List<Project> getProjectsForEmployee(long id) {
        return projectDao.getProjectsForEmployee(id, false);
    }

    public boolean changeProjectStatus(ChangeProjectStatusRequest request) {
        return projectDao.setProjectStatus(request);
    }

    public boolean isUserOnProject(long projectId, String username) {
        return projectDao.isUserOnProject(projectId, username);
    }

    /**
     * Return a sanitised list of projects. These don't show the summary, budget or status.
     * @param id
     * @return 
     */
    public List<Project> getSanitisedProjectsForEmployee(long id) {
        return projectDao.getProjectsForEmployee(id, true);
    }
    
}
