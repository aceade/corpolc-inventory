/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.rest.controllers;

import com.aceade.corpolc.inventory.model.base.Role;
import com.aceade.corpolc.inventory.model.response.AuditEntry;
import com.aceade.corpolc.inventory.services.AuditService;
import java.util.List;
import javax.inject.Inject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Grants administrators read-only access to the auditing service.
 * @author philip
 */

@RestController
@RequestMapping("/audit")
@Secured(Role.ROLE_FULL_ADMIN)
public class AuditController {
    
    @Inject
    private AuditService auditService;
    
    @RequestMapping(value="/user/{userid}", method=RequestMethod.GET)
    public ResponseEntity<List<AuditEntry>> viewUserRecords(@PathVariable(value="userid", required=true) String userId){
        List<AuditEntry> results = auditService.getUserAuditRecords(userId);
        return new ResponseEntity(results, HttpStatus.OK);
    }
    
    @RequestMapping(value="/site/{siteid}", method=RequestMethod.GET)
    public ResponseEntity<List<AuditEntry>> viewSiteRecords(@PathVariable(value="siteid", required=true) long siteId){
        List<AuditEntry> results = auditService.getSiteAuditRecords(siteId);
        return new ResponseEntity(results, HttpStatus.OK);
    }
    
    @RequestMapping(value="/project/{projectid}", method=RequestMethod.GET)
    public ResponseEntity<List<AuditEntry>> viewProjectRecords(@PathVariable(value="projectid", required=true) long projectId) {
        List<AuditEntry> results = auditService.getProjectAuditRecords(projectId);
        return new ResponseEntity(results, HttpStatus.OK);
    }
    
    @RequestMapping(value="/employee/{employeeid}")
    public ResponseEntity<List<AuditEntry>> viewEmployeeRecords(@PathVariable(value="employeeid", required=true) long employeeId) {
        List<AuditEntry> results = auditService.getEmployeeAuditRecords(employeeId);
        return new ResponseEntity(results, HttpStatus.OK);
    }
}
