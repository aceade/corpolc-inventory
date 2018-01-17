/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.config;

import java.io.IOException;
import java.io.PrintWriter;
import javax.naming.AuthenticationException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 *
 * @author philip
 */
@Component
public class MyAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {
    
    
    public void commence
      (HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx) 
      throws IOException, ServletException {
        response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = response.getWriter();
        writer.println("HTTP Status 401 - " + authEx.getMessage());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setRealmName("Corp Olc");
        super.afterPropertiesSet();
    }
}
