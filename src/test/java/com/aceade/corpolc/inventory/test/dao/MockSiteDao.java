/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.test.dao;

import com.aceade.corpolc.inventory.dao.SiteDao;
import com.aceade.corpolc.inventory.model.base.Site;
import com.aceade.corpolc.inventory.model.request.NewSiteRequest;
import com.aceade.corpolc.inventory.model.supplies.Item;
import com.aceade.corpolc.inventory.services.ServiceLibrary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author philip
 */
public class MockSiteDao implements SiteDao {
    
    Map<Long, Site> sites = new HashMap<>();

    @Override
    public boolean addSite(NewSiteRequest newSite) {
        Site theSite = new Site();
        theSite.setCountry(newSite.getCountry());
        theSite.setRegion(theSite.getRegion());
        theSite.setPostalAddress(theSite.getPostalAddress());
        theSite.setId(sites.size() + 1);
        theSite.setMinimumSecurityLevel(ServiceLibrary.getSecurityRating(newSite.getSecurityLevel()));
        sites.put(theSite.getId(), theSite);
        return true;
    }

    @Override
    public boolean deleteSite(long id) {
        if (sites.containsKey(id)) {
            sites.remove(id);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public List<Site> getAllSites() {
        return (List<Site>) sites.values();
    }

    @Override
    public Site getFullSiteDetails(long siteId) {
        return sites.get(siteId);
    }

    @Override
    public Site getSite(long id) {
        return getFullSiteDetails(id);
    }

    @Override
    public int getSiteCount() {
        return sites.size();
    }

    @Override
    public long getSiteId(NewSiteRequest req) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Site getSiteWithAddress(long siteId) {
        return getFullSiteDetails(siteId);
    }

    @Override
    public Site getSiteWithWeakQuery(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Item> getSuppliesAtSite(long siteId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Item> getSuppliesOfTypeAtSite(long siteId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isWorkerAtSite(long employeeId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
