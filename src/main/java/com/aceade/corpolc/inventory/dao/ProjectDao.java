/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.dao;

import com.aceade.corpolc.inventory.model.base.Employee;
import com.aceade.corpolc.inventory.model.base.Project;
import com.aceade.corpolc.inventory.model.base.Site;
import com.aceade.corpolc.inventory.model.request.ChangeProjectStatusRequest;
import com.aceade.corpolc.inventory.model.request.NewProjectRequest;
import java.util.List;

/**
 *
 * @author philip
 */
public interface ProjectDao {

    long addNewProject(NewProjectRequest newProjectRequest);

    List<Project> getAll();

    List<Employee> getEmployeesForProject(long projectId);

    Project getProject(long id, boolean sanitise);

    /**
     * Used only for auditing. Exists so I can get the project ID
     * @param request
     * @return
     */
    long getProjectId(NewProjectRequest request);

    List<Project> getProjectsForEmployee(long employeeId, boolean sanitise);

    List<Site> getSitesForProject(long id);

    int getTotalProjectCount();

    boolean isUserOnProject(long projectId, String username);

    boolean setProjectStatus(ChangeProjectStatusRequest request);
    
}
