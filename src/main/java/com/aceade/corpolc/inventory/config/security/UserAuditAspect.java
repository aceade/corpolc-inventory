/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.config.security;

import com.aceade.corpolc.inventory.model.request.NewUserRequest;
import com.aceade.corpolc.inventory.services.AuditService;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
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
    
    // Create a Pointcut for this specific method, with any number of parameters
    @Pointcut("execution(* com.aceade.corpolc.inventory.rest.controllers.UserController.disableUser(..))")
    private void userStatusChange(){}
    
    @Pointcut("execution(* com.aceade.corpolc.inventory.rest.controllers.UserController.addUser(..))")
    private void userAdded(){}
    
    // This will run before the pointcut, allowing me run things before this
    @Before("userStatusChange()")
    public void logUserStatusChange(JoinPoint joinPoint) {
        LOGGER.debug("Logging a user status change: " + joinPoint);
        String user = joinPoint.getArgs()[0].toString();
        String remoteUser = ((HttpServletRequest)joinPoint.getArgs()[1]).getRemoteUser();
        auditService.logUserStatusChange(remoteUser, user);
    }
    
    // This one runs after it, making sure the user exists before auditing
    // At least this will store that it was added.
    @After("userAdded()")
    public void logUserAdded(JoinPoint joinPoint) {
        LOGGER.info("Logging a user added: " + joinPoint);
        NewUserRequest request = (NewUserRequest) joinPoint.getArgs()[0];
        HttpServletRequest hsr = (HttpServletRequest) joinPoint.getArgs()[1];
        auditService.logUserAdded(request, hsr.getRemoteUser());
    }
}
