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
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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
    
    private static final Logger LOG = LogManager.getLogger(TestSiteService.class);
    
    @Inject
    private SiteService siteService;
    
    @Test
    public void getFullSiteDetails() {
        // full details should not involve empty details
        Site theSite = siteService.getFullSiteDetails(1);
        LOG.info("Full site: " + theSite);
        Assert.assertFalse(theSite.getCountry().isEmpty());
        Assert.assertFalse(theSite.getRegion().isEmpty());
        Assert.assertFalse(theSite.getPostalAddress().isEmpty());
    }
    
    @Test
    public void getSiteAddress() {
        // currently, this is the same as the full details
        Site theSite = siteService.getSiteWithAddress(1);
        LOG.info("Site with details: " + theSite);
        Assert.assertFalse(theSite.getCountry().isEmpty());
        Assert.assertFalse(theSite.getRegion().isEmpty());
        Assert.assertFalse(theSite.getPostalAddress().isEmpty());
    }
    
    @Test
    public void getSite() {
        Site theSite = siteService.getSite(1);
        LOG.info("Basic site: " + theSite);
        Assert.assertFalse(theSite.getCountry().isEmpty());
        Assert.assertTrue(theSite.getRegion().isEmpty());
        Assert.assertTrue(theSite.getPostalAddress().isEmpty());
    }
}
