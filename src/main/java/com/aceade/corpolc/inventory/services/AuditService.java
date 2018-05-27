/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.services;

import com.aceade.corpolc.inventory.dao.AuditDao;
import com.aceade.corpolc.inventory.dao.EmployeeDao;
import com.aceade.corpolc.inventory.dao.ProjectDao;
import com.aceade.corpolc.inventory.dao.SiteDao;
import com.aceade.corpolc.inventory.model.request.ChangeProjectStatusRequest;
import com.aceade.corpolc.inventory.model.request.NewEmployeeRequest;
import com.aceade.corpolc.inventory.model.request.NewProjectRequest;
import com.aceade.corpolc.inventory.model.request.NewSiteRequest;
import com.aceade.corpolc.inventory.model.request.NewUserRequest;
import com.aceade.corpolc.inventory.model.response.AuditEntry;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author philip
 */
public class AuditService {
    
    @Inject
    private AuditDao auditDao;
    
    @Inject
    private ProjectDao projectDao;
    
    @Inject
    private EmployeeDao employeeDao;
    
    @Inject
    private SiteDao siteDao;

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
        long projectId = projectDao.getProjectId(request);
        auditDao.logNewProject(request, remoteUser, projectId);
    }

    public void logEmployeeAdded(NewEmployeeRequest newEmployeeRequest, String remoteUser) {
        long employeeId = employeeDao.getEmployeeId(newEmployeeRequest);
        auditDao.logEmployeeAdded(newEmployeeRequest, remoteUser, employeeId);
    }

    public void logEmployeeDeletion(long employeeId, String remoteUser) {
        auditDao.logEmployeeStatusChange(employeeId, remoteUser);
    }

    public void logSiteAdded(NewSiteRequest req, String remoteUser) {
        long siteId = siteDao.getSiteId(req);
        auditDao.logSiteAdded(req, remoteUser, siteId);
    }

    public List<AuditEntry> getUserAuditRecords(String userId) {
        return auditDao.getUserAuditRecords(userId);
    }

    public List<AuditEntry> getSiteAuditRecords(long siteId) {
        return auditDao.getSiteAuditRecords(siteId);
    }

    public List<AuditEntry> getProjectAuditRecords(long projectId) {
        return auditDao.getProjectAuditRecords(projectId);
    }

    public List<AuditEntry> getEmployeeAuditRecords(long employeeId) {
        return auditDao.getEmployeeAuditRecords(employeeId);
    }
}
