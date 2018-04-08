/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.services;

import com.aceade.corpolc.inventory.dao.SiteDao;
import com.aceade.corpolc.inventory.model.base.Site;
import com.aceade.corpolc.inventory.model.request.NewSiteRequest;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 *
 * @author philip
 */
@Singleton
public class SiteService {
    
    @Inject
    private SiteDao siteDao;
    
    public List<Site> viewAllSites(){
        return siteDao.getAllSites();
    }
    
    public int getTotalSiteCount() {
        return siteDao.getSiteCount();
    }
    
    /**
     * Return the specified site, with less details.
     * @param id
     * @return 
     */
    public Site getSite(long id) {
        Site theSite = siteDao.getSite(id);
        theSite.setPostalAddress("");
        theSite.setRegion("");
        return theSite;
    }
    
    public boolean createSite(NewSiteRequest newSite){
        return siteDao.addSite(newSite);
    }
    
    public boolean deleteSite(long id) {
        return siteDao.deleteSite(id);
    }
    
    public boolean isWorkerAtSite(long employeeId) {
        return siteDao.isWorkerAtSite(employeeId);
    }
    
    public Site getSiteBadly(String id) {
        return siteDao.getSiteWithWeakQuery(id);
    }

    public Site getSiteWithAddress(long siteId) {
        return siteDao.getSiteWithAddress(siteId);
    }

    /**
     * Return the full site details. Currently indistinguishable from getSiteWithAddresses
     * @param siteId
     * @return 
     */
    public Site getFullSiteDetails(long siteId) {
        return siteDao.getFullSiteDetails(siteId);
    }
    
}
