/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.config;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author philip
 */
@ControllerAdvice
public class MyExceptionHandler {
    
    private static final Logger LOGGER = LogManager.getLogger(MyExceptionHandler.class);
    
    @ExceptionHandler({ AccessDeniedException.class })
    public void handleAccessDeniedException(Exception e) {
        LOGGER.error(e);
    }
}
