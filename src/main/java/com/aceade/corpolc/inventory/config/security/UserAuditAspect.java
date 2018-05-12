/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.config.security;

import com.aceade.corpolc.inventory.services.AuditService;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Handles auditing of the user URLs
 * @author philip
 */
@Aspect
@Component
public class UserAuditAspect {
    
    private static final Logger LOGGER = LogManager.getLogger(UserAuditAspect.class);
    
    @Inject
    private AuditService auditService;
    
    // Create a Pointcut that is called when this method is called
    @Pointcut("execution(* com.aceade.corpolc.inventory.rest.controllers.UserController.disableUser(..))")
    private void userStatusChange(){}
    
    // run things before it
    @Before("userStatusChange()")
    public void logUserStatusChange(JoinPoint joinPoint) {
        LOGGER.info("Logging a user status change: " + joinPoint);
        String user = joinPoint.getArgs()[0].toString();
        String remoteUser = ((HttpServletRequest)joinPoint.getArgs()[1]).getRemoteUser();
        auditService.logUserStatusChange(remoteUser, user);
    }
}
