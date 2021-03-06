/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.dao;

import com.aceade.corpolc.inventory.database.Queries;
import com.aceade.corpolc.inventory.database.SiteRowMapper;
import com.aceade.corpolc.inventory.model.base.Site;
import com.aceade.corpolc.inventory.model.request.NewSiteRequest;
import com.aceade.corpolc.inventory.model.supplies.Item;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 * @author philip
 */
public class SiteDaoImpl extends BaseDao implements SiteDao {
    
    private static final Logger LOGGER = LogManager.getLogger(SiteDaoImpl.class);
    
    @Override
    public Site getSite(long id) {
        LOGGER.info("Retrieving site with id ["+id +"]");
        String sql = Queries.SELECT_SITE;
        Site site = (Site) jdbcTemplate.query(sql, new SiteRowMapper(), id).get(0);
        return site;
    }
    
    @Override
    public List<Site> getAllSites() {
        String sql = Queries.SELECT_ALL_SITES;
        List<Site> sites = jdbcTemplate.query(sql, new SiteRowMapper());
        return sites;
    }
    
    @Override
    public int getSiteCount (){
        String sql = Queries.COUNT_SITES;
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
    
    @Override
    public boolean addSite(NewSiteRequest newSite) {
        String sql = Queries.ADD_SITE;
        int amount = getSiteCount();
        int rowsAffected = jdbcTemplate.update(sql, amount+1, newSite.getCountry(), newSite.getRegion(), newSite.getPostalAddress(), newSite.getSecurityLevel());
        return rowsAffected == 1;        
    }
    
    @Override
    public boolean deleteSite(long id) {
        String sql = Queries.DELETE_SITE;
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected == 1;
    }
    
    /**
     * A deliberately weak query for testing SQL injection. DO NOT USE for serious purposes!
     * @param id
     * @return 
     */
    @Override
    public Site getSiteWithWeakQuery(String id) {
        Site theSite = null;
        String sql = "SELECT * FROM sites WHERE id = " + id;
        Connection dbConnection = connectionFactory.getConnection();
        try (Statement statement = dbConnection.createStatement() ) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                theSite = new SiteRowMapper().mapRow(rs, 0);    
            }
            
        } catch (SQLException e) {
            LOGGER.error("Could not retrieve site with id ["+id+"]...weakly", e);
        }
        
        return theSite;
    }

    @Override
    public boolean isWorkerAtSite(long employeeId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Site getSiteWithAddress(long siteId) {
        return getSite(siteId);
    }

    @Override
    public Site getFullSiteDetails(long siteId) {
        return getSite(siteId);
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
    public long getSiteId(NewSiteRequest req) {
        String sql = Queries.GET_SITE_ID;
        return jdbcTemplate.queryForObject(sql, Long.class, req.getCountry(), req.getRegion(), req.getPostalAddress());
    }
}
