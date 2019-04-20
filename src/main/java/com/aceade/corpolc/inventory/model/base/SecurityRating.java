/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.model.base;

/**
 *
 * @author philip
 */
public enum SecurityRating {
    
    MINIMUM,
    LOW,
    MEDIUM,
    PRIVATE,
    CONFIDENTIAL,
    HIGH,
    HIGHEST;
    
    public int toInt() {
        return this.ordinal();
    }
    
}
