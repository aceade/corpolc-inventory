/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.config.security;

import com.aceade.corpolc.inventory.model.request.NewSiteRequest;
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
 *
 * @author philip
 */
@Aspect
@Component
public class SiteAuditAspect {
    
    @Inject
    private AuditService auditService;
    
    @Pointcut("execution(* com.aceade.corpolc.inventory.rest.controllers.SiteController.addSite(..))")
    private void siteAdded(){}
    
    @After("siteAdded()")
    public void logSiteAdded(JoinPoint joinPoint) { 
        NewSiteRequest req = (NewSiteRequest) joinPoint.getArgs()[0];
        HttpServletRequest hsr = (HttpServletRequest) joinPoint.getArgs()[1];
        auditService.logSiteAdded(req, hsr.getRemoteUser());
    }
    
}
