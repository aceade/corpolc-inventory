/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.rest.controllers;

import com.aceade.corpolc.inventory.model.base.SecurityRating;
import com.aceade.corpolc.inventory.model.base.Site;
import com.aceade.corpolc.inventory.model.request.NewSiteRequest;
import com.aceade.corpolc.inventory.services.EmployeeService;
import com.aceade.corpolc.inventory.services.ProjectService;
import com.aceade.corpolc.inventory.services.SiteService;
import java.util.List;
import javax.inject.Inject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    
    @Inject
    private SiteService siteService;
    
    @Inject
    private EmployeeService employeeService;
    
    @Inject
    private ProjectService projectService;
    
    @RequestMapping(method = RequestMethod.GET, value="/getAll")
    public List<Site> getAllSites(){
        return siteService.viewAllSites();
    }
    
    @RequestMapping(method = RequestMethod.GET, value="/get")
    public Site viewSite(@RequestParam(value="id", required=true) long siteId) {
        return siteService.getSite(siteId);
    }
    
    @RequestMapping(method= RequestMethod.GET, value="/count")
    public Integer getSiteCount() {
        return siteService.getTotalSiteCount();
    }
    
    @RequestMapping(method=RequestMethod.POST, value="/add")
    public ResponseEntity addSite(@RequestBody NewSiteRequest newSite) {
        boolean added = siteService.createSite(newSite);
        if (added) {
            return ResponseEntity.ok().build();    
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        
    }
    
    @RequestMapping(method=RequestMethod.DELETE, value="/")
    public ResponseEntity deleteSite(@RequestParam(value="id", required=true) long id) {
        boolean added = siteService.deleteSite(id);
        if (added) {
            return ResponseEntity.ok().build();    
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * This is prone to SQL injection. Done so I can teach myself penetration testing.
     * @param id
     * @return 
     */
    @RequestMapping(method=RequestMethod.GET, value="/get/with/weak/query/why/does/this/exist")
    public Site getSiteWithWeakQuery(@RequestParam(value="id", required=true) String id) {
        return siteService.getSiteBadly(id);
    }
}
