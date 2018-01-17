/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.dao;

import com.aceade.corpolc.inventory.database.Queries;
import com.aceade.corpolc.inventory.model.base.Site;
import com.aceade.corpolc.inventory.model.request.NewSiteRequest;
import com.aceade.corpolc.inventory.services.ServiceLibrary;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author philip
 */
public class SiteDao extends BaseDao {
    
    public Site getSite(long id) {
        String sql = Queries.SELECT_SITE;
        Site site = null;
        
        Connection dbConnection = connectionFactory.getConnection();
        
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                site = createSite(rs);
            }
            
        } catch (SQLException e) {
            System.err.println(e);
        }
        
        return site;
    }
    
    public List<Site> getAllSites() {
        String sql = Queries.SELECT_ALL_SITES;
        List<Site> sites = new ArrayList<>();
        
        Connection dbConnection = connectionFactory.getConnection();
        
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Site site = createSite(rs);
                sites.add(site);
            }
            
        } catch (SQLException e) {
            System.err.println(e);
        }
        
        return sites;
    }
    
    public int getSiteCount (){
        String sql = Queries.COUNT_SITES;
        int amount = 0;
        
        Connection dbConnection = connectionFactory.getConnection();
        
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                amount = rs.getInt("count");
            }
            
        } catch (SQLException e) {
            System.err.println(e);
        }
        
        return amount;
    }
    
    public boolean addSite(NewSiteRequest newSite) {
        String sql = Queries.ADD_SITE;
        int amount = getSiteCount();
        Connection dbConnection = connectionFactory.getConnection();
        boolean success = false;
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            stmt.setInt(1, amount);
            stmt.setString(2, newSite.getCountry());
            stmt.setString(3, newSite.getRegion());
            stmt.setString(4, newSite.getPostalAddress());
            stmt.setInt(5, newSite.getSecurityLevel());
            success = stmt.execute();
            
        } catch (SQLException e) {
            System.err.println(e);
        }
        
        return success;
    }
    
    public boolean deleteSite(long id) {
        String sql = Queries.DELETE_SITE;
        Connection dbConnection = connectionFactory.getConnection();
        boolean successful = false;
        try (PreparedStatement stmt = dbConnection.prepareStatement(sql)) {
            stmt.setLong(1, id);
            successful = stmt.execute();
            
        } catch (SQLException e) {
            System.err.println(e);
        }
        return successful;
    }
    
    private Site createSite(ResultSet rs) throws SQLException {
        Site site = new Site(rs.getLong("id"));
        site.setPostalAddress(rs.getString("postalAddress"));
        site.setCountry(rs.getString("country"));
        site.setRegion(rs.getString("region"));
        site.setMinimumSecurityLevel(ServiceLibrary.getSecurityRating(rs.getInt("securityLevel")));
        return site;
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
            while (rs.next()) {
                theSite = createSite(rs);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        
        return theSite;
    }
}
