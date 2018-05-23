/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.config.security;

import com.aceade.corpolc.inventory.model.request.ChangeProjectStatusRequest;
import com.aceade.corpolc.inventory.model.request.NewProjectRequest;
import com.aceade.corpolc.inventory.services.AuditService;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Logs when various projects change.
 * @author philip
 */
@Aspect
@Component
public class ProjectAuditAspect {
    
    @Inject
    private AuditService auditService;
    
    // this will be called when this method runs
    @Pointcut("execution(* com.aceade.corpolc.inventory.rest.controllers.ProjectController.changeProjectStatus(..))")
    private void projectStatusChanged(){}
    
    @Before("projectStatusChanged()")
    public void logProjectStatusChange(JoinPoint joinPoint) {
        ChangeProjectStatusRequest request = (ChangeProjectStatusRequest) joinPoint.getArgs()[0];
        HttpServletRequest sr = (HttpServletRequest) joinPoint.getArgs()[1];
        auditService.logProjectStatusChange(request, sr.getRemoteUser());
    }
    
    @Pointcut("execution(* com.aceade.corpolc.inventory.rest.controllers.ProjectController.addProject(..))")
    private void projectAdded(){}
    
    @After("projectAdded()")
    public void logProjectAdded(JoinPoint joinPoint) {
        NewProjectRequest request = (NewProjectRequest) joinPoint.getArgs()[0];
        HttpServletRequest sr = (HttpServletRequest) joinPoint.getArgs()[1];
        auditService.logProjectAdded(request, sr.getRemoteUser());
    }
}
