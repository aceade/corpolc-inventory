/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.dao;

import com.aceade.corpolc.inventory.database.ItemRowMapper;
import com.aceade.corpolc.inventory.database.OrderRowMapper;
import com.aceade.corpolc.inventory.database.SiteStockMapper;
import com.aceade.corpolc.inventory.database.Queries;
import com.aceade.corpolc.inventory.model.request.ChangeOrderStatusRequest;
import com.aceade.corpolc.inventory.model.request.NewItemRequest;
import com.aceade.corpolc.inventory.model.request.NewOrderRequest;
import com.aceade.corpolc.inventory.model.supplies.Item;
import com.aceade.corpolc.inventory.model.supplies.Order;
import com.aceade.corpolc.inventory.model.supplies.SupplyType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 *
 * @author philip
 */
public class SupplyDaoImpl implements SupplyDao {

    private static final Logger LOGGER = LogManager.getLogger(SupplyDaoImpl.class);

    @Inject
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addItem(NewItemRequest newItem) {
        String sql = Queries.ADD_ITEM;
        jdbcTemplate.update(sql, newItem.getName(), newItem.getBuyingPrice(), newItem.getSellingPrice(), newItem.getWeightPerUnit(), newItem.isConsumable(), newItem.getType().name());
    }

    @Override
    public Item getItem(long itemId) {
        String sql = Queries.GET_ITEM;
        return (Item) jdbcTemplate.queryForObject(sql, new ItemRowMapper(), itemId);
    }
    
    public List<Item> getAllItems() {
		String sql = Queries.GET_ALL_ITEMS;
		return jdbcTemplate.query(sql, new ItemRowMapper());
	}

    @Override
    public void placeOrder(NewOrderRequest newOrderRequest, String username) {
        long siteId = newOrderRequest.getSiteId();
        Map<Long, Integer> orderItems = newOrderRequest.getOrderItems();
        Date orderDate = newOrderRequest.getOrderDate();
        String orderSql = Queries.ADD_ORDER;
        String itemSql = Queries.ADD_ORDER_ITEM;

        // a KeyHolder allows me to get the order of the inserted object...handy!
        KeyHolder kh = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

                // it turns out that the ID column must be specified...
                PreparedStatement st = connection.prepareStatement(orderSql, new String[]{"orderId"});
                st.setLong(1, siteId);
                st.setDate(2, new java.sql.Date(orderDate.getTime()));
                st.setString(3, username);
                return st;
            }

        }, kh);
        long orderId = kh.getKey().longValue();
        LOGGER.info("Order ID is " + orderId);
        LOGGER.info("Adding " + orderItems.size() + " items");

        // loop through the items and insert them.
        for (Map.Entry<Long, Integer> entry : orderItems.entrySet()) {
            jdbcTemplate.update(itemSql, orderId, entry.getKey(), entry.getValue());
        }

    }

    @Override
    public Order viewOrder(long orderId) {
        String sql = Queries.GET_ORDER;
        Order order = (Order) jdbcTemplate.queryForObject(sql, new OrderRowMapper(), orderId);

        order.setItems(getOrderItems(orderId));
        return order;
    }

    @Override
    public Map<Item, Integer> getOrderItems(long orderId) {
        String sql = Queries.GET_ORDER_ITEMS;

        // using a ResultSetExtractor allows me to define HOW I generate the map
        Map<Item, Integer> items = (Map<Item, Integer>) jdbcTemplate.query(sql, new ResultSetExtractor() {
            @Override
            public Map<Item, Integer> extractData(ResultSet rs) throws SQLException, DataAccessException {
                Map<Item, Integer> results = new HashMap<>();
                while (rs.next()) {
                    Item item = new Item();
                    item.setName(rs.getString("name"));
                    item.setBuyingPrice(rs.getDouble("buying_price"));
                    item.setSellingPrice(rs.getDouble("selling_price"));
                    item.setConsumable(rs.getBoolean("consumable"));
                    item.setWeightPerUnit(rs.getDouble("weight"));
                    item.setType(SupplyType.valueOf(rs.getString("type")));
                    results.put(item, rs.getInt("quantity"));
                }
                LOGGER.info(results.size());
                return results;
            }

        }, orderId);

        return items;
    }

    @Override
    public List<Order> viewOrdersByUser(String username) {
        String sql = Queries.GET_ORDERS_BY_USER;
        return jdbcTemplate.query(sql, new OrderRowMapper(), username);
    }

    @Override
    public void updateOrderStatus(ChangeOrderStatusRequest changeOrder) {
        String sql = Queries.SET_ORDER_STATUS;
        jdbcTemplate.update(new PreparedStatementCreator(){
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1, changeOrder.getNewStatus().name());
                st.setLong(2, changeOrder.getOrderId());
                return st;
            }
        });
    }

    @Override
    public List<Item> getItemsWithName(String name) {
        String sql = Queries.GET_ITEMS_BY_NAME;
        return jdbcTemplate.query(sql, new ItemRowMapper(), "%"+name +"%");
    }

    @Override
    public Map<Item, Integer> getSiteStocks(long siteId) {
        String sql = Queries.GET_SITE_STOCKS;
        return (Map<Item, Integer>) jdbcTemplate.query(sql, new SiteStockMapper(), siteId);
    }

}
