/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aceade.corpolc.inventory.model.supplies;

import com.aceade.corpolc.inventory.model.supplies.types.FoodType;
import java.sql.Date;

/**
 *
 * @author philip
 */
public class FoodSupply extends Item {
    
    private Date expiryDate;
    
    private FoodType foodType;
    
    public FoodSupply() {
        super();
    }

    /**
     * @return the expiryDate
     */
    public Date getExpiryDate() {
        return expiryDate;
    }

    /**
     * @param expiryDate the expiryDate to set
     */
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    /**
     * @return the foodType
     */
    public FoodType getFoodType() {
        return foodType;
    }

    /**
     * @param foodType the foodType to set
     */
    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }
    
    
}
