/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.services;

import com.aceade.corpolc.inventory.dao.AuditDao;
import com.aceade.corpolc.inventory.model.request.ChangeProjectStatusRequest;
import com.aceade.corpolc.inventory.model.request.NewProjectRequest;
import com.aceade.corpolc.inventory.model.request.NewUserRequest;
import javax.inject.Inject;

/**
 *
 * @author philip
 */
public class AuditService {
    
    @Inject
    private AuditDao auditDao;

    /**
     * 
     * @param remoteUser - the user who is requesting this
     * @param user - the user who needs to be changed
     */
    public void logUserStatusChange(String remoteUser, String user) {
        auditDao.logUserStatusChange(remoteUser, user);
    }

    public void logUserAdded(NewUserRequest request, String remoteUser) {
        auditDao.logNewUser(request, remoteUser);
    }

    public void logProjectStatusChange(ChangeProjectStatusRequest request, String remoteUser) {
        auditDao.logProjectStatusChange(request, remoteUser);
    }

    public void logProjectAdded(NewProjectRequest request, String remoteUser) {
        auditDao.logNewProject(request, remoteUser);
    }
}
