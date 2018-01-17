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
import com.aceade.corpolc.inventory.model.errors.NotImplementedException;
import java.util.Collections;
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
        return Collections.EMPTY_LIST;
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
    
    public void addProject(){
        throw new NotImplementedException();
    }

    public List<Project> getProjectsForEmployee(long id) {
        return projectDao.getProjectsForEmployee(id);
    }
    
}
