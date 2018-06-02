/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.dao;

import com.aceade.corpolc.inventory.model.base.Site;
import com.aceade.corpolc.inventory.model.request.NewSiteRequest;
import com.aceade.corpolc.inventory.model.supplies.Item;
import java.util.List;

/**
 *
 * @author philip
 */
public interface SiteDao {

    boolean addSite(NewSiteRequest newSite);

    boolean deleteSite(long id);

    List<Site> getAllSites();

    Site getFullSiteDetails(long siteId);

    Site getSite(long id);

    int getSiteCount();

    long getSiteId(NewSiteRequest req);

    Site getSiteWithAddress(long siteId);

    /**
     * A deliberately weak query for testing SQL injection. DO NOT USE for serious purposes!
     * @param id
     * @return
     */
    Site getSiteWithWeakQuery(String id);

    List<Item> getSuppliesAtSite(long siteId);

    List<Item> getSuppliesOfTypeAtSite(long siteId);

    boolean isWorkerAtSite(long employeeId);
    
}
