/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.services;

import com.aceade.corpolc.inventory.dao.SupplyDao;
import com.aceade.corpolc.inventory.model.request.ChangeOrderStatusRequest;
import com.aceade.corpolc.inventory.model.request.NewItemRequest;
import com.aceade.corpolc.inventory.model.request.NewOrderRequest;
import com.aceade.corpolc.inventory.model.supplies.Item;
import com.aceade.corpolc.inventory.model.supplies.Order;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Handles anything relating to supplies and stocks.
 * @author philip
 */
@Singleton
public class SupplyService {
    
    @Inject
    private SupplyDao supplyDao;
    
    public void addItem(NewItemRequest newItem) {
        supplyDao.addItem(newItem);
    }
    
    public List<Item> getAllItems() {
		return supplyDao.getAllItems();
	}
    
    public Item getItem(long itemId) {
        return supplyDao.getItem(itemId);
    }
    
    public void addOrder(NewOrderRequest newOrderRequest, String username) {
        supplyDao.placeOrder(newOrderRequest, username);
    }
    
    public Order viewOrder(long orderId) {
        return supplyDao.viewOrder(orderId);
    }
    
    public List<Order> viewUsersOrders(String username) {
        return supplyDao.viewOrdersByUser(username);
    }

    public void changeOrderStatus(ChangeOrderStatusRequest changeOrder) {
        supplyDao.updateOrderStatus(changeOrder);
    }

    public List<Item> getItemsWithName(String name) {
        return supplyDao.getItemsWithName(name);
    }

    public Map<Item, Integer> getSiteStocks(long siteId) {
        return supplyDao.getSiteStocks(siteId);
    }
}
