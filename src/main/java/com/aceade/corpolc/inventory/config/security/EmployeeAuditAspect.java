/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.config.security;

import com.aceade.corpolc.inventory.model.request.NewEmployeeRequest;
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
public class EmployeeAuditAspect {
    
    @Inject
    private AuditService auditService;
    
    // this will be called when this method runs
    @Pointcut("execution(* com.aceade.corpolc.inventory.rest.controllers.EmployeeController.addEmployee(..))")
    private void employeeAdded(){}
    
    @After("employeeAdded()")
    private void logEmployeeAdded(JoinPoint joinPoint) {
        NewEmployeeRequest newEmployeeRequest = (NewEmployeeRequest) joinPoint.getArgs()[0];
        HttpServletRequest httpServletRequest = (HttpServletRequest) joinPoint.getArgs()[1];
        auditService.logEmployeeAdded(newEmployeeRequest, httpServletRequest.getRemoteUser());
    }
    
    @Pointcut("execution (* com.aceade.corpolc.inventory.rest.controllers.EmployeeController.deleteEmployee(..))")
    private void employeeTerminated(){}
    
    @Before("employeeTerminated()")
    private void logEmployeeTermination(JoinPoint joinPoint) {
        long employeeId = (long) joinPoint.getArgs()[0];
        HttpServletRequest httpServletRequest = (HttpServletRequest) joinPoint.getArgs()[1];
        auditService.logEmployeeDeletion(employeeId, httpServletRequest.getRemoteUser());
    }
}
