/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.database;

import com.aceade.corpolc.inventory.model.base.Site;
import com.aceade.corpolc.inventory.model.supplies.Order;
import com.aceade.corpolc.inventory.model.supplies.OrderStatus;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author philip
 */
public class OrderRowMapper implements RowMapper {

    @Override
    public Order mapRow(ResultSet rs, int i) throws SQLException {
        Order order = new Order();
        order.setId(rs.getLong("orderId"));
        order.setOrderDate(rs.getDate("orderDate"));
        order.setUsername(rs.getString("username"));
        order.setOrderStatus(OrderStatus.valueOf(rs.getString("status")));
        Site site = new Site();
        site.setCountry(rs.getString("country"));
        site.setPostalAddress(rs.getString("postalAddress"));
        site.setRegion(rs.getString("region"));
        order.setAddress(site);
        return order;
    }
}
