/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.model.request;

import com.aceade.corpolc.inventory.model.supplies.OrderStatus;

/**
 *
 * @author philip
 */
public class ChangeOrderStatusRequest {
    
    private long orderId;
    
    private OrderStatus newStatus;
    
    public ChangeOrderStatusRequest() {
        
    }

    /**
     * @return the newStatus
     */
    public OrderStatus getNewStatus() {
        return newStatus;
    }

    /**
     * @param newStatus the newStatus to set
     */
    public void setNewStatus(OrderStatus newStatus) {
        this.newStatus = newStatus;
    }

    /**
     * @return the orderId
     */
    public long getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }
    
    
}
