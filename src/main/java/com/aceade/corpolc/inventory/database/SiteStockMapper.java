/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.database;

import com.aceade.corpolc.inventory.model.supplies.Item;
import com.aceade.corpolc.inventory.model.supplies.SupplyType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;


/**
 *
 * @author philip
 */
public class SiteStockMapper implements ResultSetExtractor {

    @Override
    public Map<Item, Integer> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<Item, Integer> results = new HashMap<>();
        while (rs.next()) {
            Item item = new Item();
            item.setId(rs.getLong("id"));
            item.setName(rs.getString("name"));
//            item.setBuyingPrice(rs.getDouble("buying_price"));    // this is confidential!!1!
            item.setSellingPrice(rs.getDouble("selling_price"));
            item.setConsumable(rs.getBoolean("consumable"));
            item.setWeightPerUnit(rs.getDouble("weight"));
            item.setType(SupplyType.valueOf(rs.getString("type")));
            results.put(item, rs.getInt("quantity"));
        }
        return results;
    }

  
    
}
