/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.dao;

import com.aceade.corpolc.inventory.model.request.ChangeOrderStatusRequest;
import com.aceade.corpolc.inventory.model.request.NewItemRequest;
import com.aceade.corpolc.inventory.model.request.NewOrderRequest;
import com.aceade.corpolc.inventory.model.supplies.Item;
import com.aceade.corpolc.inventory.model.supplies.Order;
import java.util.List;
import java.util.Map;

/**
 *
 * @author philip
 */
public interface SupplyDao {

    void addItem(NewItemRequest newItem);

    Item getItem(long itemId);
    
    List<Item> getAllItems();

    List<Item> getItemsWithName(String name);

    Map<Item, Integer> getOrderItems(long orderId);

    Map<Item, Integer> getSiteStocks(long siteId);

    void placeOrder(NewOrderRequest newOrderRequest, String username);

    void updateOrderStatus(ChangeOrderStatusRequest changeOrder);

    Order viewOrder(long orderId);

    List<Order> viewOrdersByUser(String username);
    
}
