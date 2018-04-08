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
public class SiteDao extends BaseDao {
    
    private static final Logger LOGGER = LogManager.getLogger(SiteDao.class);
    
    public Site getSite(long id) {
        LOGGER.info("Retrieving site with id ["+id +"]");
        String sql = Queries.SELECT_SITE;
        Site site = jdbcTemplate.queryForObject(sql, Site.class, id);
        return site;
    }
    
    public List<Site> getAllSites() {
        String sql = Queries.SELECT_ALL_SITES;
        List<Site> sites = jdbcTemplate.query(sql, new SiteRowMapper());
        return sites;
    }
    
    public int getSiteCount (){
        String sql = Queries.COUNT_SITES;
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
    
    public boolean addSite(NewSiteRequest newSite) {
        String sql = Queries.ADD_SITE;
        int amount = getSiteCount();
        int rowsAffected = jdbcTemplate.update(sql, amount+1, newSite.getCountry(), newSite.getRegion(), newSite.getPostalAddress(), newSite.getSecurityLevel());
        return rowsAffected == 1;        
    }
    
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
    public Site getSiteWithWeakQuery(String id) {
        Site theSite = null;
        String sql = "SELECT * FROM sites WHERE id = " + id;
        Connection dbConnection = connectionFactory.getConnection();
        try (Statement statement = dbConnection.createStatement() ) {
            ResultSet rs = statement.executeQuery(sql);
            theSite = new SiteRowMapper().mapRow(rs, 0);
        } catch (SQLException e) {
            LOGGER.error("Could not retrieve site with id ["+id+"]...weakly", e);
        }
        
        return theSite;
    }
}
