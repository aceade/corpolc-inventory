/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.dao;

import com.aceade.corpolc.inventory.model.request.ChangeProjectStatusRequest;
import com.aceade.corpolc.inventory.model.request.NewEmployeeRequest;
import com.aceade.corpolc.inventory.model.request.NewProjectRequest;
import com.aceade.corpolc.inventory.model.request.NewSiteRequest;
import com.aceade.corpolc.inventory.model.request.NewUserRequest;
import com.aceade.corpolc.inventory.model.response.AuditEntry;
import java.util.List;

/**
 *
 * @author philip
 */
public interface AuditDao {

    List<AuditEntry> getEmployeeAuditRecords(long employeeId);

    List<AuditEntry> getProjectAuditRecords(long projectId);

    List<AuditEntry> getSiteAuditRecords(long siteId);

    List<AuditEntry> getUserAuditRecords(String userId);

    void logEmployeeAdded(NewEmployeeRequest newEmployeeRequest, String remoteUser, long employeeId);

    void logEmployeeStatusChange(long employeeId, String remoteUser);

    void logNewProject(NewProjectRequest request, String remoteUser, long projectId);

    void logNewUser(NewUserRequest request, String remoteUser);

    void logProjectStatusChange(ChangeProjectStatusRequest request, String remoteUser);

    void logSiteAdded(NewSiteRequest req, String remoteUser, long siteId);

    void logUserStatusChange(String remoteUser, String user);
    
}
