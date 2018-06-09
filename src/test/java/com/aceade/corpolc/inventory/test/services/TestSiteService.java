/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.test.services;

import com.aceade.corpolc.inventory.model.base.Site;
import com.aceade.corpolc.inventory.model.request.NewSiteRequest;
import com.aceade.corpolc.inventory.services.SiteService;
import com.aceade.corpolc.inventory.test.config.DevConfig;
import javax.inject.Inject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author philip
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={DevConfig.class})
@ActiveProfiles("test")
public class TestSiteService {
    
    @Inject
    private SiteService siteService;
    
    private void addSite() {
        NewSiteRequest newSite = new NewSiteRequest();
        newSite.setCountry("Ireland");
        newSite.setRegion("Leinster");
        newSite.setPostalAddress("Bewley's Cafe, Grafton Street, Dublin");
        siteService.createSite(newSite);
    }
    
    @Test
    public void getFullSiteDetails() {
        addSite();
        // full details should not involve null details
        Site theSite = siteService.getFullSiteDetails(1);
        Assert.assertNotNull(theSite.getCountry());
        Assert.assertNotNull(theSite.getRegion());
        Assert.assertNotNull(theSite.getPostalAddress());
    }
    
    @Test
    public void getSiteAddress() {
        addSite();
        // currently, this is the same
        Site theSite = siteService.getSiteWithAddress(1);
        Assert.assertNotNull(theSite.getCountry());
        Assert.assertNotNull(theSite.getRegion());
        Assert.assertNotNull(theSite.getPostalAddress());
    }
    
    @Test
    public void getSite() {
        addSite();
        // Currently, there is no difference in the methods
        Site theSite = siteService.getSite(1);
        Assert.assertNotNull(theSite.getCountry());
        Assert.assertNotNull(theSite.getRegion());
        Assert.assertNotNull(theSite.getPostalAddress());
    }
}
