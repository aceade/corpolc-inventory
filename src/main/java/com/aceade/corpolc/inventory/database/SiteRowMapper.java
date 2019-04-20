/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.database;

import com.aceade.corpolc.inventory.model.base.Site;
import com.aceade.corpolc.inventory.services.ServiceLibrary;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author philip
 */
public class SiteRowMapper implements RowMapper {

    @Override
    public Site mapRow(ResultSet rs, int i) throws SQLException {
        Site site = new Site(rs.getLong("id"));
        site.setPostalAddress(rs.getString("address"));
        site.setCountry(rs.getString("country"));
        site.setRegion(rs.getString("region"));
        site.setMinimumSecurityLevel(ServiceLibrary.getSecurityRating(rs.getString("security_level")));
        return site;
    }
    
}
