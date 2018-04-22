/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.dao;

import com.aceade.corpolc.inventory.database.ItemRowMapper;
import com.aceade.corpolc.inventory.database.OrderRowMapper;
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
public class SupplyDao {

    private static final Logger LOGGER = LogManager.getLogger(SupplyDao.class);

    @Inject
    private JdbcTemplate jdbcTemplate;

    public void addItem(NewItemRequest newItem) {
        String sql = "INSERT INTO items (name, buying_price, selling_price, weight, consumable, type) VALUES (?, ?, ?, ?, ?, ?::supply_type )";
        jdbcTemplate.update(sql, newItem.getName(), newItem.getBuyingPrice(), newItem.getSellingPrice(), newItem.getWeightPerUnit(), newItem.isConsumable(), newItem.getType().name());
    }

    public Item getItem(long itemId) {
        String sql = "SELECT * FROM items WHERE id = ?";
        return (Item) jdbcTemplate.queryForObject(sql, new ItemRowMapper(), itemId);
    }

    public void placeOrder(NewOrderRequest newOrderRequest, String username) {
        long siteId = newOrderRequest.getSiteId();
        Map<Long, Integer> orderItems = newOrderRequest.getOrderItems();
        Date orderDate = newOrderRequest.getOrderDate();
        String orderSql = "INSERT INTO orders (\"siteId\", \"orderDate\", status, username) VALUES (?, ?, 'SUBMITTED', ?)";
        String itemSql = "INSERT INTO order_items (order_id, item_id, quantity) VALUES (?,?,?)";

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

    public Order viewOrder(long orderId) {
        String sql = "SELECT o.\"orderId\", o.\"orderDate\", o.status, o.username, s.id AS \"siteId\", s.\"postalAddress\", s.country, s.region FROM orders o, sites s WHERE o.\"orderId\" = ? AND o.\"siteId\" = s.id";
        Order order = (Order) jdbcTemplate.queryForObject(sql, new OrderRowMapper(), orderId);

        order.setItems(getOrderItems(orderId));
        return order;
    }

    public Map<Item, Integer> getOrderItems(long orderId) {
        String sql = "SELECT i.name, i.buying_price, i.selling_price, i.weight, i.consumable, i.type, oi.quantity FROM items i, order_items oi WHERE oi.item_id = i.id AND oi.order_id = ?";

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

    public List<Order> viewOrdersByUser(String username) {
        String sql = "SELECT o.\"orderId\", o.\"orderDate\", o.status, o.username, s.id AS \"siteId\", s.\"postalAddress\", s.country, s.region FROM orders o, sites s WHERE o.username = ? AND o.\"siteId\" = s.id";
        return jdbcTemplate.query(sql, new OrderRowMapper(), username);
    }

    public void updateOrderStatus(ChangeOrderStatusRequest changeOrder) {
        String sql = "UPDATE orders SET status = ?::order_status WHERE \"orderId\" = ?";
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

}
