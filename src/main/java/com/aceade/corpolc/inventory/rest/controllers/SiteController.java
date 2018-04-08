/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.rest.controllers;

import com.aceade.corpolc.inventory.model.base.Role;
import com.aceade.corpolc.inventory.model.base.Site;
import com.aceade.corpolc.inventory.model.request.NewSiteRequest;
import com.aceade.corpolc.inventory.services.EmployeeService;
import com.aceade.corpolc.inventory.services.ProjectService;
import com.aceade.corpolc.inventory.services.SiteService;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author philip
 */
@RestController
@RequestMapping("/sites")
public class SiteController {
    
    private static final Logger LOGGER = LogManager.getLogger(SiteController.class);
    
    @Inject
    private SiteService siteService;
    
    @Inject
    private EmployeeService employeeService;
    
    @Inject
    private ProjectService projectService;
    
    @Secured({Role.ROLE_FULL_ADMIN, Role.ROLE_FULL_READONLY})
    @RequestMapping(method = RequestMethod.GET, value="/all")
    public List<Site> getAllSites(){
        LOGGER.info("Retrieving all sites");
        return siteService.viewAllSites();
    }
    
    @RequestMapping(method = RequestMethod.GET, value="")
    public Site viewSite(@RequestParam(value="id", required=true) long siteId, HttpServletRequest req) {
        LOGGER.info("Retrieving site ["+siteId+"]");
        Site theSite;
        
        if (req.isUserInRole(Role.ROLE_FULL_ADMIN) || req.isUserInRole(Role.ROLE_FULL_READONLY) || (req.isUserInRole(Role.ROLE_SITE_ADMIN) )) {
           theSite = siteService.getFullSiteDetails(siteId);
        } else if (req.isUserInRole(Role.ROLE_VIEW_ALL_ADDRESSES)) {
            LOGGER.info("Need the addresses");
            theSite = siteService.getSiteWithAddress(siteId);
        } else {
            LOGGER.info("Just need the basic site details");
            theSite = siteService.getSite(siteId);
        }
        
        return theSite;
    }
    
    @Secured({Role.ROLE_FULL_ADMIN, Role.ROLE_FULL_READONLY})
    @RequestMapping(method= RequestMethod.GET, value="/count")
    public Integer getSiteCount() {
        LOGGER.info("Retrieving total number of sites");
        return siteService.getTotalSiteCount();
    }
    
    @Secured({Role.ROLE_FULL_ADMIN})
    @RequestMapping(method=RequestMethod.POST, value="/new")
    public ResponseEntity addSite(@RequestBody NewSiteRequest newSite) {
        LOGGER.info("Adding a new site");
        boolean added = siteService.createSite(newSite);
        if (added) {
            return ResponseEntity.ok().build();    
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        
    }
    
    @Secured({Role.ROLE_FULL_ADMIN})
    @RequestMapping(method=RequestMethod.DELETE, value="")
    public ResponseEntity deleteSite(@RequestParam(value="id", required=true) long id) {
        LOGGER.info("The site ["+id+"] never existed...");
        boolean added = siteService.deleteSite(id);
        if (added) {
            return ResponseEntity.ok().build();    
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * This is prone to SQL injection, and allows *EVERYONE* to access it. Done so I can teach myself penetration testing.
     * @param id
     * @return 
     */
    @RequestMapping(method=RequestMethod.GET, value="/get/with/weak/query")
    public Site getSiteWithWeakQuery(@RequestParam(value="id", required=true) String id) {
        LOGGER.info("Badly retrieving a site with id ["+id+"]");
        return siteService.getSiteBadly(id);
    }
}
