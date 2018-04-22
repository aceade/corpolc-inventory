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
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author philip
 */
public class ItemRowMapper implements RowMapper {

    @Override
    public Item mapRow(ResultSet rs, int i) throws SQLException {
        Item item = new Item();
        item.setId(rs.getLong("id"));
        item.setName(rs.getString("name"));
        item.setBuyingPrice(rs.getDouble("buying_price"));
        item.setSellingPrice(rs.getDouble("selling_price"));
        item.setConsumable(rs.getBoolean("consumable"));
        item.setWeightPerUnit(rs.getDouble("weight"));
        item.setType(SupplyType.valueOf(rs.getString("type")));
        return item;
    }
    
}
