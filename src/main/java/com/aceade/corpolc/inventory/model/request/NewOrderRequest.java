/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.model.request;

import java.util.Date;
import java.util.Map;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author philip
 */
public class NewOrderRequest {
    
    @Min(1)
    private long siteId;
    
    @NotNull
    private long orderDate;
    
    // map of object IDs to quantity
    @NotNull
    private Map<Long, Integer> orderItems;
    
    public NewOrderRequest() {
        // default constructor
    }

    /**
     * @return the siteId
     */
    public long getSiteId() {
        return siteId;
    }

    /**
     * @param siteId the siteId to set
     */
    public void setSiteId(long siteId) {
        this.siteId = siteId;
    }

    /**
     * @return the orderDate
     */
    public long getOrderDate() {
        return orderDate;
    }

    /**
     * @param orderDate the orderDate to set
     */
    public void setOrderDate(long orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * @return the orderItems
     */
    public Map<Long, Integer> getOrderItems() {
        return orderItems;
    }

    /**
     * @param orderItems the orderItems to set
     */
    public void setOrderItems(Map<Long, Integer> orderItems) {
        this.orderItems = orderItems;
    }
    
    
}
