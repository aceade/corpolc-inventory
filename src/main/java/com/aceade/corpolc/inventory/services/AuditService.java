/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.services;

import com.aceade.corpolc.inventory.dao.AuditDao;
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
}
