/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.test.dao;

import com.aceade.corpolc.inventory.dao.SiteDao;
import com.aceade.corpolc.inventory.model.base.SecurityRating;
import com.aceade.corpolc.inventory.model.base.Site;
import com.aceade.corpolc.inventory.model.request.NewSiteRequest;
import com.aceade.corpolc.inventory.model.supplies.Item;
import com.aceade.corpolc.inventory.services.ServiceLibrary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 * @author philip
 */
public class MockSiteDao implements SiteDao {
    
    private static final Logger LOG = LogManager.getLogger(MockSiteDao.class);
    
    private final Site mockSite = new Site(1, "Ireland", "Leinster", "Bewley's Cafe, Grafton Street, Dublin", SecurityRating.MINIMUM);
    
    private final Map<Long, Site> sites = new HashMap<>();
    
    public MockSiteDao() {
        sites.put((long)1, mockSite);
    }
    
    @Override
    public boolean addSite(NewSiteRequest newSite) {
        Site theSite = new Site();
        theSite.setCountry(newSite.getCountry());
        theSite.setRegion(newSite.getRegion());
        theSite.setPostalAddress(newSite.getPostalAddress());
        theSite.setId(sites.size() + 1);
        theSite.setMinimumSecurityLevel(ServiceLibrary.getSecurityRating(newSite.getSecurityLevel()));
        sites.put(theSite.getId(), theSite);
        LOG.info("Added site : " + theSite);
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
        Site theSite = sites.get(siteId);
        LOG.info("Site returned: " + theSite);
        return theSite;
    }

    @Override
    public Site getSite(long id) {
        Site theSite = sites.get(id);
        Site returnedSite = new Site();
        returnedSite.setCountry(theSite.getCountry());
        returnedSite.setId(theSite.getId());
        return returnedSite;
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
