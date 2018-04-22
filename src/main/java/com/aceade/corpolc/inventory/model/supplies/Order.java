/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.model.supplies;

import com.aceade.corpolc.inventory.model.base.Site;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Holds details for a supply run.
 * @author philip
 */
public class Order {
    
    private long id;
    
    private Map<Item, Integer> items;
    
    private Site address;
    
    private Date orderDate;
    
    private OrderStatus orderStatus;
    
    private String username;
    
    public Order() {
        //
    }

    /**
     * @return the items
     */
    public Map<Item, Integer> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(Map<Item, Integer> items) {
        this.items = items;
    }

    /**
     * @return the address
     */
    public Site getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(Site address) {
        this.address = address;
    }

    /**
     * @return the orderDate
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate the orderDate to set
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * @return the orderStatus
     */
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    /**
     * @param orderStatus the orderStatus to set
     */
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }
    
    
}
