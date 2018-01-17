/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.services;

import com.aceade.corpolc.inventory.dao.SiteDao;
import com.aceade.corpolc.inventory.model.base.Site;
import com.aceade.corpolc.inventory.model.errors.NotImplementedException;
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
    
    public Site getSite(long id) {
        return siteDao.getSite(id);
    }
    
    public boolean createSite(NewSiteRequest newSite){
        return siteDao.addSite(newSite);
    }
    
    public boolean deleteSite(long id) {
        return siteDao.deleteSite(id);
    }
    
    public Site getSiteBadly(String id) {
        return siteDao.getSiteWithWeakQuery(id);
    }
    
}
