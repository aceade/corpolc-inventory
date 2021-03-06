/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.model.request;

import com.aceade.corpolc.inventory.model.supplies.SupplyType;

/**
 *
 * @author philip
 */
public class NewItemRequest {
    private String name;
    
    private double buyingPrice;
    
    private double sellingPrice;
    
    private double weightPerUnit;
    
    private boolean consumable;
    
    private SupplyType type;
    
    public NewItemRequest() {
        // default constructor
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the buyingPrice
     */
    public double getBuyingPrice() {
        return buyingPrice;
    }

    /**
     * @param buyingPrice the buyingPrice to set
     */
    public void setBuyingPrice(double buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    /**
     * @return the sellingPrice
     */
    public double getSellingPrice() {
        return sellingPrice;
    }

    /**
     * @param sellingPrice the sellingPrice to set
     */
    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    /**
     * @return the weightPerUnit
     */
    public double getWeightPerUnit() {
        return weightPerUnit;
    }

    /**
     * @param weightPerUnit the weightPerUnit to set
     */
    public void setWeightPerUnit(double weightPerUnit) {
        this.weightPerUnit = weightPerUnit;
    }

    /**
     * @return the consumable
     */
    public boolean isConsumable() {
        return consumable;
    }

    /**
     * @param consumable the consumable to set
     */
    public void setConsumable(boolean consumable) {
        this.consumable = consumable;
    }

    /**
     * @return the type
     */
    public SupplyType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(SupplyType type) {
        this.type = type;
    }
    
    
}
