/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.model.request;

import com.aceade.corpolc.inventory.model.base.ProjectStatus;

/**
 *
 * @author philip
 */
public class ChangeProjectStatusRequest {
    
    private long projectId;
    
    private ProjectStatus newProjectStatus;
    
    public ChangeProjectStatusRequest() {
        // required for JSON conversion
    }

    /**
     * @return the projectId
     */
    public long getProjectId() {
        return projectId;
    }

    /**
     * @param projectId the projectId to set
     */
    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    /**
     * @return the newProjectStatus
     */
    public ProjectStatus getNewProjectStatus() {
        return newProjectStatus;
    }

    /**
     * @param newProjectStatus the newProjectStatus to set
     */
    public void setNewProjectStatus(ProjectStatus newProjectStatus) {
        this.newProjectStatus = newProjectStatus;
    }
    
    
}
